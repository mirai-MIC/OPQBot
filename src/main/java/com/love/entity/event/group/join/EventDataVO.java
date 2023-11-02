package com.love.entity.event.group.join;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: mi
 * @CreateTime: 2023/10/25 21:09
 * @Description:
 */


@NoArgsConstructor
@Data
public class EventDataVO {
    @SerializedName("MsgHead")
    private MsgHeadVO msgHead;
    @SerializedName("MsgBody")
    private Object msgBody;
    @SerializedName("Event")
    private EventVO event;
}
