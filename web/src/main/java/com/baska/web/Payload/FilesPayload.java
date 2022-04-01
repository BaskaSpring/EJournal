package com.baska.web.Payload;

import java.util.List;

public class FilesPayload {
    List<_Files> filesList;
    String serverName;

    public FilesPayload(List<_Files> filesList, String serverName) {
        this.filesList = filesList;
        this.serverName = serverName;
    }

    public FilesPayload() {
    }

    public List<_Files> getFilesList() {
        return filesList;
    }

    public void setFilesList(List<_Files> filesList) {
        this.filesList = filesList;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
