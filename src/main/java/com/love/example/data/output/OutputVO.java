package com.love.example.data.output;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: mi
 * @CreateTime: 2023/10/31 12:43
 * @Description:
 */


@NoArgsConstructor
@Data
public class OutputVO {
    @SerializedName("finish_reason")
    private String finishReason;
    @SerializedName("text")
    private String text;
}
