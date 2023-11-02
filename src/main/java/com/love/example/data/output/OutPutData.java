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
public class OutPutData {

    @SerializedName("output")
    private OutputVO output;
    @SerializedName("usage")
    private UsageVO usage;
    @SerializedName("request_id")
    private String requestId;
}
