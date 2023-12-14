package com.example.devicechart.model

data class HourMinute(@JvmField var hour: Int, @JvmField var minute: Int) {
    override fun toString(): String {
        return hour.toString() + ":" + minute
    }
}