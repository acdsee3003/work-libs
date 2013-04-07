package com.vstar.lib.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.commonsware.cwac.adapter.AdapterWrapper;
import com.vstar.lib.utils.L;
import com.vstar.lib.utils.ViewUtil;

/**
 * 对ListAdapter 再次封装, 使用ListView显示网格的Item.
 * 
 * @author Administrator
 * 
 */
public class GridAdapter extends AdapterWrapper implements OnClickListener {

    private static final String TAG = L.makeLogTag(GridAdapter.class);
    private int mNumColumn = 1;
    private ListView mListView;

    public GridAdapter(ListAdapter wrapped) {
        super(wrapped);
    }

    @Override
    public int getCount() {
        // 根据列数来计算ListView的行数
        return (int) Math.ceil(super.getCount() / (float) mNumColumn);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        LinearLayout groupLayout = (LinearLayout) convertView;
        // 每一行的初始position
        int firstItemPosition = position * mNumColumn;
        // ListView的最大索引值
        int itemMaxPostion = super.getCount() - 1;

        if (groupLayout == null || groupLayout.getChildCount() != mNumColumn) {
            groupLayout = new LinearLayout(context);
            // if (mListView != null)
            // groupLayout.setBackgroundDrawable(mListView.getDivider());
            groupLayout.setClickable(true);
            groupLayout.setLayoutParams(new AbsListView.LayoutParams(
                                                                     AbsListView.LayoutParams.MATCH_PARENT,
                                                                     AbsListView.LayoutParams.MATCH_PARENT));

            LinearLayout.LayoutParams itemViewLayoutParams = new LinearLayout.LayoutParams(
                                                                                           LinearLayout.LayoutParams.MATCH_PARENT,
                                                                                           LinearLayout.LayoutParams.MATCH_PARENT);
            itemViewLayoutParams.weight = 1;
            // if (mListView != null)
            // itemViewLayoutParams.setMargins(mListView.getDividerHeight(),
            // 0, mListView.getDividerHeight(), 0);
            for (int i = 0; i < mNumColumn; i++) {
                int itemPosition = Math.min(firstItemPosition + i, itemMaxPostion);
                View itemView = super.getView(itemPosition, groupLayout.getChildAt(i), parent);
                itemView.setOnClickListener(this);
                itemView.setTag(itemView.getId(), itemPosition);
                groupLayout.addView(itemView, itemViewLayoutParams);
            }
        } else {
            int groupItemCount = groupLayout.getChildCount();
            for (int i = 0; i < groupItemCount; i++) {
                // item的虚拟位置
                int virtualItemPosition = firstItemPosition + i;
                // item的真实位置--因为虚拟位置可能大于item的实际个数 , 所以取小值;
                int realItemPosition = Math.min(virtualItemPosition, itemMaxPostion);
                // 获取Item View
                View itemView = groupLayout.getChildAt(i);
                itemView.setTag(itemView.getId(), realItemPosition);
                // 隐藏多余的Item View
                if (virtualItemPosition > realItemPosition) {
                    ViewUtil.setGone(itemView, true);
                } else {
                    ViewUtil.setGone(itemView, false);
                    super.getView(realItemPosition, itemView, parent);
                }
            }
        }

        return groupLayout;
    }

    public int getNumColumn() {
        return mNumColumn;
    }

    public void setNumColumn(int numColumn) {
        if (numColumn < 0) {
            L.e(TAG, "num column must > 0 !");
            return;
        }
        if (mNumColumn != numColumn) {
            mNumColumn = numColumn;
            notifyDataSetChanged();
        }
    }

    /**
     * 设置GridAdapter对应的ListView , 如果不设,ListView.OnItemClickListener 事件将不会被触发.
     * 
     * @param lv
     *            GridAdapter对应的ListView
     */
    public void setListView(ListView lv) {
        mListView = lv;
        lv.setCacheColorHint(Color.TRANSPARENT);
        lv.setDividerHeight(0);
    }

    @Override
    public void onClick(View v) {
        if (mListView != null) {
            mListView.performItemClick(v, (Integer) v.getTag(v.getId()), v.getId());
        }
    }

}
