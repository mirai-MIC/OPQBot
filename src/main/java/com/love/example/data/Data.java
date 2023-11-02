package com.love.example.data;

import com.google.gson.annotations.SerializedName;
import lombok.NoArgsConstructor;

/**
 * @Author: mi
 * @CreateTime: 2023/10/31 10:07
 * @Description:
 */


@NoArgsConstructor
@lombok.Data
public class Data {

    @SerializedName("model")
    private String model;
    @SerializedName("input")
    private InputVO input;
    @SerializedName("parameters")
    private ParametersVO parameters;
}
