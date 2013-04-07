package com.vstar.lib;

import org.apache.http.params.HttpProtocolParams;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.loopj.android.http.AsyncHttpClient;

public class VstarApplication extends Application {
    private static VstarApplication instance;
    private static AsyncHttpClient mAsyncHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static VstarApplication getInstance() {
        return instance;
    }

    public SharedPreferences getSettingPreferences() {
        return getSharedPreferences("setting", Context.MODE_PRIVATE);
    }

    public AsyncHttpClient getAsyncHttpClient() {
        if (mAsyncHttpClient == null) {
            mAsyncHttpClient = new AsyncHttpClient();
            mAsyncHttpClient.setTimeout(30 * 1000);
            HttpProtocolParams.setUseExpectContinue(mAsyncHttpClient.getHttpClient().getParams(),
                                                    false);
        }
        return mAsyncHttpClient;
    }

}
