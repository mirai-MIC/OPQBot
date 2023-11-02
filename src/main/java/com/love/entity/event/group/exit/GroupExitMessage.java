package com.love.entity.event.group.exit;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: mi
 * @CreateTime: 2023/10/25 21:13
 * @Description:
 */


@NoArgsConstructor
@Data
public class GroupExitMessage {

    @SerializedName("CurrentPacket")
    private CurrentPacketVO currentPacket;
    @SerializedName("CurrentQQ")
    private Integer currentQQ;
}
