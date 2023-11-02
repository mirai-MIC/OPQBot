package com.love.config;

import com.google.gson.Gson;
import com.love.aspects.ListenerRegistry;
import com.love.entity.event.eventmessage.EventMessage;
import com.love.enums.EventName;
import com.love.event.*;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@SuppressWarnings("all")
public class AutoWebSocket {

    private final int maxRetries = 20;
    private final long retryInterval = 20;
    @Resource
    private ApplicationContext context;
    @Value("${base.ws}")
    private String ws;
    @Resource
    private ListenerRegistry listenerRegistry;
    private WebSocketClient webSocketClient;
    //    private ScheduledExecutorService scheduler;
    private ScheduledFuture<?> reconnectTask;
    private int retryCount = 0;
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public AutoWebSocket(ApplicationContext context, @Value("${base.ws}") String ws, ListenerRegistry listenerRegistry) {
        this.context = context;
        this.ws = ws;
        this.listenerRegistry = listenerRegistry;
    }

    @Bean
    public WebSocketClient webSocketClient() throws URISyntaxException {
        webSocketClient = new WebSocketClient(new URI(ws)) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                log.info("建立WS连接: {}", serverHandshake.getHttpStatusMessage());
                cancelReconnectTask();
            }

            @SneakyThrows
            @Override
            public void onMessage(String s) {
                var eventMessage = new Gson().fromJson(s, EventMessage.class);
                EventName eventName = EventName.fromEventName(eventMessage.getCurrentPacket().getEventName());
                switch (eventName) {
                    case GROUP_NEW_MSG -> {
                        if (eventMessage.getCurrentPacket().getEventData().getMsgBody() == null) log.warn("收到空消息");
                        else context.publishEvent(new GroupMessageEvent(this, s));
                    }
                    case FRIEND_NEW_MSG -> context.publishEvent(new PrivateMessageEvent(this, s));
                    case GROUP_JOIN -> context.publishEvent(new GroupJoinMessageEvent(this, s));
                    case GROUP_EXIT -> context.publishEvent(new GroupExitMessageEvent(this, s));
                    case ON_EVENT_GROUP_INVITE -> context.publishEvent(new GroupInviteMessageEvent(this, s));
                    case GROUP_SYSTEM_MSG_NOTIFY -> context.publishEvent(new GroupSystemNotifyMessageEvent(this, s));
                    default -> log.info("未处理事件: {}\n{}", eventName,s);
                }
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                if (retryCount < maxRetries) {
                    scheduleReconnect();
                    log.info("关闭WS连接: {}, {}, {}. 尝试重新连接WebSocket (第 {} 次)", i, s, b, retryCount + 1);
                } else {
                    log.warn("已达到最大重连次数，无法再次尝试连接");
                }
            }

            @Override
            public void onError(Exception e) {
                if (retryCount < maxRetries) {
                    scheduleReconnect();
                    log.error("WS异常: {}. 尝试重新连接WebSocket (第 {} 次)", e.getMessage(), retryCount + 1);
                } else {
                    log.warn("已达到最大重连次数，无法再次尝试连接");
                }
            }
        };

        // 初始连接
        webSocketClient.connect();
        return webSocketClient;
    }

    private void cancelReconnectTask() {
        if (reconnectTask != null && !reconnectTask.isDone()) {
            reconnectTask.cancel(true);
            if (scheduler != null) {
                scheduler.shutdown();
            }
        }
        if (scheduler != null) {
            scheduler.shutdown();
        }
    }

    // 重新连接WebSocket方法
    protected void reconnectWebSocket() {
        try {
            log.info("尝试重新连接WebSocket (第 {} 次)", retryCount);
            WebSocketClient newWebSocketClient = webSocketClient();
            newWebSocketClient.close();
            newWebSocketClient.reconnect();
        } catch (Exception e) {
            log.error("重连失败: {}", e.getMessage());
        } finally {
            retryCount++;
            if (retryCount < maxRetries) {
                scheduleReconnect();
            } else {
                log.warn("已达到最大重连次数，无法再次尝试连接");
            }
        }
    }

    private void scheduleReconnect() {
        scheduler.schedule(this::reconnectWebSocket, retryInterval, TimeUnit.SECONDS);
    }

}
