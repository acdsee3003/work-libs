package com.vstar.lib.bean;

/**
 * 
 * <b>类名:</b> More.java</br>
 * <b>说明:</b> 列表的信息</br>
 * <b>创建日期:</b> 2013-4-7下午2:09:28</br>
 * <b>更新时间:</b> 2013-4-7下午2:09:28</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class More extends BaseField {
    private static final long serialVersionUID = 1L;
    /** 获取更多列表的url */
    public String url;

    @Override
    public String toString() {
        return "More [url=" + url + ", flag=" + flag + "]";
    }

}
