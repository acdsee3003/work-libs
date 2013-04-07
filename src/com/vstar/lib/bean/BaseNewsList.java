package com.vstar.lib.bean;

import java.util.List;

/**
 * 
 * <b>类名:</b> BaseNewsList.java</br>
 * <b>说明:</b> 新闻列表的基础信息</br>
 * <b>创建日期:</b> 2013-4-7下午2:07:36</br>
 * <b>更新时间:</b> 2013-4-7下午2:07:36</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class BaseNewsList<T extends BaseNewsItem> extends BaseField {
    private static final long serialVersionUID = 1L;
    /** 头部热图数据集 */
    public List<T> header;
    /** 新闻列表数据集 */
    public List<T> list;
    /** 更多新闻 */
    public More more;

    @Override
    public String toString() {
        return "BaseNewsList [header=" + header + ", list=" + list + ", more=" + more + ", flag="
                + flag + "]";
    }

}
