package com.baska.web.Payload;

import java.util.List;

public class PathPayload {

    List<String> path;
    Integer refreshTime;

    public PathPayload(List<String> path, Integer refreshTime) {
        this.path = path;
        this.refreshTime = refreshTime;
    }

    public PathPayload() {
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public Integer getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(Integer refreshTime) {
        this.refreshTime = refreshTime;
    }
}
