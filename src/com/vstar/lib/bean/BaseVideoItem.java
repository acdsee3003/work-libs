package com.vstar.lib.bean;

/**
 * 
 * <b>类名:</b> BaseVideoItem.java</br>
 * <b>说明:</b> 图片列表Item的基础信息</br>
 * <b>创建日期:</b> 2013-4-7下午2:08:53</br>
 * <b>更新时间:</b> 2013-4-7下午2:08:53</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class BaseVideoItem extends BaseListItem {
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "BaseVideoItem [icon=" + icon + ", title=" + title + ", url=" + url + "]";
    }

}
