package com.zerone.store.shopingtime.DB.impl;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.zerone.store.shopingtime.Bean.UserInfo;
import com.zerone.store.shopingtime.DB.abs.AbstractDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by on 2018/3/31 0031 16 46.
 * Author  LiuXingWen
 * 保存用户的信息的实现类
 */

public class UserInfoImpl extends AbstractDao {
    public UserInfoImpl(Context context) {
        super(context);
    }

    /**
     * @param userInfo 用户信息
     * @throws Exception
     */
    public void saveUserInfo(UserInfo userInfo) throws Exception {
        try {
            db = baseDao.getWritableDatabase();
            String sql = "insert into customer (u_id, account_id,account,u_orgid,u_uuid) values (?,?,?,?,?)";
            String[] param = new String[]{"10", userInfo.getAccount_id(), userInfo.getAccount(), userInfo.getOrganization_id(), userInfo.getUuid()};
            db.execSQL(sql, param);
            Log.i("URL", "保存用户信息成功");
        } catch (Exception e) {
            throw new Exception("插入数据失败", e);
        } finally {
            db.close();
        }
    }

    /**
     * @return
     * @throws Exception
     */
    public UserInfo getUserInfo(String u_id) throws Exception {
        Cursor cur = null;
        try {
            db = baseDao.getReadableDatabase();
            String[] field = new String[]{u_id};
            cur = db.rawQuery("select * from customer where u_id=" + field[0], null);
            int count = cur.getCount();
            if (count == 0) {
                return null;
            }
            UserInfo userInfo = new UserInfo();
            if (cur.moveToFirst()) {
                do {
                    userInfo.setAccount(cur.getString(cur.getColumnIndex("account")));

                    userInfo.setAccount_id(cur.getString(cur.getColumnIndex("account_id")));

                    userInfo.setOrganization_id(cur.getString(cur.getColumnIndex("u_orgid")));

                    userInfo.setUuid(cur.getString(cur.getColumnIndex("u_uuid")));

                } while (cur.moveToNext());
            }
            Log.i("BBBBB", userInfo.toString());
            return userInfo;

        } catch (Exception e) {
            throw new Exception("获取失败", e);
        } finally {
            cur.close();
            db.close();
        }
    }

    /**
     * 清空表中数据
     *
     * @throws Exception
     */
    public void deltable() throws Exception {
        try {
            db = baseDao.getWritableDatabase();
            String sql = "delete from customer";
            db.execSQL(sql);
        } catch (Exception e) {
            throw new Exception("清空失败", e);
        } finally {
            db.close();
        }
    }


    /**
     * 获取session
     *
     * @return
     * @throws Exception
     */
    public List<UserInfo> getAllUserInfo() throws Exception {
        Cursor cur = null;
        List<UserInfo> list = new ArrayList<>();
        try {
            db = baseDao.getReadableDatabase();
            cur = db.rawQuery("select * from customer", null);
            int count = cur.getCount();
            Log.i("BBBBB", count + "");
            if (count == 0) {
                return null;
            }
            cur.moveToFirst();
            Log.i("BBBBB", cur.getString(cur.getColumnIndex("acount")));

//            if (cur.moveToFirst()) {
//                do {
//                    UserInfo userInfo = new UserInfo();
//                    userInfo.setAccount(cur.getString(cur.getColumnIndex("acount")));
//                    userInfo.setAccount_id(cur.getString(cur.getColumnIndex("acount_id")));
//                    userInfo.setOrganization_id(cur.getString(cur.getColumnIndex("u_orgid")));
//                    userInfo.setUuid(cur.getString(cur.getColumnIndex("u_uuid")));
//                    list.add(userInfo);
//                } while (cur.moveToNext());
//            }
            Log.i("BBBBB", "userInfo: " + list);
            return list;
        } catch (Exception e) {
            throw new Exception("获取失败", e);
        } finally {
            cur.close();
            db.close();
        }
    }

}
