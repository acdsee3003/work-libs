package com.vstar.lib.bean;

/**
 * 
 * <b>类名:</b> BaseNewsBoby.java</br>
 * <b>说明:</b> 新闻正文信息</br>
 * <b>创建日期:</b> 2013-4-7下午2:06:54</br>
 * <b>更新时间:</b> 2013-4-7下午2:06:54</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class BaseNewsBoby extends BaseBoby {
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "BaseNewsBoby [id=" + id + ", pid=" + pid + ", title=" + title + ", date=" + date
                + ", from=" + from + ", content=" + content + ", flag=" + flag + "]";
    }

}
