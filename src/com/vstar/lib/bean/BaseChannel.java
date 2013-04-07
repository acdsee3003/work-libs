package com.vstar.lib.bean;

import java.io.Serializable;

/**
 * 
 * <b>类名:</b> BaseChannel.java</br>
 * <b>说明:</b> 频道的基础字段</br>
 * <b>创建日期:</b> 2013-4-7下午1:59:50</br>
 * <b>更新时间:</b> 2013-4-7下午1:59:50</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class BaseChannel implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 频道ID */
    public int id;
    /** 频道的父ID */
    public int pid;
    /** 频道的类型 */
    public int type;
    /** 频道的图标 */
    public String icon;
    /** 是否有子频道 */
    public boolean isHaveChild;
    /** 频道名称 */
    public String name;
    /** 频道对应的链接地址 */
    public String url;

    @Override
    public String toString() {
        return "BaseChannel [id=" + id + ", pid=" + pid + ", type=" + type + ", icon=" + icon
                + ", isHaveChild=" + isHaveChild + ", name=" + name + ", url=" + url + "]";
    }

}
