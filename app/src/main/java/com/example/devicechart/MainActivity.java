package com.example.devicechart;

import static com.example.devicechart.utils.AppUtils.CHART_TYPE;
import static com.example.devicechart.utils.AppUtils.DEVICE;
import static com.example.devicechart.utils.AppUtils.DEVICE_JSON;
import static com.example.devicechart.utils.AppUtils.USAGE;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
        setSumma();
    }

    private void setSumma() {

        // Get a string that can be spanned with an ImageSpan and a ClickableSpan.
        SpannableString ss = new SpannableString("Some text ");
        // This will be the image. I use a resource here, but there are constructors that can
        // accept a Drawable. See BitmapDrawable at https://developer.android.com/reference/android/graphics/drawable/BitmapDrawable#BitmapDrawable(android.content.res.Resources,%20android.graphics.Bitmap)

        ImageSpan imgSpan = new ImageSpan(this, R.drawable.person_48, ImageSpan.ALIGN_CENTER);
        ClickableSpan cs = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(MainActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();
            }
        };

        // The image will overlay the last blank in the text.
        ss.setSpan(imgSpan, ss.length() - 1, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // The clickable span will overlay the image from the ImageSpan.
        ss.setSpan(cs, ss.length() - 1, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // Just a TextView.
        TextView myView = new TextView(this);
        myView.setText(ss);
        // Prevent the ImageSpan from showing a highlight on click.
        myView.setHighlightColor(getResources().getColor(android.R.color.transparent));
        // Make sure the TextView can be clicked.
        myView.setMovementMethod(LinkMovementMethod.getInstance());

    }

    @SuppressLint("SetTextI18n")
    private void setTimeAndSpeedUsage() {
        HourMinute data = AppUtils.totalTimeUsage();
        hourText.setText(String.valueOf(data.hour));
        minuteText.setText(String.valueOf(data.minute));
        SpeedTest speedTest = DEVICE.speedTest.get(DEVICE.speedTest.size() - 1);
        downloadSpeedText.setText(speedTest.downloadRate + "");
        uploadSpeedTest.setText(speedTest.uploadRate + "");

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
        loadProfileImageFromURL(profileAvatar, DEVICE.avatarUrl);
        profileName.setText(DEVICE.name);
        setOnlineOrOffline(profileAvatar);

    }

    private void showBarChart() {
        deviceBarChart.clear();

        ArrayList<BarEntry> entries = new ArrayList<>();

        switch (CHART_TYPE) {
            case "Month": {
                USAGE = DEVICE.timeUsage.get(2).usage;
                break;
            }
            case "Week": {
                USAGE = DEVICE.timeUsage.get(1).usage;
                break;
            }
            default:
                USAGE = DEVICE.timeUsage.get(0).usage;
                break;
        }

        setTimeAndSpeedUsage();
        dayText.setText(CHART_TYPE);
        for (int i = 0; i < USAGE.size(); i++)
            entries.add(new BarEntry(i, USAGE.get(i).duration));
        BarDataSet barDataSet = new BarDataSet(entries, "");
        AppUtils.initBarDataSet(barDataSet);
        BarData barData = new BarData(barDataSet);
        deviceBarChart.setData(barData);
        AppUtils.initBarChart(deviceBarChart);
        deviceBarChart.invalidate();
    }

    private void setOnlineOrOffline(AvatarView profileAvatar) {
        if (DEVICE.isBlocked) {
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