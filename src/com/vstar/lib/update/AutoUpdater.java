package com.vstar.lib.update;

import java.io.File;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;

import com.vstar.lib.VstarApplication;
import com.vstar.lib.network.ProgressDialogLoadingHttpResponseHandler;
import com.vstar.lib.ui.fragment.ConfirmDialogFragment;
import com.vstar.lib.ui.fragment.DownloadDialogFragment;
import com.vstar.lib.ui.fragment.DownloadDialogFragment.OnDownloadFinishListener;
import com.vstar.lib.utils.PathUtil;
import com.vstar.lib.R;

/**
 * 
 * <b>类名:</b> AutoUpdater.java</br>
 * <b>说明:</b> 自动更新管理器</br>
 * <b>创建日期:</b> 2013-4-7下午2:15:27</br>
 * <b>更新时间:</b> 2013-4-7下午2:15:27</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class AutoUpdater implements DialogInterface.OnClickListener {
    private UpdateInfo mInfo;
    private FragmentActivity mActivity;

    public AutoUpdater(FragmentActivity activity) {
        mActivity = activity;

    }

    public void checkToUpdate(String url) {
        VstarApplication.getInstance()
                        .getAsyncHttpClient()
                        .get(mActivity,
                             url,
                             new ReadUpdateInfoHttpResponseHandler(
                                                                   mActivity,
                                                                   mActivity.getString(R.string.check_new_version)));
    }

    public void checkToUpdate(UpdateInfo info) {
        mInfo = info;
        showConfirmDialog();
    }

    private class ReadUpdateInfoHttpResponseHandler extends
            ProgressDialogLoadingHttpResponseHandler<UpdateInfo> {

        public ReadUpdateInfoHttpResponseHandler(Context c, String loadingMsg) {
            super(c, loadingMsg);
        }

        @Override
        public void onSuccess(UpdateInfo t) {
            super.onSuccess(t);
            mInfo = t;
            showConfirmDialog();
        }
    }

    private void showConfirmDialog() {
        if (isNeedUpdate(mInfo)) {
            ConfirmDialogFragment confirmDialog = ConfirmDialogFragment.instance(mActivity.getString(R.string.update_to_new_version,
                                                                                                     mInfo.versionName));
            confirmDialog.setPositiveButton(mActivity.getString(R.string.update), AutoUpdater.this);
            confirmDialog.setNegativeButton(mActivity.getString(R.string.cancel), null);
            confirmDialog.show(mActivity.getSupportFragmentManager(), "confirm dialog");
        }
    }

    public boolean isNeedUpdate(UpdateInfo info) {
        if (info == null || !info.flag)
            return false;
        boolean isNeed = false;
        try {
            int versionCode = mActivity.getPackageManager()
                                       .getPackageInfo(mActivity.getPackageName(), 0).versionCode;
            isNeed = versionCode < info.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return isNeed;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        DownloadDialogFragment downloadDialog = DownloadDialogFragment.instance(mActivity.getString(R.string.download),
                                                                                "新版本"
                                                                                        + mInfo.versionName,
                                                                                mInfo.apkUrl,
                                                                                new File(
                                                                                         PathUtil.getDownloadDirectory(),
                                                                                         mInfo.apkName).getAbsolutePath());
        downloadDialog.setOnDownloadFinishListener(new OnDownloadFinishListener() {

            @Override
            public void onFinish(String path) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(path)),
                                      "application/vnd.android.package-archive");
                mActivity.startActivity(intent);
            }
        });
        downloadDialog.show(mActivity.getSupportFragmentManager(), "download dialog");
    }
}
