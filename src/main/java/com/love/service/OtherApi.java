package com.love.service;

import com.dtflys.forest.annotation.*;
import com.google.gson.JsonObject;
import com.love.example.data.output.OutPutData;
import lombok.SneakyThrows;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @Author: mi
 * @CreateTime: 2023/10/27 17:43
 * @Description: <font color="#66ccff" size="5">获取图片用的接口，为了方便使用，不再封装http工具，使用声明式HTTP客户端框架</font>
 */

@Component
@ComponentScan
public interface OtherApi {
    //https://tenapi.cn/v2/acg
    @Get("https://tenapi.cn/v2/acg")
    byte[] getAcg();

    //https://api.btstu.cn/sjbz/api.php?lx=dongman&format=json
    @Get("https://api.btstu.cn/sjbz/api.php?lx=dongman&format=json")
    JsonObject getDongman();

    // https://iw233.cn/api.php?sort=pc&type=json
    //https://iw233.cn/api.php?sort=cat&type=json
    //https://iw233.cn/api.php?sort=random&type=json
    @Get("https://iw233.cn/api.php?sort=random&type=json")
    JsonObject getIw233();

    //https://api.sretna.cn/layout/pc.php
    @Get("https://api.sretna.cn/layout/pc.php")
    @SneakyThrows
    byte[] getSretna();

    @Get("https://iw233.cn/api.php?sort=random")
    byte[] getRandom();

    // https://iw233.cn/api.php?sort=pc
    @Get("https://iw233.cn/api.php?sort=pc")
    byte[] getPc();

    //https://www.loliapi.com/acg/
    @Get("https://www.loliapi.com/acg/")
    byte[] getLoli();

    //http://localhost:8080/hello
    @Get("http://localhost:8080/hello")
    JsonObject getH();

    @Get("${url}")
    byte[] getIm(@Var("url") String url);

    /**
     * AI对话
     *
     * @param text
     * @return
     */
    @Headers({
            "Authorization: Bearer ${apiKey}",
            "Content-Type: application/json",
    })
    @Post("https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation")
    OutPutData helloForest(@Body String text);
}
