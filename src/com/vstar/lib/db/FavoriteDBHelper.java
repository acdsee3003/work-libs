package com.vstar.lib.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.vstar.lib.db.dto.Favorite;

/**
 * 
 * <b>类名:</b> FavoriteDBHelper.java</br>
 * <b>说明:</b> 操作"收藏夹"数据库的帮助类</br>
 * <b>创建日期:</b> 2013-4-7下午2:10:55</br>
 * <b>更新时间:</b> 2013-4-7下午2:10:55</br>
 * <b>Email:</b> 730123427@qq.com</br>
 * <b>最后更新者:</b> 钟威</br>
 * @author 钟威</br>
 */
public class FavoriteDBHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "favorite.db";
    private static final int DATABASE_VERSION = 3;

    private Dao<Favorite, Integer> mFavoriteDAO;

    public FavoriteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
        try {
            TableUtils.createTable(connectionSource, Favorite.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2, int arg3) {
        try {
            TableUtils.dropTable(connectionSource, Favorite.class, true);
            onCreate(arg0, arg1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Favorite, Integer> getDAO() {
        if (mFavoriteDAO == null) {
            try {
                mFavoriteDAO = getDao(Favorite.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mFavoriteDAO;
    }

    @Override
    public void close() {
        super.close();
        mFavoriteDAO = null;
    }
}
