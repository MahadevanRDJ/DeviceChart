package com.example.devicechart.model

data class TimeUsage(var type: String, var totalUsage: Int, @JvmField var usage: ArrayList<Usage>)