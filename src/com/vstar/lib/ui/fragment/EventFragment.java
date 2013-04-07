package com.vstar.lib.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import de.greenrobot.event.EventBus;

/**
 * 
 * <b>类名:</b> EventFragment.java</br>
 * <b>说明:</b> 具备EventBus的Fragment</br>
 * <b>创建日期:</b> 2013-4-7下午2:14:50</br>
 * <b>更新时间:</b> 2013-4-7下午2:14:50</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class EventFragment extends Fragment {
    protected EventBus mEventBus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEventBus = EventBus.getDefault();
        mEventBus.register(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mEventBus.unregister(this);
    }
}
