package com.love.entity.event.eventmessage;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private GroupInfoVO groupInfo;
    @SerializedName("C2CTempMessageHead")
    private Object c2CTempMessageHead;
}
