package com.love.service;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.love.message.UpLoadFile;
import com.love.message.data.UploadResponse;
import lombok.SneakyThrows;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @Author: mi
 * @CreateTime: 2023/10/26 18:58
 * @Description: <font size="5" color="#39C5BB ">消息发送接口</font>
 */

@Component
@ComponentScan
@BaseRequest(
        baseURL = "${baseApi}/v1"
)
public interface SendMessageApiClient {
    /**
     * 发送群消息
     */
    @Post("/LuaApiCaller?funcname=MagicCgiCmd&timeout=10&qq=${qq}")
    @SneakyThrows
    String sendMessage(@JSONBody Object data);

    /**
     * 上传资源文件/图片
     *
     * @param data
     */
    @Post("/upload?qq=${qq}")
    @SneakyThrows
    UploadResponse UpLoadFile(@JSONBody UpLoadFile data);

}
