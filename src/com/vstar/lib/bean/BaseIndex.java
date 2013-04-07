package com.vstar.lib.bean;

import java.util.List;

import com.vstar.lib.update.UpdateInfo;

/**
 * 
 * <b>类名:</b> BaseIndex.java</br>
 * <b>说明:</b> 首页数据</br>
 * <b>创建日期:</b> 2013-4-7下午2:05:59</br>
 * <b>更新时间:</b> 2013-4-7下午2:05:59</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class BaseIndex<T extends BaseChannel> extends BaseField {
    private static final long serialVersionUID = 1L;
    /** 登录用户的信息 */
    public BaseUser user;
    /** 首页的默认频道 */
    public List<T> navs;
    /** 欢迎页的图片 */
    public Welcome welcome;
    /** 更新的信息 */
    public UpdateInfo updata;

    @Override
    public String toString() {
        return "IndexBase [user=" + user + ", navs=" + navs + ", welcome=" + welcome + ", updata="
                + updata + ", flag=" + flag + "]";
    }

    public class Welcome extends BaseField {
        /** 欢迎页里的欢迎图的url地址 */
        public String pic;

        @Override
        public String toString() {
            return "Welcome [pic=" + pic + ", flag=" + flag + "]";
        }

    }
}
