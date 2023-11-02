package com.love;

import com.love.config.AutoWebSocket;
import jakarta.annotation.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class NtqqProApplication {
    @Resource
    private AutoWebSocket autoWebSocket;

    public static void main(String[] args) {
        SpringApplication.run(NtqqProApplication.class, args);
    }
}
