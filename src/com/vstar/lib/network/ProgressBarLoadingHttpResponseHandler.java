package com.vstar.lib.network;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import android.widget.ProgressBar;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.vstar.lib.utils.GsonUtil;
import com.vstar.lib.utils.ToastUtil;
import com.vstar.lib.utils.ViewUtil;
import com.vstar.lib.R;

public class ProgressBarLoadingHttpResponseHandler<T> extends AsyncHttpResponseHandler {
    private ProgressBar mProgressBar;
    private Type typeOfT;

    public ProgressBarLoadingHttpResponseHandler(ProgressBar progressBar) {
        mProgressBar = progressBar;
        typeOfT = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mProgressBar != null) {
            ViewUtil.setGone(mProgressBar, true);
        }
    }

    @Override
    public void onFinish() {
        super.onFinish();
        if (mProgressBar != null) {
            ViewUtil.setGone(mProgressBar, false);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onSuccess(String arg0) {
        super.onSuccess(arg0);
        onSuccess((T) GsonUtil.parse(arg0, typeOfT));
    }

    public void onSuccess(T t) {

    }

    protected Type getTypeOfT() {
        return typeOfT;
    }

    // @Override
    // public void onFailure(Throwable arg0) {
    // super.onFailure(arg0);
    // ToastUtil.showShortTimeMsg(R.string.error_load_failure);
    // }

    @Override
    public void onFailure(Throwable arg0, String arg1) {
        super.onFailure(arg0, arg1);
        ToastUtil.showShortTimeMsg(R.string.error_load_failure);
    }
}
