package com.vstar.lib.adapter.viewbuilder;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.ImageView;

import com.lurencun.android.adapter.ViewBuilder;
import com.vstar.lib.io.ImageFetcher;
import com.vstar.lib.io.ImageFetcher.OnImageFetcherListener;
import com.vstar.lib.utils.UIUtils;

public class ListItemViewBuilder<T> extends ViewBuilder<T> {

    protected ImageFetcher mImageFetcher;

    public ListItemViewBuilder(FragmentActivity activity) {
        mImageFetcher = UIUtils.getImageFetcher(activity);
    }

    protected void loadImage(ImageView iv, String url) {
        if (mImageFetcher != null && !TextUtils.isEmpty(url))
            mImageFetcher.loadImage(url, iv);
    }

    protected void loadImage(ImageView iv, String url, OnImageFetcherListener listener) {
        if (mImageFetcher != null && !TextUtils.isEmpty(url))
            mImageFetcher.loadImage(url, iv, listener);
    }
}
