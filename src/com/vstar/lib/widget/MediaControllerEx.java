package com.vstar.lib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.MediaController;

/**
 * 
 * <b>类名:</b> MediaControllerEx.java</br>
 * <b>说明:</b> 扩展MediaController , 添加MediaController显示和隐藏的监听器</br>
 * <b>创建日期:</b> 2013-4-7下午2:20:53</br>
 * <b>更新时间:</b> 2013-4-7下午2:20:53</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class MediaControllerEx extends MediaController {
    private OnShowListener mOnShowListener;
    private OnHideListener mOnHideListener;

    public MediaControllerEx(Context context, boolean useFastForward) {
        super(context, useFastForward);
    }

    public MediaControllerEx(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MediaControllerEx(Context context) {
        super(context);
    }

    @Override
    public void show(int timeout) {
        super.show(timeout);
        if (mOnShowListener != null) {
            mOnShowListener.onShow();
        }
    }

    @Override
    public void hide() {
        super.hide();
        if (mOnHideListener != null) {
            mOnHideListener.onHide();
        }
    }

    public void setOnShowListener(OnShowListener listener) {
        mOnShowListener = listener;
    }

    public void setOnHideListener(OnHideListener listener) {
        mOnHideListener = listener;
    }

    public interface OnShowListener {
        void onShow();
    }

    public interface OnHideListener {
        void onHide();
    }
}
