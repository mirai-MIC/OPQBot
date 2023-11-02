package com.love.enums;

import lombok.Getter;

@Getter
public enum EventName {

    /**
     * 登录成功
     */
    ON_EVENT_LOGIN_SUCCESS("ON_EVENT_LOGIN_SUCCESS"),

    /**
     * 网络变化
     */
    ON_EVENT_NETWORK_CHANGE("ON_EVENT_NETWORK_CHANGE"),

    /**
     * 群组新消息事件
     */
    GROUP_NEW_MSG("ON_EVENT_GROUP_NEW_MSG"),

    /**
     * 好友/私聊新消息事件
     */
    FRIEND_NEW_MSG("ON_EVENT_FRIEND_NEW_MSG"),

    /**
     * 好友系统消息通知事件
     */
    FRIEND_SYSTEM_MSG_NOTIFY("ON_EVENT_FRIEND_SYSTEM_MSG_NOTIFY"),

    /**
     * 群组加入事件
     */
    GROUP_JOIN("ON_EVENT_GROUP_JOIN"),

    /**
     * 邀请进群事件
     */
    ON_EVENT_GROUP_INVITE("ON_EVENT_GROUP_INVITE"),

    /**
     * 群组退出事件
     */
    GROUP_EXIT("ON_EVENT_GROUP_EXIT"),
    /**
     * 撤回消息
     */
    ON_EVENT_GROUP_MSG_REVOKE("ON_EVENT_GROUP_MSG_REVOKE"),
    /**
     * 群组系统消息通知事件
     */
    GROUP_SYSTEM_MSG_NOTIFY("ON_EVENT_GROUP_SYSTEM_MSG_NOTIFY");

    private final String eventName;

    EventName(String eventName) {
        this.eventName = eventName;
    }

    public static EventName fromEventName(String eventName) {
        for (EventName name : EventName.values()) {
            if (name.getEventName().equals(eventName)) {
                return name;
            }
        }
        throw new IllegalArgumentException("Invalid EventName: " + eventName);
    }
}

