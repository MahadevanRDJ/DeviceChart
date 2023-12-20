package com.example.devicechart;

import static com.example.devicechart.utils.AppUtils.CHART_TYPE;
import static com.example.devicechart.utils.AppUtils.DEVICE;
import static com.example.devicechart.utils.AppUtils.DEVICE_JSON;
import static com.example.devicechart.utils.AppUtils.USAGE;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.devicechart.databinding.ActivityMainBinding;
import com.example.devicechart.model.HourMinute;
import com.example.devicechart.model.SpeedTest;
import com.example.devicechart.utils.AppUtils;
import com.example.devicechart.utils.JsonUtil;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import io.getstream.avatarview.AvatarView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mainBinding;
    private TextView profileName;
    private TextView profileStatus;
    private BarChart deviceBarChart;
    private AvatarView profileAvatar;
    private TextView dayText;
    private TextView hourText;
    private TextView minuteText;
    private ImageView downloadImage;
    private ImageView uploadImage;
    private TextView downloadSpeedText;
    private TextView uploadSpeedTest;
    private TextView dayChart;
    private TextView weekChart;
    private TextView monthChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initResource();
        initView();
        setProfileSection();
        showBarChart();
    }

    @SuppressLint("SetTextI18n")
    private void setTimeAndSpeedUsage() {
        HourMinute data = AppUtils.totalTimeUsage();
        hourText.setText(String.valueOf(data.getHour()));
        minuteText.setText(String.valueOf(data.getMinute()));
        SpeedTest speedTest = DEVICE.getSpeedTest().get(DEVICE.getSpeedTest().size() - 1);
        downloadSpeedText.setText(speedTest.getDownloadRate() + "");
        uploadSpeedTest.setText(speedTest.getUploadRate() + "");

    }

    private void initResource() {
        String string = JsonUtil.getInstance().loadJSONFromAsset(this, DEVICE_JSON);
        JsonUtil.getInstance().serialize(string, DEVICE);
        assert DEVICE != null;
        System.out.println(DEVICE);
    }

    @SuppressLint("ResourceAsColor")
    private void initView() {
        deviceBarChart = mainBinding.deviceBarChart;
        profileStatus = mainBinding.statusText;
        profileName = mainBinding.profileNameText;
        profileAvatar = mainBinding.profile;
        downloadImage = mainBinding.timeAndSpeedUsageLayout.downloadImage;
        uploadImage = mainBinding.timeAndSpeedUsageLayout.uploadImage;
        dayText = mainBinding.timeAndSpeedUsageLayout.dayText;
        hourText = mainBinding.timeAndSpeedUsageLayout.hourText;
        minuteText = mainBinding.timeAndSpeedUsageLayout.minuteText;
        downloadSpeedText = mainBinding.timeAndSpeedUsageLayout.downloadSpeedText;
        uploadSpeedTest = mainBinding.timeAndSpeedUsageLayout.uploadSpeedText;
        dayChart = mainBinding.dayChart;
        weekChart = mainBinding.weekChart;
        monthChart = mainBinding.monthChart;

        downloadImage.setColorFilter(Color.parseColor("#AF17C9"));
        uploadImage.setColorFilter(Color.parseColor("#36D83D"));



        dayChart.setOnClickListener(
                (l) -> {
                    l.setBackgroundColor(getColor(R.color.light_blue));
                    weekChart.setBackgroundColor(Color.parseColor("#00000000"));
                    monthChart.setBackgroundColor(Color.parseColor("#00000000"));
                    CHART_TYPE = "Day";
                    showBarChart();
                }
        );
        weekChart.setOnClickListener(
                (l) -> {
                    l.setBackgroundColor(getColor(R.color.light_blue));
                    dayChart.setBackgroundColor(Color.parseColor("#00000000"));
                    monthChart.setBackgroundColor(Color.parseColor("#00000000"));
                    CHART_TYPE = "Week";
                    showBarChart();
                }
        );
        monthChart.setOnClickListener(
                (l) -> {
                    l.setBackgroundColor(getColor(R.color.light_blue));
                    weekChart.setBackgroundColor(Color.parseColor("#00000000"));
                    dayChart.setBackgroundColor(Color.parseColor("#00000000"));
                    CHART_TYPE = "Month";
                    showBarChart();
                }
        );
    }

    private void setProfileSection() {
        loadProfileImageFromURL(profileAvatar, DEVICE.getAvatarUrl());
        profileName.setText(DEVICE.getName());
        setOnlineOrOffline(profileAvatar);

    }

    private void showBarChart() {
        deviceBarChart.clear();

        ArrayList<BarEntry> entries = new ArrayList<>();

        switch (CHART_TYPE) {
            case "Month": {
                USAGE = DEVICE.getTimeUsage().get(2).getUsage();
                break;
            }
            case "Week": {
                USAGE = DEVICE.getTimeUsage().get(1).getUsage();
                break;
            }
            default:
                USAGE = DEVICE.getTimeUsage().get(0).getUsage();
                break;
        }

        setTimeAndSpeedUsage();
        dayText.setText(CHART_TYPE);
        for (int i = 0; i < USAGE.size(); i++)
            entries.add(new BarEntry(i, USAGE.get(i).getDuration()));
        BarDataSet barDataSet = new BarDataSet(entries, "");
        AppUtils.initBarDataSet(barDataSet);
        BarData barData = new BarData(barDataSet);
        deviceBarChart.setData(barData);
        AppUtils.initBarChart(deviceBarChart);
        deviceBarChart.invalidate();
    }

    private void setOnlineOrOffline(AvatarView profileAvatar) {
        if (DEVICE.isBlocked()) {
            profileAvatar.setAvatarBorderColor(Color.YELLOW);
            profileAvatar.setIndicatorColor(Color.YELLOW);
            profileStatus.setText(R.string.paused_status);
            profileStatus.setTextColor(Color.YELLOW);
        }
    }


    private void loadProfileImageFromURL(ImageView imageView, final String URL) {
        Glide.with(this)
                .asDrawable()
                .circleCrop()
                .load(URL)
                .apply(
                        new RequestOptions()
                                .placeholder(R.drawable.person_48)
                                .error(R.drawable.person_48))
                .into(imageView);
    }
}