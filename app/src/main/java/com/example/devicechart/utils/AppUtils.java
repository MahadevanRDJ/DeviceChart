package com.example.devicechart.utils;

import android.graphics.Color;
import android.graphics.Typeface;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.devicechart.model.Device;
import com.example.devicechart.model.HourMinute;
import com.example.devicechart.model.Usage;
import com.example.devicechart.utils.formatter.HourMinuteFormatter;
import com.example.devicechart.utils.formatter.UsagePlatformFormatter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarDataSet;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AppUtils {
    public static String DEVICE_JSON = "device.json";
    public static Device DEVICE;

    public static List<Usage> USAGE;
    public static String CHART_TYPE = "Day";
    public static void setRecycler(RecyclerView recycler) {
        int scrollPosition = 0;

        if (recycler.getLayoutManager() != null)
            scrollPosition = ((LinearLayoutManager) recycler
                    .getLayoutManager())
                    .findFirstVisibleItemPosition();
        recycler.setLayoutManager(new LinearLayoutManager(recycler.getContext()));
        recycler.scrollToPosition(scrollPosition);
    }

    public static void initBarChart(BarChart barChart) {
        //hiding the grey background of the chart, default false if not set
        barChart.setDrawGridBackground(false);
        //remove the bar shadow, default false if not set
        barChart.setDrawBarShadow(false);
        //remove border of the chart, default false if not set
        barChart.setDrawBorders(false);
        //remove grid lines


        //remove the description label text located at the lower right corner
        Description description = new Description();
        description.setEnabled(false);
        barChart.setDescription(description);

        //setting animation for y-axis, the bar will pop up from 0 to its value within the time we set
        barChart.animateY(1000);
        //setting animation for x-axis, the bar will pop up separately within the time we set
        barChart.animateX(1000);

        XAxis xAxis = barChart.getXAxis();
        //change the position of x-axis to the bottom
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //set the horizontal distance of the grid line
        xAxis.setGranularity(1f);
        //hiding the x-axis line, default true if not set
        xAxis.setDrawAxisLine(false);
        //set custom formatter for XAxis
        xAxis.setValueFormatter(new UsagePlatformFormatter());
        xAxis.setTextSize(11f);
        xAxis.setDrawGridLines(false);

        //set bar width
        barChart.getBarData().setBarWidth(.5F);

        YAxis leftAxis = barChart.getAxisLeft();
        //hiding the left y-axis line, default true if not set
        leftAxis.setDrawAxisLine(false);

        leftAxis.setTextSize(11f);
        leftAxis.setTypeface(Typeface.MONOSPACE);
        leftAxis.setAxisMinimum(0f);
        float max = USAGE.get(0).getDuration();
        System.out.println(max);
        leftAxis.setAxisMaximum(max);

        leftAxis.setTextSize(11f);
        leftAxis.setLabelCount(4);
        barChart.setHardwareAccelerationEnabled(true);
        leftAxis.enableGridDashedLine(25f, 10f, 0);
        barChart.getAxisRight().enableGridDashedLine(25f, 10f, 0);
        //set custom formatter for YAxis(Left)
        leftAxis.setValueFormatter(new HourMinuteFormatter());
        //right Axis disabled
        barChart.getAxisRight().setEnabled(false);
        //disable legend
        barChart.getLegend().setEnabled(false);
    }

    public static void initBarDataSet(BarDataSet barDataSet) {
        //Changing the color of the bar
        barDataSet.setColor(Color.parseColor("#304567"));
        //Setting the size of the form in the legend
        barDataSet.setFormSize(15f);
        //showing the value of the bar, default true if not set
        barDataSet.setDrawValues(false);

    }

    public static HourMinute convertDurationToHourMinute(int duration) {
        int hour = (int) duration / 3600;
        int minute = (int) duration % 60;
        return new HourMinute(hour, minute);
    }

    public static HourMinute totalTimeUsage() {
        AtomicInteger total = new AtomicInteger();
        USAGE.forEach(
                it -> total.addAndGet(it.getDuration())
        );
        return AppUtils.convertDurationToHourMinute(total.get());
    }
}
