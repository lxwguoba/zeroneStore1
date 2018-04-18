package com.zerone.store.shopingtime.DB.abs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/9/21 0021.
 */

public class BaseDao extends SQLiteOpenHelper {

    private static final String DB_NAME = "zerone_retail.db";

    private static final int DB_VERSION = 1;

    public BaseDao(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //更新时调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //第一次来是调用这个
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建用户信息表
        createUserInfo(db);
        createSaveAccount(db);
//        //分店表的创建
//        createBranchTable(db);
//        //菜品分类的创建
//        createcategorygoodsTable(db);
//        //菜品详情的分类
//        creategooddetailsTable(db);
//        //创建接待员的表
//        createWorkerTable(db);
//        //session保留的数据库
//        createSessionTable(db);
    }

    public void createSaveAccount(SQLiteDatabase db) {
        StringBuffer sb = new StringBuffer();
        sb.append(" create table  if not exists account (");
        sb.append(" 	'a_id' varchar(10) not null,");
        sb.append(" 	'account_name' varchar(20) not null,");
        sb.append(" 	'account_pwd' varchar(20) not null,");
        sb.append("primary key(a_id)");
        sb.append(" );");
        db.execSQL(sb.toString());
    }

    /**
     * 创建用户信息表
     *
     * @param db String  account_id; 账户id
     *           String   account; 账户号
     *           String  organization_id; 组织id
     *           String  uuid; uuid 用来生成token
     */
    public void createUserInfo(SQLiteDatabase db) {
        StringBuffer sb = new StringBuffer();
        sb.append(" create table  if not exists customer (");
        sb.append(" 	'u_id' varchar(10) not null,");
        sb.append(" 	'account_id' varchar(20) not null,");
        sb.append(" 	'account' varchar(20) not null,");
        sb.append(" 	'u_orgid' varchar(100) not null,");
        sb.append(" 	'u_uuid' varchar(100) not null,");
        sb.append("primary key(u_id)");
        sb.append(" );");
        db.execSQL(sb.toString());
    }

    /**
     * 这个是用来存储session
     *
     * @param db
     */
    public void createSessionTable(SQLiteDatabase db) {
        StringBuffer sb = new StringBuffer();
        sb.append(" create table  if not exists sessiondb (");
        sb.append(" 	's_id' varchar(10) not null,");
        sb.append(" 	's_value' varchar(100) not null,");
        sb.append("primary key(s_id)");
        sb.append(" );");
        db.execSQL(sb.toString());
    }

    /**
     * 创建workertable
     *
     * @param db
     */
    private void createWorkerTable(SQLiteDatabase db) {
        StringBuffer sb = new StringBuffer();
        sb.append(" create table  if not exists workertable (");
        sb.append(" 	'workerid' varchar(20) not null,");
        sb.append(" 	'reception_qr' varchar(20) not null,");
        sb.append(" 	'name' varchar(50) not null,");
        sb.append(" 	'icon_thumb' varchar(100) not null,");
        sb.append("primary key(workerid)");
        sb.append(" );");
        db.execSQL(sb.toString());
    }

    /**
     * 菜品详情的表创建
     *
     * @param db
     */
    public void creategooddetailsTable(SQLiteDatabase db) {

        StringBuffer sb = new StringBuffer();
        sb.append(" create table  if not exists gooddetails (");
        sb.append(" 	'goodsid' varchar(20) not null,");
        sb.append(" 	'categoryid' varchar(20) not null,");
        sb.append(" 	'goodsname' varchar(50) not null,");
        sb.append(" 	'price' varchar(50) not null,");
        sb.append(" 	'thumb' varchar(100),");
        sb.append(" 	'hasoption' varchar(10),");
        sb.append(" primary key(goodsid)");
        sb.append(" );");
        db.execSQL(sb.toString());
    }


    /**
     * 创建菜品的分类表创建
     *
     * @param db
     */
    public void createcategorygoodsTable(SQLiteDatabase db) {
        StringBuffer sb = new StringBuffer();
        sb.append(" create table if not exists categorygoods (");
        sb.append(" 	'id' varchar(20) not null,");
        sb.append(" 	'name' varchar(50) not null,");
        sb.append(" 	'parentid' varchar(20),");
        sb.append(" primary key(id)");
        sb.append(" );");
        db.execSQL(sb.toString());
    }

    /**
     * 创建分店表
     *
     * @param db
     */
    public void createBranchTable(SQLiteDatabase db) {
        StringBuffer sb = new StringBuffer();
        sb.append(" create table if not exists branch (");
        sb.append(" 	'id' varchar(20) not null,");
        sb.append(" 	'ztdname' varchar(50) not null,");
        sb.append(" 	'thumb' varchar(100),");
        sb.append(" primary key(id)");
        sb.append(" );");
        db.execSQL(sb.toString());
    }

}
