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
public class CurrentPacketVO {
    @SerializedName("EventData")
    private EventDataVO eventData;
    @SerializedName("EventName")
    private String eventName;
}
