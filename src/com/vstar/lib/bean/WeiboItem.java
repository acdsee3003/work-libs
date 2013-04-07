package com.vstar.lib.bean;

/**
 * 
 * <b>类名:</b> WeiboItem.java</br>
 * <b>说明:</b> 微博列表的Item</br>
 * <b>创建日期:</b> 2013-4-7下午2:32:34</br>
 * <b>更新时间:</b> 2013-4-7下午2:32:34</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class WeiboItem {
    public int icon;
    public String name;
    public String displayName;

    @Override
    public String toString() {
        return "WeiboItem [icon=" + icon + ", name=" + name + ", displayName=" + displayName + "]";
    }
}