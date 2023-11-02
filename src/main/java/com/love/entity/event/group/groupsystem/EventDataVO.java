package com.love.entity.event.group.groupsystem;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: mi
 * @CreateTime: 2023/10/25 21:20
 * @Description:
 */


@NoArgsConstructor
@Data
public class EventDataVO {
    @SerializedName("ActorUid")
    private String actorUid;
    @SerializedName("ActorUidNick")
    private String actorUidNick;
    @SerializedName("GroupCode")
    private Integer groupCode;
    @SerializedName("GroupName")
    private String groupName;
    @SerializedName("InvitorUid")
    private String invitorUid;
    @SerializedName("InvitorUidNick")
    private String invitorUidNick;
    @SerializedName("MsgAdditional")
    private String msgAdditional;
    @SerializedName("MsgSeq")
    private Long msgSeq;
    @SerializedName("MsgType")
    private Integer msgType;
    @SerializedName("ReqUid")
    private String reqUid;
    @SerializedName("ReqUidNick")
    private String reqUidNick;
    @SerializedName("Status")
    private Integer status;
}
