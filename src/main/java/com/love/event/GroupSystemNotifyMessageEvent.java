package com.love.event;

import com.google.gson.Gson;
import com.love.entity.event.group.groupsystem.GroupSystemNofityMessage;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @Author: mi
 * @CreateTime: 2023/10/25 21:21
 * @Description:
 */

@Getter
public class GroupSystemNotifyMessageEvent extends ApplicationEvent {
    private final GroupSystemNofityMessage msgBodyVO;

    public GroupSystemNotifyMessageEvent(Object source, String msgBodyVO) {
        super(source);
        this.msgBodyVO = new Gson().fromJson(msgBodyVO, GroupSystemNofityMessage.class);
    }
}
