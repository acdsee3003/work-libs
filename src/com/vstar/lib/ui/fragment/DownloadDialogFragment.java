package com.vstar.lib.ui.fragment;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;

import com.commonsware.cwac.task.AsyncTaskEx;
import com.loopj.android.http.AsyncHttpClient;
import com.vstar.lib.VstarApplication;
import com.vstar.lib.utils.FileUtil;
import com.vstar.lib.utils.FileUtil.SaveFileCallBack;
import com.vstar.lib.utils.L;
import com.vstar.lib.utils.ToastUtil;
import com.vstar.lib.R;

/**
 * 
 * <b>类名:</b> DownloadDialogFragment.java</br>
 * <b>说明:</b> 下载的弹出框</br>
 * <b>创建日期:</b> 2013-4-7下午2:14:28</br>
 * <b>更新时间:</b> 2013-4-7下午2:14:28</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class DownloadDialogFragment extends DialogFragment {
    private static final String EXTRA_TITLE = "title";
    private static final String EXTRA_MSG = "msg";
    private static final String EXTRA_DOWNLOAD_URL = "download_url";
    private static final String EXTRA_SAVE_PATH = "save_path";
    private String title;
    private String msg;
    private String url;
    private String downloadFilePath;
    private OnDownloadFinishListener listener;
    private AsyncHttpClient mClient;

    public static DownloadDialogFragment instance(String title, String msg, String url,
                                                  String filepath) {
        DownloadDialogFragment dialog = new DownloadDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TITLE, title);
        bundle.putString(EXTRA_MSG, msg);
        bundle.putString(EXTRA_DOWNLOAD_URL, url);
        bundle.putString(EXTRA_SAVE_PATH, filepath);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString(EXTRA_TITLE);
        msg = getArguments().getString(EXTRA_MSG);
        url = getArguments().getString(EXTRA_DOWNLOAD_URL);
        downloadFilePath = getArguments().getString(EXTRA_SAVE_PATH);
        mClient = VstarApplication.getInstance().getAsyncHttpClient();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setTitle(title);
        pd.setMessage(getString(R.string.dowloading, msg));
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setButton(ProgressDialog.BUTTON_NEGATIVE, getString(R.string.cancel),
                     new OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             /* 取消下载 */
                         }
                     });
        new DownLoadSkinAsyncTask(pd).execute(url);
        return pd;
    }

    class DownLoadSkinAsyncTask extends AsyncTaskEx<String, Integer, String> {

        private static final String TAG = "DownLoadSkinAsyncTaskEx";
        private ProgressDialog pd;

        public DownLoadSkinAsyncTask(ProgressDialog pd) {
            this.pd = pd;
        }

        @Override
        protected String doInBackground(String... urls) {
            L.i(TAG, "doInBackground > download url-->" + urls[0]);
            try {
                HttpEntity entity = mClient.getHttpClient().execute(new HttpGet(urls[0]))
                                           .getEntity();
                if (entity == null)
                    return null;
                pd.setMax((int) entity.getContentLength());
                L.i(TAG, "文件的大小-->" + entity.getContentLength());

                File downloadFile = new File(downloadFilePath);
                FileUtil.saveFile(downloadFile, entity.getContent(), new SaveFileCallBack() {
                    @Override
                    public void saveFileSize(int size) {
                        publishProgress(size);
                    }
                });
                L.i(TAG, "文件下载完成的地址-->" + downloadFilePath);

            } catch (ClientProtocolException e1) {
                e1.printStackTrace();
                downloadFilePath = null;
            } catch (IOException e1) {
                e1.printStackTrace();
                downloadFilePath = null;
            }

            return downloadFilePath;
        }

        @Override
        protected void onPostExecute(final String path) {
            pd.dismiss();
            /* 若下载失败提示相应信息 */
            if (TextUtils.isEmpty(path)) {
                ToastUtil.showLongTimeMsg(R.string.error_download_file_failure);
            }
            if (listener != null)
                listener.onFinish(path);
        }

        @Override
        protected void onPreExecute() {
            pd.show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            /* 更新进度条 */
            pd.setProgress(values[0]);
        }
    }

    public void setOnDownloadFinishListener(OnDownloadFinishListener l) {
        listener = l;
    }

    public interface OnDownloadFinishListener {
        void onFinish(String path);
    }
}