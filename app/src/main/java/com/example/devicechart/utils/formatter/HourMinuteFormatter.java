package com.example.devicechart.utils.formatter;

import com.example.devicechart.model.HourMinute;
import com.example.devicechart.utils.AppUtils;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

public class HourMinuteFormatter extends ValueFormatter {
    public HourMinuteFormatter() {
    }
    @Override
    public String getAxisLabel(float value, AxisBase axis) {
        HourMinute data = AppUtils.convertDurationToHourMinute((int) (value));
        return (value > 0) ? data.toString() : "0";
    }
}
