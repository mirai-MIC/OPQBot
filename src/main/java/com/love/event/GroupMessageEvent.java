package com.love.event;

import com.google.gson.Gson;
import com.love.entity.event.eventmessage.EventMessage;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @Author: mi
 * @CreateTime: 2023/10/25 20:59
 * @Description: 群聊消息
 */

@Getter
public class GroupMessageEvent extends ApplicationEvent {
    private final EventMessage msgBodyVO;

    public GroupMessageEvent(Object source, String msgBodyVO) {
        super(source);
        this.msgBodyVO = new Gson().fromJson(msgBodyVO, EventMessage.class);
    }

}
