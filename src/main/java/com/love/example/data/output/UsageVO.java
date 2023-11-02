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
public class UsageVO {
    @SerializedName("total_tokens")
    private Integer totalTokens;
    @SerializedName("output_tokens")
    private Integer outputTokens;
    @SerializedName("input_tokens")
    private Integer inputTokens;
}
