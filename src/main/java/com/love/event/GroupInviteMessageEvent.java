package com.love.event;

import com.google.gson.Gson;
import com.love.entity.event.group.invite.GroupInviteMessage;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @Author: mi
 * @CreateTime: 2023/10/25 21:17
 * @Description:
 */

@Getter
public class GroupInviteMessageEvent extends ApplicationEvent {
    private final GroupInviteMessage msgBodyVO;

    public GroupInviteMessageEvent(Object source, String msgBodyVO) {
        super(source);
        this.msgBodyVO = new Gson().fromJson(msgBodyVO, GroupInviteMessage.class);
    }
}
