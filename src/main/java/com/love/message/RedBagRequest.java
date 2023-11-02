package com.love.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h2 style="color:red;">打开红包实体类</h2>
 * <p style="color:red;">注意：此类不可随意修改，否则可能导致打开红包失败</p>
 * <pre style="color:#66ccff;">
 * {
 *   "CgiCmd": "OpenREDBAG",
 *   "CgiRequest": {
 *     "Wishing": "大吉大利🤔️",
 *     "Des": "赶紧点击拆开吧",
 *     "RedType": 6,
 *     "Listid": "10000441012310133800111037285200",
 *     "Authkey": "405d13c61ddfbb063d84e1383a5144dez9",
 *     "Channel": 1,
 *     "StingIndex": "N2M5ZCcxZTdlMDZhMDJjNDFmOTAwNzRlNTZlZTFiZDY=",
 *     "TransferMsg": "",
 *     "Token_17_2": "PTNHoJi5T5iajyRoIBwoVHQmsptDMekJBmxX6jwpkGQ=",
 *     "Token_17_3": "Njc2OTk5MjU0NzRiYmQyOWFmYzIxOWJiMDczOWUyMzg=",
 *     "FromUin": 12345678,
 *     "FromType": 0
 *   }
 * </pre>
 */
@Data
@AllArgsConstructor
public class RedBagRequest {
    private String CgiCmd;
    private CgiRequest CgiRequest;

    public RedBagRequest() {
        this.CgiCmd = "OpenREDBAG";
        this.CgiRequest = new CgiRequest();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CgiRequest {
        private String Wishing;
        private String Des;
        private int RedType;
        private String Listid;
        private String Authkey;
        private int Channel;
        private String StingIndex;
        private String TransferMsg;
        private String Token_17_2;
        private String Token_17_3;
        private long FromUin;
        private int FromType;
    }
}