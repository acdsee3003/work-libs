package com.vstar.lib.task;

import android.view.View;

import com.commonsware.cwac.task.AsyncTaskEx;
import com.vstar.lib.utils.ViewUtil;

/**
 * 
 * <b>类名:</b> LoadingTask.java</br>
 * <b>说明:</b> 在读取的过程中显示指定View的AsyncTask</br>
 * <b>创建日期:</b> 2013-4-7下午2:13:04</br>
 * <b>更新时间:</b> 2013-4-7下午2:13:04</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public abstract class LoadingTask<Params, Progress, Result> extends
        AsyncTaskEx<Params, Progress, Result> {
    private View loading;

    public LoadingTask(View loading) {
        this.loading = loading;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        ViewUtil.setGone(loading, false);
    }

    protected void onPostExecute(Result result) {
        super.onPostExecute(result);
        ViewUtil.setGone(loading, true);
    };
}
