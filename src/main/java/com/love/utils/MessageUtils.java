package com.love.utils;

import com.love.entity.event.eventmessage.MsgBodyVO;
import com.love.event.GroupMessageEvent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

/**
 * @Author: mi
 * @CreateTime: 2023/10/27 12:17
 * @Description:
 */

@Slf4j
public class MessageUtils {
    /**
     * 判断消息是否相等<br>
     * <font color="blue">有开发的注解，但是有大量的bug未能及时改正，所以目前用的这个小工具替代</font>
     *
     * @param event
     * @param value
     * @return
     */
    public static Boolean MessageEquals(GroupMessageEvent event, String value) {
        MsgBodyVO msgBody = event.getMsgBodyVO().getCurrentPacket().getEventData().getMsgBody();
        return msgBody != null && msgBody.getContent().equals(value);
    }


    /**
     * 压缩图片并转换为base64<br>
     * <font color="red">注意：压缩后的图片大小会变小，但是长宽不会变小</font><br>
     *
     * <font color="red">❗只是为了解决图片发送失败问题</font>
     *
     * @param imageBytes 图片字节
     * @param quality    压缩质量 0.0最低质量  1.0最高画质
     * @return
     */
    @SneakyThrows
    public static String compressAndEncodeToBase64Thumbnails(byte[] imageBytes, double quality) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Thumbnails.of(new ByteArrayInputStream(imageBytes))
                .scale(1.0)
                .outputQuality(quality)
                .toOutputStream(os);
        return Base64.getEncoder().encodeToString(os.toByteArray());
    }

}
