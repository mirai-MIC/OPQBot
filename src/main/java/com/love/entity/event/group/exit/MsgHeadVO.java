package com.love.entity.event.group.exit;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: mi
 * @CreateTime: 2023/10/25 22:21
 * @Description:
 */


@NoArgsConstructor
@Data
public class MsgHeadVO {
    @SerializedName("FromUin")
    private Integer fromUin;
    @SerializedName("FromUid")
    private String fromUid;
    @SerializedName("ToUin")
    private Integer toUin;
    @SerializedName("ToUid")
    private String toUid;
    @SerializedName("FromType")
    private Integer fromType;
    @SerializedName("SenderUin")
    private Integer senderUin;
    @SerializedName("SenderUid")
    private String senderUid;
    @SerializedName("SenderNick")
    private String senderNick;
    @SerializedName("MsgType")
    private Integer msgType;
    @SerializedName("C2cCmd")
    private Integer c2cCmd;
    @SerializedName("MsgSeq")
    private Integer msgSeq;
    @SerializedName("MsgTime")
    private Integer msgTime;
    @SerializedName("MsgRandom")
    private Integer msgRandom;
    @SerializedName("MsgUid")
    private Long msgUid;
    @SerializedName("GroupInfo")
    private Object groupInfo;
    @SerializedName("C2CTempMessageHead")
    private Object c2CTempMessageHead;
}
