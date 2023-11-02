package com.love.enums;

import com.love.event.GroupMessageEvent;
import lombok.Getter;

/**
 * @Author: mi
 * @CreateTime: 2023/10/26 16:27
 * @Description:
 */

@Getter
public enum MessageTypeUtil {
    GROUPM_ESSAGE(GroupMessageEvent.class),

    ;

    MessageTypeUtil(Class<GroupMessageEvent> groupMessageEventClass) {

    }
}
