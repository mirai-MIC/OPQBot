package com.love.enums;

import lombok.Getter;

@Getter
public enum MsgType {
    /**
     * 申请加入群组消息
     */
    APPLY_TO_JOIN(1),

    /**
     * 被邀请加入群组消息
     */
    INVITED_TO_JOIN(2),

    /**
     * 退出群聊消息
     */
    EXIT_GROUP(13),

    /**
     * 取消管理员消息
     */
    CANCEL_ADMIN(15),

    /**
     * 设置管理员消息
     */
    SET_ADMIN(3),

    /**
     * 群消息
     */
    GROUP_MESSAGE(82),
    /**
     * 撤回消息/邀请进群/戳一戳
     */
    RECALL_MESSAGE(732),
    /**
     * 邀请用户进群
     */
    INVITE_USER(33),

    OTHER(528),
    ;

    private final int value;

    MsgType(int value) {
        this.value = value;
    }

    public static MsgType fromValue(Long value) {
        for (MsgType type : MsgType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid MsgType value: %d".formatted(value));
    }
}

