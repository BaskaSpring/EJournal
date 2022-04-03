package com.baska.web.Payloads.DaemonPayload;

public class _Files {

    String fileName;
    Long createDate;

    public _Files(String fileName, Long createDate) {
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

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }
}
