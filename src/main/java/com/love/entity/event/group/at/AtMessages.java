package com.love.entity.event.group.at;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: mi
 * @CreateTime: 2023/10/31 11:19
 * @Description:
 */


@NoArgsConstructor
@Data
public class AtMessages {

    @SerializedName("Nick")
    private String nick;
    @SerializedName("Uid")
    private String uid;
    @SerializedName("Uin")
    private Long uin;
}
