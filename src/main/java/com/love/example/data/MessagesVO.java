package com.love.example.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: mi
 * @CreateTime: 2023/10/31 10:20
 * @Description:
 */


@NoArgsConstructor
@Data
public class MessagesVO {
    @SerializedName("role")
    private String role;
    @SerializedName("content")
    private String content;
}
