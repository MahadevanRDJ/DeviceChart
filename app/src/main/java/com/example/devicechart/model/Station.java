package com.example.devicechart.model;

import com.google.gson.annotations.SerializedName;

public class Station {
    private String deviceId;
    private String name;
    private boolean isOnline;
    private String ifType;
    private int type;
    @SerializedName("ipAddr")
    private String ipAddress;

    public Station(String deviceId, String name, boolean isOnline, String ifType, int type, String ipAddress) {
        this.deviceId = deviceId;
        this.name = name;
        this.isOnline = isOnline;
        this.ifType = ifType;
        this.type = type;
        this.ipAddress = ipAddress;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getIfType() {
        return ifType;
    }

    public void setIfType(String ifType) {
        this.ifType = ifType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
