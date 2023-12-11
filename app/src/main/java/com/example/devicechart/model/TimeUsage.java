package com.example.devicechart.model;

import com.example.devicechart.utils.AppUtils;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class TimeUsage {
    private String type;
    private int totalUsage;
    private ArrayList<Usage> usage;

    public TimeUsage(String type, int totalUsage, ArrayList<Usage> usage) {
        this.type = type;
        this.totalUsage = totalUsage;
        this.usage = usage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotalUsage() {
        return totalUsage;
    }

    public void setTotalUsage(int totalUsage) {
        this.totalUsage = totalUsage;
    }

    public ArrayList<Usage> getUsage() {
        return usage;
    }

    public void setUsage(ArrayList<Usage> usage) {
        this.usage = usage;
    }

}
