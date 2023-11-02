package com.love.message;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: mi
 * @Create: 2023/10/26 20:15
 * @Description: <font size="5" color="#39C5BB ">用来封装的发送群消息的实体</font>
 * <pre>
 * {
 *   "CgiCmd": "MessageSvc.PbSendMsg",
 *   "CgiRequest": {
 *     "ToUin": 88888888, //发送消息对象 好友/私聊/群组Uid
 *     "ToType": 2,       //发送消息对象类型 3私聊 2群组 1好友
 *     "Content": "你好",  //发送消息内容 长文本未测试
 *     "AtUinLists": [    //AtUser数组
 *       {
 *         "Uin": 123456789
 *       },
 *       {
 *         "Uin": 987654321
 *       }
 *     ]
 *     "Images": [       //图片数组
 *       {
 *         "FileId": 2780922102,
 *         "FileMd5": "0N6b4nNKvivUxHQCB+E0QA==",
 *         "FileSize": 34880
 *       }
 *     ]
 *     "Voice": {
 *       "FileMd5": "fk5AXTZkLcEp8tK0jGINgQ==",
 *       "FileSize": 47121,
 *       "FileToken": "9Ai1NvpkGg0agwBve1knSgmb1DyYijbzyRgw"
 *     }
 *   }
 * }
 * </pre>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest {
    private String CgiCmd;
    private CgiRequest CgiRequest;

    public MessageRequest(long groupId, String content, List<UserInfo> atUinLists) {
        this.CgiCmd = "MessageSvc.PbSendMsg";
        this.CgiRequest = new CgiRequest(groupId, content, atUinLists);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CgiRequest {
        private long ToUin;
        private int ToType;
        private String Content;
        private List<UserInfo> AtUinLists;

        public CgiRequest(long toUin, String content, List<UserInfo> atUinLists) {
            this.ToUin = toUin;
            this.ToType = 2;
            this.Content = content;
            this.AtUinLists = atUinLists;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserInfo {
        private String Nick;
        private long Uin;
    }
}
