package com.vstar.lib.bean;

/**
 * 
 * <b>类名:</b> BaseNewsItem.java</br>
 * <b>说明:</b> 新闻列表Item的基础信息</br>
 * <b>创建日期:</b> 2013-4-7下午2:07:21</br>
 * <b>更新时间:</b> 2013-4-7下午2:07:21</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class BaseNewsItem extends BaseListItem {
    private static final long serialVersionUID = 1L;
    /** 新闻的更新时间 */
    public String date;
    /** 新闻的类型 */
    public int type;

    @Override
    public String toString() {
        return "NewsItem [date=" + date + ", type=" + type + ", icon=" + icon + ", title=" + title
                + ", url=" + url + "]";
    }

}
