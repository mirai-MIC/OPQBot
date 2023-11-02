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
public class GroupJoinMessage {

    @SerializedName("CurrentPacket")
    private CurrentPacketVO currentPacket;
    @SerializedName("CurrentQQ")
    private Integer currentQQ;
}
