package com.vstar.lib.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;

import com.vstarlib.R;

/**
 * 
 * <b>类名:</b> ConfirmDialogFragment.java</br>
 * <b>说明:</b> 确定弹出框</br>
 * <b>创建日期:</b> 2013-4-7下午2:14:16</br>
 * <b>更新时间:</b> 2013-4-7下午2:14:16</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class ConfirmDialogFragment extends DialogFragment {
    private static final String EXTRA_MSG = "msg";
    private String mPositiveText;
    private String mNegativeText;
    private DialogInterface.OnClickListener mPositiveListener;
    private DialogInterface.OnClickListener mNegativeListener;

    public static ConfirmDialogFragment instance(String message) {
        ConfirmDialogFragment fragment = new ConfirmDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_MSG, message);
        fragment.setArguments(bundle);
        return fragment;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.tip);
        builder.setMessage(getArguments().getString(EXTRA_MSG));
        if (!TextUtils.isEmpty(mPositiveText))
            builder.setPositiveButton(mPositiveText, mPositiveListener);
        if (!TextUtils.isEmpty(mNegativeText))
            builder.setNegativeButton(mNegativeText, mNegativeListener);
        return builder.create();
    }

    public void setPositiveButton(String text, DialogInterface.OnClickListener listener) {
        mPositiveText = text;
        mPositiveListener = listener;
    }

    public void setNegativeButton(String text, DialogInterface.OnClickListener listener) {
        mNegativeText = text;
        mNegativeListener = listener;
    }
}
