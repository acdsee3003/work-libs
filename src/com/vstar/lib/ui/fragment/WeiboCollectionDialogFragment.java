package com.vstar.lib.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import cn.sharesdk.douban.Douban;
import cn.sharesdk.framework.AbstractWeibo;
import cn.sharesdk.framework.WeiboActionListener;
import cn.sharesdk.renren.Renren;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.tencent.weibo.TencentWeibo;
import cn.sharesdk.wechat.friends.Wechat;

import com.lurencun.android.adapter.HolderAdapter;
import com.vstar.lib.adapter.viewbuilder.WeiboItemViewBuilder;
import com.vstar.lib.bean.WeiboItem;
import com.vstar.lib.utils.L;
import com.vstar.lib.utils.ToastUtil;
import com.vstar.lib.R;

public class WeiboCollectionDialogFragment extends DialogFragment implements OnClickListener,
                                                                 WeiboActionListener, Callback {
    private static final Object TAG = L.makeLogTag(WeiboCollectionDialogFragment.class);
    private static HolderAdapter<WeiboItem> mAdapter;
    private Handler mHandler;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if (mAdapter == null) {
            mAdapter = new HolderAdapter<WeiboItem>(getActivity().getLayoutInflater(),
                                                    new WeiboItemViewBuilder());
            mAdapter.update(findWeibo());
        }
        builder.setAdapter(mAdapter, this);
        return builder.create();
    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        mHandler = new Handler(this);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        L.i(TAG, mAdapter.getItem(which).toString());
        String name = ((WeiboItem) mAdapter.getItem(which)).name;
        AbstractWeibo weibo = AbstractWeibo.getWeibo(getActivity(), name);
        weibo.setWeiboActionListener(this);
        weibo.showUser(null);
    }

    private List<WeiboItem> findWeibo() {
        AbstractWeibo[] weibos = AbstractWeibo.getWeiboList(getActivity());
        List<WeiboItem> items = new ArrayList<WeiboItem>();
        for (AbstractWeibo weibo : weibos) {
            int icon = 0;
            int name = 0;
            if (TencentWeibo.NAME.equalsIgnoreCase(weibo.getName())) {
                icon = R.drawable.tencent_weibo;
                name = R.string.tencent_weibo;
            } else if (SinaWeibo.NAME.equalsIgnoreCase(weibo.getName())) {
                icon = R.drawable.sina_weibo;
                name = R.string.sina_weibo;
            } else if (Douban.NAME.equalsIgnoreCase(weibo.getName())) {
                icon = R.drawable.douban;
                name = R.string.douban;
            } else if (Renren.NAME.equalsIgnoreCase(weibo.getName())) {
                icon = R.drawable.renren;
                name = R.string.renren;
            } else if (QZone.NAME.equalsIgnoreCase(weibo.getName())) {
                icon = R.drawable.qzone;
                name = R.string.qzone;
            } else if (Wechat.NAME.equalsIgnoreCase(weibo.getName())) {
                icon = R.drawable.wechat_friend;
                name = R.string.wechat;
            }
            WeiboItem item = new WeiboItem();
            item.icon = icon;
            item.name = weibo.getName();
            item.displayName = getActivity().getString(name);

            items.add(item);
        }
        return items;
    }

    @Override
    public void onCancel(AbstractWeibo weibo, int action) {
        Message msg = new Message();
        msg.arg1 = 0;
        msg.arg2 = action;
        msg.obj = weibo;
        mHandler.sendMessage(msg);
    }

    @Override
    public void onComplete(AbstractWeibo weibo, int action, HashMap<String, Object> arg2) {
        Message msg = new Message();
        msg.arg1 = 1;
        msg.arg2 = action;
        msg.obj = weibo;
        mHandler.sendMessage(msg);
    }

    @Override
    public void onError(AbstractWeibo weibo, int action, Throwable arg2) {
        Message msg = new Message();
        msg.arg1 = -1;
        msg.arg2 = action;
        msg.obj = weibo;
        mHandler.sendMessage(msg);
    }

    @Override
    public boolean handleMessage(Message msg) {
        AbstractWeibo weibo = (AbstractWeibo) msg.obj;
        String text = AbstractWeibo.actionToString(msg.arg2);
        switch (msg.arg1) {
        case -1: { // 失败
            text = weibo.getName() + " caught error at " + text;
            break;
        }
        case 0: { // 取消
            text = weibo.getName() + " canceled at " + text;
            break;
        }
        case 1: { // 成功
            text = weibo.getName() + " completed at " + text;
            break;
        }
        }

        ToastUtil.showLongTimeMsg(text);
        return false;
    }
}
