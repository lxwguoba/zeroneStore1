package com.store.zerone.zeronestore.db.impl;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.store.zerone.zeronestore.db.abs.AbstractDao;
import com.store.zerone.zeronestore.domain.SessionBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by on 2017/11/18 0018 17 49.
 * Author  LiuXingWen
 */

public class SessionTabeDao extends AbstractDao {

    public SessionTabeDao(Context context) {
        super(context);
    }
    /**
     * 插入数据
     * @param s_bean  Session的实体类
     * @throws Exception
     */
    public void addSession(SessionBean s_bean) throws Exception {
        try {
            db = baseDao.getWritableDatabase();
            String sql = "insert into sessiondb (s_id, s_value) values (?, ?)";
            String[] param = new String[] {s_bean.getS_id(),s_bean.getS_value() };
            db.execSQL(sql, param);
            Log.i("URL", "sessiondb: session");
        } catch (Exception e) {
            throw new Exception("插入数据失败", e);
        } finally {
            db.close();
        }
    }

    /**
     * 获取session
     * @return
     * @throws Exception
     */
    public List<SessionBean> getAllSession() throws Exception {
        Cursor cur = null;
        List<SessionBean> list = new ArrayList<>();
        try {
            db = baseDao.getReadableDatabase();
            cur = db.rawQuery("select * from sessiondb", null);
            int count = cur.getCount();
            if(count == 0){
                return null;
            }
            if (cur.moveToFirst()) {
                do {
                    SessionBean sessionBean  = new SessionBean();
                    sessionBean.setS_id(cur.getString(cur.getColumnIndex("s_id")));
                    sessionBean.setS_value(cur.getString(cur.getColumnIndex("s_value")));
                    list.add(sessionBean);
                } while (cur.moveToNext());
            }
            Log.i("DAO", "SessionAll: "+list);
            return  list;
        } catch (Exception e) {
            throw new Exception("获取失败", e);
        } finally {
            cur.close();
            db.close();
        }
    }

    /**
     * 获取session
     * @return
     * @throws Exception
     */
    public String getSession() throws Exception {
        Cursor cur = null;
        try {
            db = baseDao.getReadableDatabase();
            cur = db.rawQuery("select * from sessiondb where s_id=0", null);
            int count = cur.getCount();
            if(count == 0){
                return null;
            }
            SessionBean sessionBean  = new SessionBean();
            if (cur.moveToFirst()) {
                do {
                    sessionBean.setS_id(cur.getString(cur.getColumnIndex("s_id")));
                    sessionBean.setS_value(cur.getString(cur.getColumnIndex("s_value")));
                } while (cur.moveToNext());
            }
            return  sessionBean.getS_value();
        } catch (Exception e) {
            throw new Exception("获取失败", e);
        } finally {
            cur.close();
            db.close();
        }
    }

    /**
     * 清空表中数据
     * @throws Exception
     */
    public void del() throws Exception{
        try {
            db = baseDao.getWritableDatabase();
            String sql = "delete from sessiondb";
            db.execSQL(sql);
        } catch (Exception e) {
            throw new Exception("清空失败", e);
        } finally {
            db.close();
        }
    }
}
