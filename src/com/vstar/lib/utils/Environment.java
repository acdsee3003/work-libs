package com.vstar.lib.utils;

import com.vstar.lib.VstarApplication;

public class Environment {

    public static int getScreenHeight() {
        return VstarApplication.getInstance().getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth() {
        return VstarApplication.getInstance().getResources().getDisplayMetrics().widthPixels;
    }
}
