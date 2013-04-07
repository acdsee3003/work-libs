package com.vstar.lib.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import de.greenrobot.event.EventBus;

/**
 * 
 * <b>类名:</b> EventFragmentActivity.java</br>
 * <b>说明:</b> 具备EventBus的Activity</br>
 * <b>创建日期:</b> 2013-4-7下午2:13:55</br>
 * <b>更新时间:</b> 2013-4-7下午2:13:55</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class EventFragmentActivity extends FragmentActivity {
    protected EventBus mEventBus;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        mEventBus = EventBus.getDefault();
        mEventBus.register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mEventBus.unregister(this);
    }
}
