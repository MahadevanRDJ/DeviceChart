package com.example.devicechart.utils.formatter;

import com.example.devicechart.utils.AppUtils;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.List;


public class UsagePlatformFormatter extends ValueFormatter {
    private List<String> usages;

    private final static int THRESHOLD = 13;

    public UsagePlatformFormatter() {
        this.usages = AppUtils.DEVICE.getUsagePlatforms();
    }

    @Override
    public String getAxisLabel(float value, AxisBase axis) {
        String data = usages.get((int) value);
        int n = usages.size();
        if (n > 4) {
            int clip = (n / 3) - 1;
            if (data.length() > 10)
                data = new StringBuilder(data.substring(0, THRESHOLD - clip)).replace(10, THRESHOLD, "...").toString();
        }
        return data;
    }

}
