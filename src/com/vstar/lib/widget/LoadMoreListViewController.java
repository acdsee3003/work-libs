package com.vstar.lib.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ViewFlipper;

import com.vstar.lib.VstarApplication;
import com.vstar.lib.bean.More;
import com.vstar.lib.network.ProgressBarLoadingHttpResponseHandler;
import com.vstar.lib.utils.L;
import com.vstar.lib.utils.ViewUtil;
import com.vstar.lib.R;

/**
 * 
 * <b>类名:</b> LoadMoreListViewController.java</br>
 * <b>说明:</b> 加载更多列表的控制类</br>
 * <b>创建日期:</b> 2013-4-7下午2:20:42</br>
 * <b>更新时间:</b> 2013-4-7下午2:20:42</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class LoadMoreListViewController<T> implements OnClickListener, OnScrollListener {

    private static final int CLICK_LOAD_INDEX = 0;
    private static final int LOADING_INDEX = 1;
    private static final int NO_MORE_INDEX = 2;
    private static final int LOAD_FAILURE_INDEX = 3;
    private static final Object TAG = L.makeLogTag(LoadMoreListViewController.class);
    private Context mContext;

    private View mRootView;

    private ViewFlipper mViewFilpper;

    private Button mLoadMoreButton;

    private ListView mListView;

    private More mMore;

    private boolean isAutoLoad = false;

    private OnLoadMoreListener<T> mOnLoadMoreListener;

    private OnScrollListener mOnListViewScrollListener;

    public LoadMoreListViewController(Context context) {
        mContext = context;
        mRootView = LayoutInflater.from(context).inflate(R.layout.list_more_layout, null);
        mViewFilpper = ViewUtil.findView(mRootView, R.id.load_more_vf);
        mLoadMoreButton = ViewUtil.findView(mViewFilpper, R.id.load_more_btn);
        mLoadMoreButton.setOnClickListener(this);
        ViewUtil.findView(mViewFilpper, R.id.load_failure_try_again_btn).setOnClickListener(this);
    }

    public void setListView(ListView lv) {
        setListView(lv, null);
    }

    public void setListView(ListView lv, OnScrollListener scrollListener) {
        mListView = lv;
        mOnListViewScrollListener = scrollListener;
        lv.addFooterView(mRootView);
        mListView.setOnScrollListener(this);
    }

    public void setAutoLoad(boolean auto) {
        isAutoLoad = auto;
    }

    public void setMore(More more) {
        mMore = more;
        if (more == null || !more.flag || TextUtils.isEmpty(more.url)) {
            mViewFilpper.setDisplayedChild(NO_MORE_INDEX);
        }
    }

    @Override
    public void onClick(View v) {
        fetcherMoreData();
    }

    private void fetcherMoreData() {
        if (mMore == null || !mMore.flag || TextUtils.isEmpty(mMore.url)) {
            L.e(TAG, "invalid More Object!");
            return;
        }
        VstarApplication.getInstance().getAsyncHttpClient()
                        .get(mContext, mMore.url, new LoadMoreHttpResponseHandler(null));
    }

    public void setOnLoadMoreListener(OnLoadMoreListener<T> listener) {
        mOnLoadMoreListener = listener;
    }

    class LoadMoreHttpResponseHandler extends ProgressBarLoadingHttpResponseHandler<T> {

        public LoadMoreHttpResponseHandler(ProgressBar progressBar) {
            super(progressBar);
        }

        @Override
        public void onStart() {
            super.onStart();
            mViewFilpper.setDisplayedChild(LOADING_INDEX);
        }

        @Override
        public void onSuccess(T t) {
            mViewFilpper.setDisplayedChild(CLICK_LOAD_INDEX);
            if (mOnLoadMoreListener != null && t != null) {
                mOnLoadMoreListener.onLoadMoreSuccess(t);
            }
        };

        @Override
        public void onFailure(Throwable arg0, String arg1) {
            super.onFailure(arg0, arg1);
            mViewFilpper.setDisplayedChild(LOAD_FAILURE_INDEX);
        }
    }

    public interface OnLoadMoreListener<T> {
        void onLoadMoreSuccess(T t);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
        case OnScrollListener.SCROLL_STATE_IDLE:
            // L.e(TAG, view.getLastVisiblePosition() + "");
            // L.e(TAG, view.getCount() + "");
            if (isAutoLoad && view.getLastVisiblePosition() == view.getCount() - 1) {
                if (CLICK_LOAD_INDEX == mViewFilpper.getDisplayedChild())
                    fetcherMoreData();
            }
            break;
        default:
            break;
        }
        if (mOnListViewScrollListener != null)
            mOnListViewScrollListener.onScrollStateChanged(view, scrollState);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                         int totalItemCount) {
        if (mOnListViewScrollListener != null)
            mOnListViewScrollListener.onScroll(view, firstVisibleItem, visibleItemCount,
                                               totalItemCount);
    }
}
