package com.love.entity.event.eventmessage;

import com.google.gson.annotations.SerializedName;
import com.love.entity.event.group.at.AtMessages;
import com.love.entity.event.group.redbag.RedBagVO;
import com.love.entity.event.image.Image;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @BelongsProject: NTQQ
 * @BelongsPackage: com.ntqq.message.data
 * @Author: mi
 * @CreateTime: 2023/10/23 11:20
 * @Description:
 * @Version: 1.0
 */


@NoArgsConstructor
@Data
public class MsgBodyVO {
    @SerializedName("SubMsgType")
    private Integer subMsgType;
    @SerializedName("Content")
    private String content;
    @SerializedName("AtUinLists")
    private List<AtMessages> atUinLists;
    @SerializedName("Images")
    private List<Image> images;
    @SerializedName("Video")
    private Object video;
    @SerializedName("Voice")
    private Object voice;
    @SerializedName("RedBag")
    private RedBagVO redBag;

    @Override
    public String toString() {
        return "Message{" +
                "subMsgType=" + subMsgType +
                ", content='" + content + '\'' +
                ", atUinLists=" + atUinLists +
                ", images=" + images +
                ", video=" + video +
                ", voice=" + voice +
                ", redBag=" + redBag +
                '}';
    }
}
