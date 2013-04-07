package com.vstar.lib.bean;

import java.util.List;

/**
 * 
 * <b>类名:</b> BaseImageList.java</br>
 * <b>说明:</b> 图片列表</br>
 * <b>创建日期:</b> 2013-4-7下午2:05:38</br>
 * <b>更新时间:</b> 2013-4-7下午2:05:38</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class BaseImageList<T extends BaseListItem> extends BaseField {
    private static final long serialVersionUID = 1L;
    /** 列表的集合 */
    public List<T> images;
    /** 更多列表 */
    public More more;

    @Override
    public String toString() {
        return "ImageList [images=" + images + ", more=" + more + ", flag=" + flag + "]";
    }

}
