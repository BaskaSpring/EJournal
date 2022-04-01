package com.baska.web.Payload;

import java.time.Instant;

public class _Files {

    String fileName;
    Instant createDate;

    public _Files(String fileName, Instant createDate) {
        this.fileName = fileName;
        this.createDate = createDate;
    }

    public _Files() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }
}
