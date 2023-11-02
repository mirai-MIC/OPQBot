package com.love.example.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: mi
 * @CreateTime: 2023/10/31 10:20
 * @Description:
 */


@NoArgsConstructor
@Data
public class InputVO {
    @SerializedName("messages")
    private List<MessagesVO> messages;
}
