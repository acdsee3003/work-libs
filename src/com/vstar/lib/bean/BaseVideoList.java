package com.vstar.lib.bean;

import java.util.List;

/**
 * 
 * <b>类名:</b> BaseVideoList.java</br>
 * <b>说明:</b> 图片列表的基础信息</br>
 * <b>创建日期:</b> 2013-4-7下午2:09:16</br>
 * <b>更新时间:</b> 2013-4-7下午2:09:16</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class BaseVideoList<T extends BaseListItem> extends BaseField {
    private static final long serialVersionUID = 1L;
    public List<T> videos;
    public More more;

    @Override
    public String toString() {
        return "VideoList [videos=" + videos + ", more=" + more + ", flag=" + flag + "]";
    }

}
