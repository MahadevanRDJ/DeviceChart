package com.example.devicechart.model;


import com.example.devicechart.utils.AppUtils;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Device {
    private String profileId;
    private String name;
    private String avatarUrl;
    private boolean isBlocked;
    private int webCount;
    private int appCount;
    private String timeLimitStatus;
    @SerializedName("speedtest")
    private ArrayList<SpeedTest> speedTest;
    private List<TimeUsage> timeUsage;
    private ArrayList<Station> stations;
    private boolean isScheduledBlocked;

    public Device(String profileId, String name, String avatarUrl, boolean isBlocked, int webCount, int appCount, String timeLimitStatus, ArrayList<SpeedTest> speedTest, List<TimeUsage> timeUsage, ArrayList<Station> stations, boolean isScheduledBlocked) {
        this.profileId = profileId;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.isBlocked = isBlocked;
        this.webCount = webCount;
        this.appCount = appCount;
        this.timeLimitStatus = timeLimitStatus;
        this.speedTest = speedTest;
        this.timeUsage = timeUsage;
        this.stations = stations;
        this.isScheduledBlocked = isScheduledBlocked;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public int getWebCount() {
        return webCount;
    }

    public void setWebCount(int webCount) {
        this.webCount = webCount;
    }

    public int getAppCount() {
        return appCount;
    }

    public void setAppCount(int appCount) {
        this.appCount = appCount;
    }

    public String getTimeLimitStatus() {
        return timeLimitStatus;
    }

    public void setTimeLimitStatus(String timeLimitStatus) {
        this.timeLimitStatus = timeLimitStatus;
    }

    public ArrayList<SpeedTest> getSpeedTest() {
        return speedTest;
    }

    public void setSpeedTest(ArrayList<SpeedTest> speedtest) {
        this.speedTest = speedtest;
    }

    public List<TimeUsage> getTimeUsage() {
        return timeUsage;
    }

    public void setTimeUsage(List<TimeUsage> timeUsage) {
        this.timeUsage = timeUsage;
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public void setStations(ArrayList<Station> stations) {
        this.stations = stations;
    }

    public boolean isScheduledBlocked() {
        return isScheduledBlocked;
    }

    public void setScheduledBlocked(boolean scheduledBlocked) {
        isScheduledBlocked = scheduledBlocked;
    }

    @Override
    public String toString() {
        return "Device{" + "profileId='" + profileId + '\'' + ", name='" + name + '\'' + ", avatarUrl='" + avatarUrl + '\'' + ", isBlocked=" + isBlocked + ", webCount=" + webCount + ", appCount=" + appCount + ", timeLimitStatus='" + timeLimitStatus + '\'' + ", speedTest=" + speedTest + ", timeUsage=" + timeUsage + ", stations=" + stations + ", isScheduledBlocked=" + isScheduledBlocked + '}';
    }

    public List<String> getUsagePlatforms() {
        List<String> platformList = new ArrayList<>();
        AppUtils.USAGE.forEach(usage -> platformList.add(usage.getName()));
        return platformList;
    }

    public List<HourMinute> getHourMinuteList() {
        List<HourMinute> hourMinuteList = new ArrayList<>();
        AppUtils.USAGE.forEach(usage -> hourMinuteList.add(usage.getHourMinute()));
        return hourMinuteList;
    }
}

