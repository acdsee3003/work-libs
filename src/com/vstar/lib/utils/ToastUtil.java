package com.vstar.lib.utils;

import android.widget.Toast;

import com.vstar.lib.VstarApplication;

public class ToastUtil {
    private static VstarApplication context = VstarApplication.getInstance();

    public static void showLongTimeMsg(CharSequence text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static void showLongTimeMsg(int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }

    public static void showShortTimeMsg(CharSequence text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showShortTimeMsg(int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }
}
