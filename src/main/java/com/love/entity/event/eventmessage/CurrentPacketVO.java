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
public class CurrentPacketVO {
    @SerializedName("EventData")
    private EventDataVO eventData;
    @SerializedName("EventName")
    private String eventName;
}
