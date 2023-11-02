package com.love.entity.event.eventmessage;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: NTQQ
 * @BelongsPackage: com.ntqq.message.data
 * @Author: mi
 * @CreateTime: 2023/10/23 11:19
 * @Description:
 * @Version: 1.0
 */

/**
 * <pre>
 * </pre>
 */
@NoArgsConstructor
@Data
public class EventMessage {
    @SerializedName("CurrentPacket")
    private CurrentPacketVO currentPacket;
    @SerializedName("CurrentQQ")
    private Long currentQQ;
}
