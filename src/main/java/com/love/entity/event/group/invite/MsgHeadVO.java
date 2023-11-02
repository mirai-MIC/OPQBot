package com.love.entity.event.group.invite;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: mi
 * @CreateTime: 2023/10/25 21:17
 * @Description:
 */


@NoArgsConstructor
@Data
public class MsgHeadVO {
    @SerializedName("FromUin")
    private Long fromUin;
    @SerializedName("FromUid")
    private String fromUid;
    @SerializedName("ToUin")
    private Long toUin;
    @SerializedName("ToUid")
    private String toUid;
    @SerializedName("FromType")
    private Long fromType;
    @SerializedName("SenderUin")
    private Long senderUin;
    @SerializedName("SenderUid")
    private String senderUid;
    @SerializedName("SenderNick")
    private String senderNick;
    @SerializedName("MsgType")
    private Long msgType;
    @SerializedName("C2cCmd")
    private Long c2cCmd;
    @SerializedName("MsgSeq")
    private Long msgSeq;
    @SerializedName("MsgTime")
    private Long msgTime;
    @SerializedName("MsgRandom")
    private Long msgRandom;
    @SerializedName("MsgUid")
    private Long msgUid;
    @SerializedName("GroupInfo")
    private Object groupInfo;
    @SerializedName("C2CTempMessageHead")
    private Object c2CTempMessageHead;
}
