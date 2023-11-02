package com.love.entity.event.image;

import lombok.Getter;

@Getter
public class Image {
    // Getters and setters for the properties
    private String FileId;
    private String FileMd5;
    private double FileSize;
    private String Url;

    public void setFileId(String fileId) {
        this.FileId = fileId;
    }

    public void setFileMd5(String fileMd5) {
        this.FileMd5 = fileMd5;
    }

    public void setFileSize(double fileSize) {
        this.FileSize = fileSize;
    }

    public void setUrl(String url) {
        this.Url = url;
    }

    @Override
    public String toString() {
        return "{FileId='%s', FileMd5='%s', FileSize=%s, Url='%s'}".formatted(FileId, FileMd5, FileSize, Url);
    }
}

