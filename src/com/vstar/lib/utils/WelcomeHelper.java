package com.vstar.lib.utils;

import java.io.File;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.widget.ImageView;

import com.commonsware.cwac.task.AsyncTaskEx;
import com.vstar.lib.VstarApplication;
import com.vstar.lib.bean.BaseIndex.Welcome;
import com.vstar.lib.io.ImageFetcher;
import com.vstar.lib.R;

/**
 * 
 * <b>类名:</b> WelcomeHelper.java</br>
 * <b>说明:</b> 处理欢迎页的帮助类</br>
 * <b>创建日期:</b> 2013-4-7下午2:19:56</br>
 * <b>更新时间:</b> 2013-4-7下午2:19:56</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class WelcomeHelper {
    private static final Object TAG = L.makeLogTag(WelcomeHelper.class);
    private static final String INDEX_IMAGE_URL = "index_image_url";
    private static final String INDEX_IMAGE_LOCAL_PATH = "index_image_local_path";
    private SharedPreferences mPreferences;
    private Bitmap mImageBitmap;

    public WelcomeHelper() {
        mPreferences = VstarApplication.getInstance().getSettingPreferences();
    }

    public void setWelcomeIndexImage(ImageView iv) {
        String imagePath = mPreferences.getString(INDEX_IMAGE_LOCAL_PATH, null);
        if (!TextUtils.isEmpty(imagePath)) {
            mImageBitmap = BitmapFactory.decodeFile(imagePath);
        } else {
            mImageBitmap = BitmapFactory.decodeResource(iv.getResources(), R.drawable.ic_launcher);
        }

        iv.setImageBitmap(mImageBitmap);
    }

    public void recycleImage() {
        if (mImageBitmap != null && !mImageBitmap.isRecycled())
            mImageBitmap.recycle();
    }

    public void check(Welcome welcome) {
        if (welcome == null) {
            L.e(TAG, "Welcome is null");
            return;
        }
        String picUrl = welcome.pic;
        if (welcome.flag) {
            if (!TextUtils.isEmpty(picUrl)) {
                String imageUrl = mPreferences.getString(INDEX_IMAGE_URL, "");
                if (!imageUrl.equals(picUrl)) {
                    mPreferences.edit().putString(INDEX_IMAGE_URL, picUrl).commit();
                    new DownloadWelcomeImageTask().execute(picUrl);
                }
            }
        } else {
            mPreferences.edit().remove(INDEX_IMAGE_URL).commit();
            mPreferences.edit().remove(INDEX_IMAGE_LOCAL_PATH).commit();
        }

    }

    private class DownloadWelcomeImageTask extends AsyncTaskEx<String, Void, File> {

        @Override
        protected File doInBackground(String... arg0) {
            return ImageFetcher.downloadBitmapToFile(arg0[0], PathUtil.getImageDirectory());
        }

        @Override
        protected void onPostExecute(File result) {
            super.onPostExecute(result);
            L.i(TAG, "welcome image download success , path : " + result.getAbsolutePath());
            mPreferences.edit().putString(INDEX_IMAGE_LOCAL_PATH, result.getAbsolutePath())
                        .commit();
        }
    }
}
