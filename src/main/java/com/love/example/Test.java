package com.love.example;

import com.google.common.util.concurrent.RateLimiter;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.love.entity.event.eventmessage.MsgBodyVO;
import com.love.entity.event.eventmessage.MsgHeadVO;
import com.love.entity.event.group.at.AtMessages;
import com.love.entity.event.group.redbag.RedBagVO;
import com.love.event.GroupMessageEvent;
import com.love.message.MessageImageRequest;
import com.love.message.MessageRequest;
import com.love.message.RedBagRequest;
import com.love.message.UpLoadFile;
import com.love.message.data.ResponseDataVO;
import com.love.message.data.UploadResponse;
import com.love.service.OtherApi;
import com.love.service.SendMessageApiClient;
import com.love.example.data.Data;
import com.love.example.data.InputVO;
import com.love.example.data.MessagesVO;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.love.utils.MessageUtils.MessageEquals;
import static com.love.utils.MessageUtils.compressAndEncodeToBase64Thumbnails;

/**
 * @Author: mi
 * @CreateTime: 2023/10/25 22:56
 * @Description:
 */
@Slf4j
@SuppressWarnings("all")
@Component
public class Test {



    /**
     * 令牌桶限流规则
     */
    private final RateLimiter rateLimiter = RateLimiter.create(0.2); // 限制为每秒0.3次
    @Resource
    private SendMessageApiClient client;
    @Resource
    private OtherApi api;
    @Value("${forest.variables.qq}")
    private Long qq;

    public String RandomThanks() {
        int i = new Random().nextInt(4);
        String message = "终于抢到了一次呜呜呜呜呜,谢谢老板";
        switch (i) {
            case 0 -> message = "谢谢老板谢谢老板谢谢老板，给你磕头";
            case 1 -> message = "wcwcwc刚进来就有红包吗";
            case 2 -> message = "发红包的最帅，爱你爱你爱你";
            case 3 -> message = "抢了这么多吗";
        }
        return message;
    }

    @EventListener
    public void send(GroupMessageEvent event) {
        MsgHeadVO msgHead = event.getMsgBodyVO().getCurrentPacket().getEventData().getMsgHead();
        if (!MessageEquals(event, "测试")) return;
        System.out.println(msgHead.getFromUin());
        List<MessageRequest.UserInfo> atUinLists = new ArrayList<>();
        atUinLists.add(new MessageRequest.UserInfo("0.0", 3092179918L));
        MessageRequest messageRequest = new MessageRequest(msgHead.getFromUin(), "你好", atUinLists);
        client.sendMessage(messageRequest);
    }

    /**
     * 进行发送图片功能，
     * compressAndEncodeToBase64Thumbnails()压缩图片质量
     * @param event
     */

    @Async
    @EventListener
    @SneakyThrows
    public void TestSend(GroupMessageEvent event) {
        if (!MessageEquals(event, "/img")) return;
        MsgHeadVO msgHead = event.getMsgBodyVO().getCurrentPacket().getEventData().getMsgHead();
        if (!rateLimiter.tryAcquire()) {
            MessageRequest messageRequest = new MessageRequest(msgHead.getFromUin(), "调用太频繁，触发限流规则", null);
            client.sendMessage(messageRequest);
            return;
        }

        Long fromUin = event.getMsgBodyVO().getCurrentPacket().getEventData().getMsgHead().getFromUin();
        UpLoadFile upLoadFile = new UpLoadFile(new UpLoadFile.CgiRequest(2, compressAndEncodeToBase64Thumbnails(api.getPc(), 1.0f)));
        UploadResponse uploadResponse = client.UpLoadFile(upLoadFile);
        log.info("上传图片: {}", new Gson().toJson(uploadResponse));
        ResponseDataVO responseData = uploadResponse.getResponseData();
        Long fileId = responseData.getFileId();
        if (fileId == null) return;
        String fileMd5 = responseData.getFileMd5();
        Integer fileSize = responseData.getFileSize();

        List<MessageImageRequest.ImageInfo> imageInfos = new ArrayList<>();
        imageInfos.add(new MessageImageRequest.ImageInfo(fileId, fileMd5, fileSize));
        MessageImageRequest messageImageRequest = new MessageImageRequest(fromUin, imageInfos);
        log.info("发送结果---> %s".formatted(client.sendMessage(messageImageRequest)));
    }

    @Async
    @SneakyThrows
    @EventListener
    public void Bac(GroupMessageEvent event) {
        if (!MessageEquals(event, "cnm")) return;
        MsgHeadVO msgHead = event.getMsgBodyVO().getCurrentPacket().getEventData().getMsgHead();
        if (!rateLimiter.tryAcquire()) {
            MessageRequest messageRequest = new MessageRequest(msgHead.getFromUin(), "调用太频繁，触发限流规则", null);
            client.sendMessage(messageRequest);
            return;
        }
        Long fromUin = event.getMsgBodyVO().getCurrentPacket().getEventData().getMsgHead().getFromUin();
        UpLoadFile upLoadFile = new UpLoadFile(new UpLoadFile.CgiRequest(2, compressAndEncodeToBase64Thumbnails(api.getLoli(), 0.9f)));
        UploadResponse uploadResponse = client.UpLoadFile(upLoadFile);
        log.info("上传图片: {}", new Gson().toJson(uploadResponse));
        ResponseDataVO responseData = uploadResponse.getResponseData();
        Long fileId = responseData.getFileId();
        if (fileId == null) return;
        String fileMd5 = responseData.getFileMd5();
        Integer fileSize = responseData.getFileSize();
        List<MessageImageRequest.ImageInfo> imageInfos = new ArrayList<>();
        imageInfos.add(new MessageImageRequest.ImageInfo(fileId, fileMd5, fileSize));
        MessageImageRequest messageImageRequest = new MessageImageRequest(fromUin, imageInfos);
        log.info("发送结果---> %s".formatted(client.sendMessage(messageImageRequest)));
    }

    /**
     * 快速抢红包功能，目前官方的只能支持拼手气红包和口令红包，就做了这个功能
     * @param event
     */

    @SneakyThrows
    @EventListener
    public void RedBagQuick(GroupMessageEvent event) {
        MsgHeadVO msgHead = event.getMsgBodyVO().getCurrentPacket().getEventData().getMsgHead();
        RedBagRequest.CgiRequest redBagVO = new RedBagRequest.CgiRequest();
        RedBagRequest redBagRequest = new RedBagRequest();
        RedBagVO redBag = event.getMsgBodyVO().getCurrentPacket().getEventData().getMsgBody().getRedBag();
        if (redBag == null) return;
        redBagVO.setWishing(redBag.getWishing());
        redBagVO.setDes(redBag.getDes());
        redBagVO.setRedType(redBag.getRedType());
        redBagVO.setListid(redBag.getListid());
        redBagVO.setAuthkey(redBag.getAuthkey());
        redBagVO.setChannel(redBag.getChannel());
        redBagVO.setStingIndex(redBag.getStingIndex());
        redBagVO.setTransferMsg(redBag.getTransferMsg());
        redBagVO.setToken_17_2(redBag.getToken_17_2());
        redBagVO.setToken_17_3(redBag.getToken_17_3());
        redBagVO.setFromUin(redBag.getFromUin());
        redBagVO.setFromType(redBag.getFromType());
        redBagRequest.setCgiRequest(redBagVO);
        double money = new Gson().fromJson(client.sendMessage(redBagRequest), JsonObject.class).getAsJsonObject("ResponseData").get("GetMoney").getAsDouble();
        if (redBag.getRedType().intValue() == 12) {
            client.sendMessage(new MessageRequest(msgHead.getFromUin(), redBag.getWishing(), null));
            Thread.sleep(10000);
            client.sendMessage(new MessageRequest(msgHead.getFromUin(), RandomThanks(), null));
        } else {
            Thread.sleep(10000);
            client.sendMessage(new MessageRequest(msgHead.getFromUin(), RandomThanks(), null));
        }
        log.info("领取红包金额: {} 元", money / 100.0);
    }

    /**
     * 采用了通意千问AI，自己去官网注册一个账号，把token粘贴进yml
     * @param event
     */
    @Async
    @EventListener
    public void OpenAig(GroupMessageEvent event) {
        System.out.println(event);
        MsgBodyVO msgBody = event.getMsgBodyVO().getCurrentPacket().getEventData().getMsgBody();
        MsgHeadVO msgHead = event.getMsgBodyVO().getCurrentPacket().getEventData().getMsgHead();
        List<AtMessages> atUinLists = msgBody.getAtUinLists();
        if (atUinLists == null) return;
        if (atUinLists.get(0).getUin().intValue() != qq) return;
        Data data = new Data();
        InputVO inputVO = new InputVO();
        MessagesVO messagesVO = new MessagesVO();
        List<MessagesVO> messages = new ArrayList<>();
        messagesVO.setRole("user");
        messagesVO.setContent(msgBody.getContent());
        messages.add(messagesVO);
        inputVO.setMessages(messages);

        data.setModel("qwen-turbo");
        data.setInput(inputVO);
        data.setParameters(null);
        client.sendMessage(new MessageRequest(msgHead.getFromUin(), api.helloForest(new Gson().toJson(data)).getOutput().getText(), null));
    }
}
