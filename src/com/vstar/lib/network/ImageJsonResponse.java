package com.vstar.lib.network;

import android.view.View;

import com.vstar.lib.mock.MockData;

public class ImageJsonResponse extends JsonResponse2Bean {
    public static void toImageList(String url, View loading) {
        // get("http://index.net", new
        // EventBusHttpResponseHandler<Index>(progressBar));
        mEventBus.post(MockData.imageList());
    }
}
