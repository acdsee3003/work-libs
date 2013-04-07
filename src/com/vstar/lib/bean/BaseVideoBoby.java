package com.vstar.lib.bean;

/**
 * 
 * <b>类名:</b> BaseVideoBoby.java</br>
 * <b>说明:</b> 图片正文的基础信息</br>
 * <b>创建日期:</b> 2013-4-7下午2:08:36</br>
 * <b>更新时间:</b> 2013-4-7下午2:08:36</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class BaseVideoBoby extends BaseBoby {
    private static final long serialVersionUID = 1L;
    /** 视频的URL地址 */
    public String video;

    @Override
    public String toString() {
        return "ImageBoby [title=" + title + ", video=" + video + ", id=" + id + ", pid=" + pid
                + ", flag=" + flag + "]";
    }
}
