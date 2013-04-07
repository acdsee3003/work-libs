package com.vstar.lib.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * <b>类名:</b> BaseComment.java</br>
 * <b>说明:</b> 评论的基础字段</br>
 * <b>创建日期:</b> 2013-4-7下午2:01:10</br>
 * <b>更新时间:</b> 2013-4-7下午2:01:10</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class BaseComment implements Serializable {
    private static final long serialVersionUID = 1L;
    public int id;
    public String icon;
    public String username;
    public String content;
    public List<BaseComment> childs;

    @Override
    public String toString() {
        return "BaseComment [id=" + id + ", icon=" + icon + ", username=" + username + ", content="
                + content + ", childs=" + childs + "]";
    }

}
