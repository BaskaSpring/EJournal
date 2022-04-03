package com.baska.web.Payloads.DaemonPayload;

public class Disc {

    String name;
    String freeSpace;
    String totalSpace;

    public Disc(String name, String freeSpace, String totalSpace) {
        this.name = name;
        this.freeSpace = freeSpace;
        this.totalSpace = totalSpace;
    }

    public Disc() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(String freeSpace) {
        this.freeSpace = freeSpace;
    }

    public String getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(String totalSpace) {
        this.totalSpace = totalSpace;
    }
}
