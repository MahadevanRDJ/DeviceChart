package com.example.devicechart.utils;

import android.content.Context;

import com.example.devicechart.model.Device;
import com.google.gson.Gson;

import java.io.InputStream;

public class JsonUtil {
    private static JsonUtil jsonUtil = null;

    public static JsonUtil getInstance() {
        if (jsonUtil == null) {
            jsonUtil = new JsonUtil();
        }
        return jsonUtil;
    }

    public String loadJSONFromAsset(Context context, String fileName) {
        String json = null;

        try {
            InputStream inputStream = context.getAssets().open(fileName);
            int size = inputStream.available();
            byte[] bytes = new byte[size];
            inputStream.read(bytes);
            inputStream.close();
            json = new String(bytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public void serialize(String string, Device device) {
        AppUtils.DEVICE = new Gson().fromJson(string, Device.class);

    }
}
