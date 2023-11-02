package com.love.event;

import com.google.gson.Gson;
import com.love.entity.event.group.exit.GroupExitMessage;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @Author: mi
 * @CreateTime: 2023/10/25 21:14
 * @Description:
 */

@Getter
public class GroupExitMessageEvent extends ApplicationEvent {
    private final GroupExitMessage msgBodyVO;

    public GroupExitMessageEvent(Object source, String msgBodyVO) {
        super(source);
        this.msgBodyVO = new Gson().fromJson(msgBodyVO, GroupExitMessage.class);
    }
}
