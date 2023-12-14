package com.example.devicechart.model

import com.google.gson.annotations.SerializedName

data class Station(
    var deviceId: String,
    var name: String,
    var isOnline: Boolean,
    var ifType: String,
    var type: Int,
    @field:SerializedName(
        "ipAddr"
    ) var ipAddress: String
)