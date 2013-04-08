package com.vstar.lib.widget;

import java.lang.ref.WeakReference;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ListView;

import com.lurencun.android.adapter.CommonPagerAdapter;
import com.lurencun.android.adapter.ViewBuilder;
import com.viewpagerindicator.CirclePageIndicator;
import com.vstar.lib.utils.L;
import com.vstar.lib.utils.ViewUtil;
import com.vstar.lib.R;

/**
 * 
 * <b>类名:</b> AbsHeaderListViewController.java</br>
 * <b>说明:</b> ListView头部热图组件的控制器</br>
 * <b>创建日期:</b> 2013-4-7下午2:20:30</br>
 * <b>更新时间:</b> 2013-4-7下午2:20:30</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public abstract class AbsHeaderListViewController<T> implements OnClickListener, OnScrollListener,
                                                     OnTouchListener {

    private static final Object TAG = L.makeLogTag(AbsHeaderListViewController.class);
    private static long mFlipDelayMillis = 5 * 1000;
    private Context mContext;
    /** 将Header添加到指定的ListView */
    private ListView mListView;
    /** Header的根容器 */
    private View mRootView;
    /** 根容器里的ViewPager */
    private ViewPager mViewPager;
    /** Pager里面的layout id */
    private Integer mPagerItemId;
    /** 圆形指示图 */
    private CirclePageIndicator mPageIndicator;
    /** 数据源 */
    private List<T> mList;
    /** ListView的滑动监听器.需要通过setListView方法设置 */
    private OnScrollListener mOnListViewScrollListener;
    /** 每张Page的点击事件 */
    private OnPageItemClickListener<T> mOnPageItemClickListener;
    /** 自动滑动的Handler */
    private AutoFlipHandler mAutoFlipHandler;
    private boolean isAutoFlip = true;

    private CommonPagerAdapter<T> mHeadPagerAdapter;

    /**
     * 将数据填充到View中
     * 
     * @param itemView
     *            需要填充数据的View
     * @param t
     *            数据
     */
    public abstract void fillItemView(View itemView, T t);

    public AbsHeaderListViewController(Context context, Integer pagerItemId) {
        mContext = context;
        mPagerItemId = pagerItemId;
        mRootView = LayoutInflater.from(context).inflate(R.layout.list_header_layout, null);
        mViewPager = ViewUtil.findView(mRootView, R.id.list_header_vp);
        mViewPager.setPageMargin(2);
        mPageIndicator = ViewUtil.findView(mRootView, R.id.list_header_indicator);
        mAutoFlipHandler = new AutoFlipHandler(context, mViewPager);
        mPageIndicator.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int scrollState) {
                // 点击滑动时,停止自动滑动.
                switch (scrollState) {
                case ViewPager.SCROLL_STATE_DRAGGING:
                case ViewPager.SCROLL_STATE_SETTLING:
                    stopFlip();
                    break;
                case ViewPager.SCROLL_STATE_IDLE:
                    startFlip();
                    break;
                }
            }
        });
        hide();
    }

    public void setListView(ListView lv) {
        setListView(lv, null);
    }

    public void setListView(ListView lv, OnScrollListener scrollListener) {
        mListView = lv;
        mOnListViewScrollListener = scrollListener;
        lv.addHeaderView(mRootView);
        lv.setOnScrollListener(this);
        mViewPager.setOnTouchListener(this);
    }

    /**
     * 设置头部数据
     * 
     * @param lists
     */
    public void setHeaderList(List<T> lists) {
        mList = lists;
        stopFlip();
        if (mHeadPagerAdapter == null) {
            mHeadPagerAdapter = new CommonPagerAdapter<T>(LayoutInflater.from(mContext),
                                                          new ListHeaderItemViewBuilder());
            mViewPager.setAdapter(mHeadPagerAdapter);
            mHeadPagerAdapter.update(lists);
            mPageIndicator.setViewPager(mViewPager);
        } else {
            mHeadPagerAdapter.update(lists);
            mPageIndicator.notifyDataSetChanged();
            mPageIndicator.setCurrentItem(0);
        }
        // 开始自动滑动
        startFlip();
        // 如果数据为空,则隐藏Header视图
        if (mHeadPagerAdapter.getCount() == 0) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        ViewUtil.setGone(mViewPager, true);
        ViewUtil.setGone(mPageIndicator, true);
    }

    private void show() {
        ViewUtil.setGone(mViewPager, false);
        ViewUtil.setGone(mPageIndicator, false);
    }

    /**
     * 设置高度
     * 
     * @param height
     *            Header视图的高度
     */
    public void setHeight(int height) {
        mViewPager.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, height));
    }

    /**
     * 设置自动滑动的时间间隔
     * 
     * @param delayMillis
     *            时间长度(单位:毫秒)
     */
    public void setFlipDelayMillis(long delayMillis) {
        if (delayMillis < 1000) {
            L.d(TAG, "filp delay millis must be > 1000 (1s)");
            return;
        }
        mFlipDelayMillis = delayMillis;
    }

    /**
     * 设置是否开启固定时间间隔内自动滑动
     * 
     * @param auto
     *            是否自动滑动 , 默认为true.
     */
    public void setAutoFilp(boolean auto) {
        isAutoFlip = auto;
    }

    public void startFlip() {
        if (isAutoFlip && !mAutoFlipHandler.hasMessages(0))
            mAutoFlipHandler.sendEmptyMessageDelayed(0, mFlipDelayMillis);
    }

    public void stopFlip() {
        mAutoFlipHandler.removeMessages(0);
    }

    public void setOnPageItemClickListener(OnPageItemClickListener<T> listener) {
        mOnPageItemClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        // 通过Tag来获取点击的这个View在ViewPager对应的位置.
        int position = (Integer) v.getTag();
        if (mOnPageItemClickListener != null) {
            mOnPageItemClickListener.onItemClick(mViewPager, mList, position);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v == mViewPager) {
            /**
             * 此方法用于解决ListView抢ViewPager的触摸事件!
             * 案例:如果正在滑动ViewPager过程中,手指向下移动了一下,ViewPager的事件将被ListView抢占
             * ,因而ViewPager不能正常的滑动.
             */
            mListView.requestDisallowInterceptTouchEvent(true);

        }
        return false;
    }

    class ListHeaderItemViewBuilder extends ViewBuilder<T> {
        public View createView(LayoutInflater inflater, int position, T data) {
            // 根据指定的layout id 实例化View
            View view = inflater.inflate(mPagerItemId, null);
            view.setOnClickListener(AbsHeaderListViewController.this);
            updateView(view, position, data);
            return view;
        };

        public void updateView(View view, int position, T data) {
            fillItemView(view, data);
            // 将位置保存到Tag里
            view.setTag(position);
        };
    }

    public interface OnPageItemClickListener<T> {
        void onItemClick(ViewPager pager, List<T> list, int position);
    }

    /** 防止Handler内存泄漏 */
    private static class AutoFlipHandler extends Handler {
        private WeakReference<Context> activityReference;
        private ViewPager pager;

        AutoFlipHandler(Context context, ViewPager pager) {
            activityReference = new WeakReference<Context>(context);
            this.pager = pager;
        }

        public void handleMessage(android.os.Message msg) {
            Context a = activityReference.get();
            if (a == null || pager.getAdapter() == null || pager.getAdapter().getCount() == 0) {
                removeMessages(0);
                return;
            }
            int index = pager.getCurrentItem() == pager.getAdapter().getCount() - 1 ? 0
                                                                                   : pager.getCurrentItem() + 1;
            pager.setCurrentItem(index);
            sendEmptyMessageDelayed(0, mFlipDelayMillis);
        };
    };
}
