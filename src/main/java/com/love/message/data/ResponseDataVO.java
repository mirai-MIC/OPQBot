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
public class ResponseDataVO {
    @SerializedName("FileId")
    private Long fileId;
    @SerializedName("FileMd5")
    private String fileMd5;
    @SerializedName("FileSize")
    private Integer fileSize;
}
