package com.example.devicechart.model

import com.example.devicechart.utils.AppUtils
import com.google.gson.annotations.SerializedName
import java.util.function.Consumer

data class Device(
    var profileId: String,
    @JvmField var name: String,
    @JvmField var avatarUrl: String,
    @JvmField var isBlocked: Boolean,
    var webCount: Int,
    var appCount: Int,
    var timeLimitStatus: String,
    @JvmField @field:SerializedName(
        "speedtest"
    ) var speedTest: ArrayList<SpeedTest>,
    @JvmField var timeUsage: List<TimeUsage>,
    var stations: ArrayList<Station>,
    var isScheduledBlocked: Boolean
) {


    val usagePlatforms: List<String>
        get() {
            val platformList: MutableList<String> = ArrayList()
            AppUtils.USAGE.forEach(Consumer { usage: Usage -> usage.name?.let { platformList.add(it) } })
            return platformList
        }
    val hourMinuteList: List<HourMinute>
        get() {
            val hourMinuteList: MutableList<HourMinute> = ArrayList()
            AppUtils.USAGE.forEach(Consumer { usage: Usage -> usage.hourMinute?.let {
                hourMinuteList.add(
                    it
                )
            } })
            return hourMinuteList
        }
}