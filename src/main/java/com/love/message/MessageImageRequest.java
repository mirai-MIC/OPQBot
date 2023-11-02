package com.love.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: mi
 * @CreateTime: 2023/10/27 12:57
 * @Description:
 */

@Data
public class MessageImageRequest {
    //{
    //  "CgiCmd": "MessageSvc.PbSendMsg",
    //  "CgiRequest": {
    //    "ToUin": {{GroupId}},
    //    "ToType": 2,
    //    "Images": [
    //      {
    //        "FileId": 2503839574,
    //        "FileMd5": "x9GVssIUI/eZhO8g0adtKA==",
    //        "FileSize": 740246,
    //        "Height": 1920,
    //        "Width": 1080
    //      }
    //    ]
    //  }
    //}
    private String CgiCmd;
    private CgiRequest CgiRequest;

    public MessageImageRequest(long groupId, List<ImageInfo> images) {
        this.CgiCmd = "MessageSvc.PbSendMsg";
        this.CgiRequest = new CgiRequest(groupId, images);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CgiRequest {
        private long ToUin;
        private int ToType;
        private List<ImageInfo> Images;

        public CgiRequest(long toUin, List<ImageInfo> images) {
            this.ToUin = toUin;
            this.ToType = 2;
            this.Images = images;
        }
    }

    @Data
    @NoArgsConstructor
//    @AllArgsConstructor
    public static class ImageInfo {
        private long FileId;
        private String FileMd5;
        private long FileSize;
        private int Height = 50000;
        private int Width = 50000;

        public ImageInfo(long fileId, String fileMd5, long fileSize) {
            FileId = fileId;
            FileMd5 = fileMd5;
            FileSize = fileSize;
        }
    }

}
