package com.vstar.lib.bean;

import java.io.Serializable;

/**
 * 
 * <b>类名:</b> BaseUser.java</br>
 * <b>说明:</b> 用户信息</br>
 * <b>创建日期:</b> 2013-4-7下午2:08:22</br>
 * <b>更新时间:</b> 2013-4-7下午2:08:22</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class BaseUser implements Serializable {
    private static final long serialVersionUID = 1L;
    public String id;
    public String name;
    public int age;
    public String sex;
}
