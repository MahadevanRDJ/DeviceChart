package com.example.devicechart.model;

public class HourMinute {
    private int hour;
    private int minute;

    public HourMinute(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public String toString() {
        return this.hour + ":" + this.minute;
    }
}
