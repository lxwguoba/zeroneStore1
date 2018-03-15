package com.store.zerone.zeronestore.db.impl;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;


import com.store.zerone.zeronestore.db.abs.AbstractDao;
import com.store.zerone.zeronestore.domain.Worker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by on 2017/11/18 0018 17 49.
 * Author  LiuXingWen
 */

public class WorkerTabeDao  extends AbstractDao {

    public WorkerTabeDao(Context context) {
        super(context);
    }
    /**
     * 插入数据
     * @param worker  员工实体类
     * @throws Exception
     */
    public void addWorker(Worker worker) throws Exception {
        try {
            db = baseDao.getWritableDatabase();
            String sql = "insert into workertable (workerid, name,reception_qr,icon_thumb) values (?, ?, ?,?)";
            String[] param = new String[] { worker.getWorkerid(),worker.getName(),worker.getReception_qr(),worker.getIcon_thumb()};
            db.execSQL(sql, param);
            Log.i("URL", "addWorker: 插入成功员工");
        } catch (Exception e) {
            throw new Exception("插入数据失败", e);
        } finally {
            db.close();
        }
    }




    /**
     * 获取所有的员工
     * @return
     * @throws Exception
     */
    public List<Worker> getWorker() throws Exception {
        Cursor cur = null;
        List<Worker> list = new ArrayList<>();
        try {
            db = baseDao.getReadableDatabase();
            cur = db.rawQuery("select * from workertable", null);
            int count = cur.getCount();
            if(count == 0){
                return null;
            }
            if (cur.moveToFirst()) {
                do {
                    Worker wk  = new Worker();
                    wk.setWorkerid(cur.getString(cur.getColumnIndex("workerid")));
                    wk.setName(cur.getString(cur.getColumnIndex("name")));
                    wk.setReception_qr(cur.getString(cur.getColumnIndex("reception_qr")));
                    wk.setIcon_thumb(cur.getString(cur.getColumnIndex("icon_thumb")));
                    list.add(wk);
                } while (cur.moveToNext());
            }
            Log.i("DAO", "getWorker: "+list);
            return  list;
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
    public void deltable() throws Exception{
        try {
            db = baseDao.getWritableDatabase();
            String sql = "delete from workertable";
            db.execSQL(sql);
        } catch (Exception e) {
            throw new Exception("清空失败", e);
        } finally {
            db.close();
        }
    }
}
