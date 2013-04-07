package com.vstar.lib.bean;

import java.io.Serializable;

/**
 * 
 * <b>类名:</b> BaseImage.java</br>
 * <b>说明:</b> 图片的基本信息</br>
 * <b>创建日期:</b> 2013-4-7下午2:03:28</br>
 * <b>更新时间:</b> 2013-4-7下午2:03:28</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class BaseImage implements Serializable {
    private static final long serialVersionUID = 1L;
    public String url;
    public String content;

    @Override
    public String toString() {
        return "Image [url=" + url + ", content=" + content + "]";
    }
}