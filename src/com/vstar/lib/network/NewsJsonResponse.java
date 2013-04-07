package com.vstar.lib.network;

import android.view.View;

import com.vstar.lib.mock.MockData;

public class NewsJsonResponse extends JsonResponse2Bean {
    public static void toNewsList(String url, View loading) {
        // get("http://index.net", new
        // EventBusHttpResponseHandler<Index>(progressBar));
        mEventBus.post(MockData.newsList());
    }
}
