package com.store.zerone.zeronestore.db.impl;//package com.catering.zerone.p1mi.db.impl;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.util.Log;
//
//import com.zerone.catering.db.abs.AbstractDao;
//import com.zerone.catering.domain.CategoryGoods;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Administrator on 2017/9/21 0021.
// */
//
//public class CatgoryGoodsDao extends AbstractDao {
//    public CatgoryGoodsDao(Context context) {
//        super(context);
//    }
//
//    /**
//     * 插入数据
//     * @param cy 菜系的实体类
//     * @throws Exception
//     */
//    public void add(CategoryGoods cy) throws Exception {
//        try {
//            db = baseDao.getWritableDatabase();
//            String sql = "insert into categorygoods (id, name,parentid) values (?, ?, ?)";
//            String[] param = new String[] { cy.getId(),cy.getName(),cy.getParentid()};
//            db.execSQL(sql, param);
////            Log.i("URL","分类插入成功");
//        } catch (Exception e) {
//            throw new Exception("插入数据失败", e);
//        } finally {
//            db.close();
//        }
//    }
//
//    /**
//     * 获取所有的菜系
//     * @return
//     * @throws Exception
//     */
//    public List<CategoryGoods> getCategory() throws Exception {
//        Cursor cur = null;
//        List<CategoryGoods> list = new ArrayList<>();
//        try {
//            db = baseDao.getReadableDatabase();
//            cur = db.rawQuery("select * from categorygoods", null);
//            int count = cur.getCount();
//            if(count == 0){
//                return null;
//            }
//            if (cur.moveToFirst()) {
//                do {
//                    CategoryGoods cy  = new CategoryGoods();
//                    cy.setId(cur.getString(cur.getColumnIndex("id")));
//                    cy.setName(cur.getString(cur.getColumnIndex("name")));
//                    cy.setParentid(cur.getString(cur.getColumnIndex("parentid")));
//                    list.add(cy);
//                } while (cur.moveToNext());
//            }
//            return  list;
//        } catch (Exception e) {
//            throw new Exception("获取失败", e);
//        } finally {
//            cur.close();
//            db.close();
//        }
//    }
//
//    /**
//     * 清空表中数据
//     * @throws Exception
//     */
//    public void del() throws Exception{
//        try {
//            db = baseDao.getWritableDatabase();
//            String sql = "delete from categorygoods";
//            db.execSQL(sql);
//        } catch (Exception e) {
//            throw new Exception("清空失败", e);
//        } finally {
//            db.close();
//        }
//    }
//}
