package com.love.message;

import lombok.Data;

/**
 * @Author: mi
 * @CreateTime: 2023/10/27 12:23
 * @Description:
 */

@Data
public class UpLoadFile {


    /**
     * <pre>
     *  {
     *     "CgiCmd": "PicUp.DataUp",
     *     "CgiRequest": {
     *        "CommandId": 2,
     *        "Base64Buf": "<font color=red>Base64编码</font>"
     *      }
     *  }
     * </pre>
     */
    private String CgiCmd;
    private CgiRequest CgiRequest;


    public UpLoadFile(CgiRequest cgiRequest) {
        CgiCmd = "PicUp.DataUp";
        CgiRequest = cgiRequest;
    }

    public static class CgiRequest {
        private final int CommandId;
        private final String Base64Buf;
        private String FileUrl; // This field is not final

        public CgiRequest(int commandId, String base64Buf) {
            CommandId = commandId;
            Base64Buf = base64Buf;
        }

        public CgiRequest(int commandId, String base64Buf, String fileUrl) {
            this(commandId, base64Buf);
            FileUrl = fileUrl;
        }
    }
}

