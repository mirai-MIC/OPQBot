package com.love.listener;

import com.love.entity.event.image.Image;
import com.love.enums.MsgType;
import com.love.event.Event;
import com.love.event.GroupMessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


/**
 * @Author: mi
 * @CreateTime: 2023/10/25 21:27
 * @Description: 控制台输出日志
 */


@Slf4j
@Component
public class MessageLogger {
    @EventListener
    public void Message(Event event) {
        var eventData = event.getMsgBodyVO().getCurrentPacket().getEventData();
        var msgHead = eventData.getMsgHead();
        var msgBody = eventData.getMsgBody();
        var fromUin = msgHead.getFromUin();
        var senderUin = msgHead.getSenderUin();
        var senderNick = msgHead.getSenderNick();

        log.info("消息code: %s ".formatted(MsgType.fromValue(msgHead.getMsgType()).getValue()));
        log.info("群: %d 成员:%d( %s )".formatted(fromUin, senderUin, senderNick.trim()));
        if (msgBody != null && !msgBody.getContent().isBlank())
            log.info("Text消息: %s".formatted(msgBody.getContent()));

        if (msgBody != null && msgBody.getImages() != null)
            msgBody.getImages().stream().map(Image::getUrl).map("图片链接->: %s".trim()::formatted).forEach(log::info);
        log.info("消息链: %s \n".formatted(msgBody));
    }
}
