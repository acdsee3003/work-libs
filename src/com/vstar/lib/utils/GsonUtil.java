package com.vstar.lib.utils;

import java.lang.reflect.Type;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

import com.google.gson.Gson;

public class GsonUtil {
    private static final String TAG = L.makeLogTag(GsonUtil.class);
    private static Gson gson = new Gson();

    public static <T> T parse(String json, Class<T> t) {
        return isJson(json) ? gson.fromJson(json, t) : null;
    }

    public static <T> T parse(String json, Type typeOfT) {
        if (isJson(json)) {
            return gson.fromJson(json, typeOfT);
        } else
            return null;
    }

    private static boolean isJson(String json) {
        if (TextUtils.isEmpty(json)) {
            L.e(TAG, "isJson  json is invalid");
            return false;
        }
        try {
            new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static <T> T parse(JSONObject jsonObject, Class<T> t) {
        return jsonObject == null ? null : gson.fromJson(jsonObject.toString(), t);
    }

    public static <T> T parse(JSONObject jsonObject, Type typeOfT) {
        T t = null;
        if (jsonObject != null) {
            t = gson.fromJson(jsonObject.toString(), typeOfT);
        }
        return t;
    }
}
