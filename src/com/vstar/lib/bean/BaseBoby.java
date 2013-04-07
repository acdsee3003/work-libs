package com.vstar.lib.bean;

/**
 * 
 * <b>类名:</b> BaseBoby.java</br>
 * <b>说明:</b> 正文的基础字段</br>
 * <b>创建日期:</b> 2013-4-7下午1:59:57</br>
 * <b>更新时间:</b> 2013-4-7下午1:59:57</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class BaseBoby extends BaseField {
    private static final long serialVersionUID = 1L;
    /** 正文的id */
    public int id;
    /** 正文所属的频道Id */
    public int pid;
    /**正文的标题*/
    public String title;
    /**正文的日期*/
    public String date;
    /**正文的来源*/
    public String from;
    /**正文的内容*/
    public String content;

    @Override
    public String toString() {
        return "BaseBoby [id=" + id + ", pid=" + pid + ", title=" + title + ", date=" + date
                + ", from=" + from + ", content=" + content + ", flag=" + flag + "]";
    }

}
