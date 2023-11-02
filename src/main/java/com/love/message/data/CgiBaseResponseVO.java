package com.love.message.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: mi
 * @CreateTime: 2023/10/27 12:50
 * @Description:
 */


@NoArgsConstructor
@Data
public class CgiBaseResponseVO {
    @SerializedName("ErrMsg")
    private String errMsg;
    @SerializedName("Ret")
    private Integer ret;
}
