package com.vstar.lib.utils;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 
 * <b>类名:</b> ViewUtil.java</br>
 * <b>说明:</b> View的工具类</br>
 * <b>创建日期:</b> 2013-4-7下午2:16:42</br>
 * <b>更新时间:</b> 2013-4-7下午2:16:42</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class ViewUtil {

    private static final String TAG = L.makeLogTag(ViewUtil.class);

    @SuppressWarnings("unchecked")
    public static <T extends View> T findView(Activity a, Integer id) {
        if (a == null) {
            L.e(TAG, "Activity is null !");
            return null;
        }
        return (T) a.findViewById(id);
    }

    @SuppressWarnings("unchecked")
    public static <T extends View> T findView(View view, Integer id) {
        if (view == null) {
            L.e(TAG, "view is null !");
            return null;
        }
        return (T) view.findViewById(id);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Fragment> T findFragment(FragmentManager fm, Integer id) {
        if (fm == null) {
            L.e(TAG, "view is null !");
            return null;
        }
        return (T) fm.findFragmentById(id);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Fragment> T findFragment(FragmentManager fm, String tag) {
        if (fm == null) {
            L.e(TAG, "view is null !");
            return null;
        }
        return (T) fm.findFragmentByTag(tag);
    }

    public static void setGone(final View view, final boolean gone) {
        if (view == null) {
            L.e(TAG, "view is null !");
            return;
        }
        final int current = view.getVisibility();
        if (gone && current != GONE)
            view.setVisibility(GONE);
        else if (!gone && current != VISIBLE)
            view.setVisibility(VISIBLE);
    }

    public static void setInvisible(final View view, final boolean invisible) {
        if (view == null) {
            L.e(TAG, "view is null !");
            return;
        }
        final int current = view.getVisibility();
        if (invisible && current != INVISIBLE)
            view.setVisibility(INVISIBLE);
        else if (!invisible && current != VISIBLE)
            view.setVisibility(VISIBLE);
    }

    public static void setOnClick(View view, OnClickListener listener) {
        if (view != null) {
            view.setOnClickListener(listener);
        }
    }

    public static void setEnable(View view, boolean enable) {
        if (view != null) {
            view.setEnabled(enable);
        }
    }

    public static void setText(TextView view, String text) {
        if (view != null) {
            view.setText(text);
        }
    }

    public static void setText(TextView view, Integer resId) {
        if (view != null && resId != null) {
            view.setText(resId);
        }
    }
}
