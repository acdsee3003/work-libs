package com.vstar.lib.task;

import java.sql.SQLException;

import android.app.Activity;

import com.commonsware.cwac.task.AsyncTaskEx;
import com.vstar.lib.db.FavoriteDBHelper;
import com.vstar.lib.db.dto.Favorite;
import com.vstar.lib.utils.ToastUtil;
import com.vstarlib.R;

/**
 * 
 * <b>类名:</b> SaveFavouriteTask.java</br>
 * <b>说明:</b> 保存"收藏"的异步任务</br>
 * <b>创建日期:</b> 2013-4-7下午2:13:29</br>
 * <b>更新时间:</b> 2013-4-7下午2:13:29</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class SaveFavouriteTask extends AsyncTaskEx<Favorite, Void, Boolean> {
    private Activity mActivity;

    public SaveFavouriteTask(Activity activity) {
        mActivity = activity;
    }

    @Override
    protected Boolean doInBackground(Favorite... favorites) {
        FavoriteDBHelper dBHelper = new FavoriteDBHelper(mActivity);
        boolean success = false;
        try {
            for (Favorite f : favorites)
                dBHelper.getDAO().create(f);
            success = true;
        } catch (SQLException e) {
            success = false;
        }
        return success;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        String msg = result ? mActivity.getString(R.string.favorite_success)
                           : mActivity.getString(R.string.favorite_had);
        ToastUtil.showShortTimeMsg(msg);
    }
}