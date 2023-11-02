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
public class EventVO {
    @SerializedName("Uid")
    private String uid;
}
