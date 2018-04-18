package com.zerone.store.shopingtime.DB.abs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2017/9/21 0021.
 */

public class AbstractDao {
    protected BaseDao baseDao;
    protected SQLiteDatabase db;

    public AbstractDao(Context context) {
        baseDao = new BaseDao(context);
    }

    public void initialTable() throws Exception {
        try {
            db = baseDao.getWritableDatabase();
            baseDao.createUserInfo(db);
            baseDao.createSaveAccount(db);
        } catch (Exception e) {
            throw new Exception("创建失败", e);
        } finally {
            db.close();
        }
    }
}
