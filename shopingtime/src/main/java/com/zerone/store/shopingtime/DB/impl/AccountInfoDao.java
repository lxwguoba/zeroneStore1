package com.zerone.store.shopingtime.DB.impl;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.zerone.store.shopingtime.Bean.login.Account;
import com.zerone.store.shopingtime.DB.abs.AbstractDao;

/**
 * Created by on 2018/4/2 0002 13 15.
 * Author  LiuXingWen
 */

public class AccountInfoDao extends AbstractDao {
    public AccountInfoDao(Context context) {
        super(context);
    }

    /**
     * @param accoun 账号信息
     * @throws Exception
     */
    public void saveAccount(Account accoun) throws Exception {
        try {
            db = baseDao.getWritableDatabase();
            String sql = "insert into account (a_id, account_name,account_pwd) values (?,?,?)";
            String[] param = new String[]{"10", accoun.getAccount_name(), accoun.getAccount_pwd()};
            db.execSQL(sql, param);
            Log.i("URL", "保存账号成功");
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
    public Account getAccount(String u_id) throws Exception {
        Cursor cur = null;
        try {
            db = baseDao.getReadableDatabase();
            String[] field = new String[]{u_id};
            cur = db.rawQuery("select * from account where a_id=" + field[0], null);
            int count = cur.getCount();
            if (count == 0) {
                return null;
            }
            Account account = new Account();
            if (cur.moveToFirst()) {
                do {
                    account.setAccount_name(cur.getString(cur.getColumnIndex("account_name")));

                    account.setAccount_pwd(cur.getString(cur.getColumnIndex("account_pwd")));
                } while (cur.moveToNext());
            }
            return account;

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
            String sql = "delete from account";
            db.execSQL(sql);
        } catch (Exception e) {
            throw new Exception("清空失败", e);
        } finally {
            db.close();
        }
    }
}
