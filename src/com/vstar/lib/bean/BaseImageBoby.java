package com.vstar.lib.bean;

import java.util.List;

/**
 * 
 * <b>类名:</b> BaseImageBoby.java</br>
 * <b>说明:</b> 图片正文的信息</br>
 * <b>创建日期:</b> 2013-4-7下午2:04:45</br>
 * <b>更新时间:</b> 2013-4-7下午2:04:45</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class BaseImageBoby<T extends BaseImage> extends BaseBoby {
    private static final long serialVersionUID = 1L;
    public List<T> images;

    @Override
    public String toString() {
        return "ImageBoby [title=" + title + ", images=" + images + ", id=" + id + ", pid=" + pid
                + ", flag=" + flag + "]";
    }
}
