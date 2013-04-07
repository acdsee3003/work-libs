package com.vstar.lib.db.dto;

import com.j256.ormlite.field.DatabaseField;

/**
 * 
 * <b>类名:</b> Favorite.java</br>
 * <b>说明:</b> 收藏Table 的字段 </br>注意 : 由于使用了ProGuard混淆代码 , 成员变量会被修改 , 因此,若需要使用表字段时, 需要 加上 columnName</br>
 * <b>创建日期:</b> 2013-4-7下午2:11:25</br>
 * <b>更新时间:</b> 2013-4-7下午2:11:25</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class Favorite {
    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
    private int id;
    @DatabaseField
    private String icon;
    @DatabaseField
    private String title;
    @DatabaseField(uniqueIndex = true)
    private String url;
    @DatabaseField
    private String type;
    @DatabaseField(columnName = "date")
    private long date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Favorite [id=" + id + ", icon=" + icon + ", title=" + title + ", url=" + url
                + ", type=" + type + ", date=" + date + "]";
    }

}
