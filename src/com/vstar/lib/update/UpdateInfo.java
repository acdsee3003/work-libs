package com.vstar.lib.update;

import com.vstar.lib.bean.BaseField;

/**
 * 
 * <b>类名:</b> UpdateInfo.java</br>
 * <b>说明:</b> 更新的信息</br>
 * <b>创建日期:</b> 2013-4-7下午2:15:38</br>
 * <b>更新时间:</b> 2013-4-7下午2:15:38</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class UpdateInfo extends BaseField {
    private static final long serialVersionUID = 1L;
    public int versionCode;
    public String versionName;
    public String apkName;
    public String apkUrl;

    @Override
    public String toString() {
        return "UpdateInfo [versionCode=" + versionCode + ", versionName=" + versionName
                + ", apkName=" + apkName + ", apkUrl=" + apkUrl + ", flag=" + flag + "]";
    }

}