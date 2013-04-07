package com.vstar.lib.network;

import org.apache.http.params.HttpProtocolParams;

import android.content.Context;
import android.widget.ProgressBar;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.vstar.lib.mock.MockData;
import com.vstar.lib.utils.L;

import de.greenrobot.event.EventBus;

public class JsonResponse2Bean {
    public static final Object TAG = L.makeLogTag(JsonResponse2Bean.class);
    protected static EventBus mEventBus = EventBus.getDefault();
    protected static AsyncHttpClient mClient;

    static {
        mClient = new AsyncHttpClient();
        mClient.setTimeout(30 * 1000);
        HttpProtocolParams.setUseExpectContinue(mClient.getHttpClient().getParams(), false);
    }

    public static void toIndex(ProgressBar progressBar) {
        // get("http://index.net", new
        // EventBusHttpResponseHandler<Index>(progressBar));
        mEventBus.post(MockData.index());
    }

    public static void get(String url, AsyncHttpResponseHandler handler) {
        mClient.get(url, handler);
    }

    public static void get(Context context, String url, AsyncHttpResponseHandler handler) {
        mClient.get(context, url, handler);
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler handler) {
        mClient.get(url, params, handler);
    }

    public static void get(Context context, String url, RequestParams params,
                           AsyncHttpResponseHandler handler) {
        mClient.get(context, url, params, handler);
    }

    public static void post(String url, AsyncHttpResponseHandler handler) {
        mClient.post(url, handler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler handler) {
        mClient.post(url, params, handler);
    }

    public static void post(Context context, String url, RequestParams params,
                            AsyncHttpResponseHandler handler) {
        mClient.post(context, url, params, handler);
    }

    static class EventBusHttpResponseHandler<T> extends ProgressBarLoadingHttpResponseHandler<T> {
        public EventBusHttpResponseHandler(ProgressBar progressBar) {
            super(progressBar);
        }

        @Override
        public void onSuccess(T t) {
            super.onSuccess(t);
            if (t == null) {
                L.e(TAG, getTypeOfT().toString() + "is null !");
                return;
            }
            mEventBus.post(t);
        }
    }
}
