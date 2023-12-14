package com.example.devicechart.model

data class SpeedTest(
    @JvmField var downloadRate: Int,
    @JvmField var uploadRate: Int,
    var phyLinkRate: Int,
    var hitCpuLimit: Int
)