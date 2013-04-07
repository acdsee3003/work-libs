package com.vstar.lib.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import de.greenrobot.event.EventBus;

/**
 * 
 * <b>类名:</b> EventListFragment.java</br>
 * <b>说明:</b> 具备EventBus的ListFragment</br>
 * <b>创建日期:</b> 2013-4-7下午2:15:04</br>
 * <b>更新时间:</b> 2013-4-7下午2:15:04</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class EventListFragment extends ListFragment {
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
