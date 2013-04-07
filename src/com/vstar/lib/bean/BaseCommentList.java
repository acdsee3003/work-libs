package com.vstar.lib.bean;

import java.util.List;

/**
 * 
 * <b>类名:</b> BaseCommentList.java</br>
 * <b>说明:</b> 评论列表的基础字段</br>
 * <b>创建日期:</b> 2013-4-7下午2:02:21</br>
 * <b>更新时间:</b> 2013-4-7下午2:02:21</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class BaseCommentList<T extends BaseComment> extends BaseField {
    private static final long serialVersionUID = 1L;
    /** 列表的集合 */
    public List<T> comments;
    /** 更多列表 */
    public More more;

    @Override
    public String toString() {
        return "BaseCommentList [comments=" + comments + ", more=" + more + ", flag=" + flag + "]";
    }

}
