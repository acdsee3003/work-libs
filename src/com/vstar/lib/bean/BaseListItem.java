package com.vstar.lib.bean;

import java.io.Serializable;

/**
 * 
 * <b>类名:</b> BaseListItem.java</br>
 * <b>说明:</b> 通用列表的Item</br>
 * <b>创建日期:</b> 2013-4-7下午2:06:16</br>
 * <b>更新时间:</b> 2013-4-7下午2:06:16</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class BaseListItem implements Serializable {
    private static final long serialVersionUID = 1L;
    public String icon;
    public String title;
    public String url;

    @Override
    public String toString() {
        return "ListItem [icon=" + icon + ", title=" + title + ", url=" + url + "]";
    }

}
