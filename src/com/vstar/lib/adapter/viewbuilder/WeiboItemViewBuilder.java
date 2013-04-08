package com.vstar.lib.adapter.viewbuilder;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lurencun.android.adapter.ViewBuilder;
import com.vstar.lib.bean.WeiboItem;
import com.vstar.lib.utils.ViewUtil;
import com.vstar.lib.R;

public class WeiboItemViewBuilder extends ViewBuilder<WeiboItem> {

    @Override
    public View createView(LayoutInflater inflater, int position, WeiboItem data) {
        View view = inflater.inflate(R.layout.list_image_text_item, null);
        updateView(view, position, data);
        return view;
    }

    @Override
    public void updateView(View view, int position, WeiboItem data) {
        ImageView icon = ViewUtil.findView(view, R.id.list_icon_iv);
        TextView text = ViewUtil.findView(view, R.id.list_text_tv);
        text.setText(data.displayName);
        icon.setImageResource(data.icon);
    }
}
