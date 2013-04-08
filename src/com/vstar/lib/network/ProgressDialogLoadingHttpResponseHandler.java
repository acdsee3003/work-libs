package com.vstar.lib.network;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.text.TextUtils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.vstar.lib.VstarApplication;
import com.vstar.lib.utils.GsonUtil;
import com.vstar.lib.utils.ToastUtil;
import com.vstar.lib.R;

public class ProgressDialogLoadingHttpResponseHandler<T> extends AsyncHttpResponseHandler implements
                                                                                         OnCancelListener {
    private ProgressDialog mProgressDialog;
    private Context mContext;
    private AsyncHttpClient mClient;

    private Type typeOfT;

    public ProgressDialogLoadingHttpResponseHandler(Context c, String loadingMsg) {
        mContext = c;
        if (!TextUtils.isEmpty(loadingMsg)) {
            mProgressDialog = new ProgressDialog(c);
            mProgressDialog.setTitle(R.string.tip);
            mProgressDialog.setMessage(loadingMsg);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setOnCancelListener(this);
        }
        typeOfT = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        mClient = VstarApplication.getInstance().getAsyncHttpClient();
    }

    @Override
    public void onStart() {
        super.onStart();
        mProgressDialog.show();
    }

    @Override
    public void onFinish() {
        super.onFinish();
        mProgressDialog.dismiss();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onSuccess(String arg0) {
        super.onSuccess(arg0);
        onSuccess((T) GsonUtil.parse(arg0, typeOfT));
    }

    public void onSuccess(T t) {

    }

    @Override
    public void onFailure(Throwable arg0) {
        super.onFailure(arg0);
        ToastUtil.showShortTimeMsg(R.string.error_load_failure);
    }

    @Override
    public void onFailure(Throwable arg0, String arg1) {
        super.onFailure(arg0, arg1);
        ToastUtil.showShortTimeMsg(R.string.error_load_failure);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        mClient.cancelRequests(mContext, true);
    }
}
