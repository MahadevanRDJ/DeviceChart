package com.example.devicechart.model;

import com.example.devicechart.utils.AppUtils;

public class Usage {
    private String name;
    private int duration;
    private HourMinute hourMinute;

    public Usage() {}
    public Usage(String name, int duration) {
        this.name = name;
        this.duration = duration;
        hourMinute = this.getHourMinute();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public HourMinute getHourMinute() {
        return AppUtils.convertDurationToHourMinute(duration);
    }


    @Override
    public String toString() {
        return "Usage{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", hourMinute=" + hourMinute +
                '}';
    }
}
