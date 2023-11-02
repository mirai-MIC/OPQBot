package com.love.event;

import com.google.gson.Gson;
import com.love.entity.event.group.join.GroupJoinMessage;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @Author: mi
 * @CreateTime: 2023/10/25 21:10
 * @Description:
 */
@Getter
public class GroupJoinMessageEvent extends ApplicationEvent {
    private final GroupJoinMessage msgBodyVO;

    public GroupJoinMessageEvent(Object source, String msgBodyVO) {
        super(source);
        this.msgBodyVO = new Gson().fromJson(msgBodyVO, GroupJoinMessage.class);
    }
}
