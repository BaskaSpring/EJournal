package com.baska.web.Payloads.DaemonPayload;

import java.util.ArrayList;

public class Computer {
    String name;
    ArrayList<Disc> discArrayList;

    public Computer(String name, ArrayList<Disc> discArrayList) {
        this.name = name;
        this.discArrayList = discArrayList;
    }

    public Computer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Disc> getDiscArrayList() {
        return discArrayList;
    }

    public void setDiscArrayList(ArrayList<Disc> discArrayList) {
        this.discArrayList = discArrayList;
    }
}
