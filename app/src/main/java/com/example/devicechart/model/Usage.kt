package com.example.devicechart.model

import com.example.devicechart.utils.AppUtils

class Usage {
    var name: String? = null
    @JvmField
    var duration = 0
    var hourMinute: HourMinute? = null

    constructor(name: String?, duration: Int) {
        this.name = name
        this.duration = duration
        hourMinute = calculateHourMinute()
    }

    fun calculateHourMinute(): HourMinute {
        return AppUtils.convertDurationToHourMinute(duration)
    }

}