package com.store.zerone.zeronestore.db.impl;//package com.catering.zerone.p1mi.db.impl;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.util.Log;
//
//import com.zerone.catering.db.abs.AbstractDao;
//import com.zerone.catering.domain.FoodItemsBean;
//import com.zerone.catering.domain.GoodsDetails;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Administrator on 2017/9/21 0021.
// */
//
//public class GoodDetailsDao extends AbstractDao {
//    public GoodDetailsDao(Context context) {
//        super(context);
//    }
//
//
//    /**
//     * 插入数据
//     *
//     * @param gd 菜品详情实体类
//     * @throws Exception
//     */
//    public void add(GoodsDetails gd) throws Exception {
//
//        try {
//            db = baseDao.getWritableDatabase();
//            String sql = "insert into gooddetails (goodsid,categoryid,goodsname,price,thumb,hasoption) values (?,?,?,?,?,?)";
//            String[] param = new String[]{gd.getGoodsid(), gd.getCategoryid(), gd.getGoodsname(), gd.getPrice(), gd.getThumb(), gd.getHasoption()};
//            db.execSQL(sql, param);
////            Log.i("URL", "插入成功菜品详情：：锅巴是对的" +
////                    "");
//        } catch (Exception e) {
//            throw new Exception("插入数据失败 锅巴来也", e);
//        } finally {
//            db.close();
//        }
//    }
//
//    /**
//     * 获取分类中的菜品数据
//     * @param
//     * @return
//     * @throws Exception
//     */
//    public List<FoodItemsBean.GoodslistBean> getFoodDetailsDB(String id) throws Exception {
//
//        Cursor cur = null;
//        try {
//            db = baseDao.getReadableDatabase();
////            String[] field = new String[] { id };
//
//            cur = db.rawQuery("select * from gooddetails where categoryid=" + id, null);
////            cur = db.rawQuery("select * from gooddetails where categoryid="+field[0], null);
//            int count = cur.getCount();
//            if (count == 0) {
//                return null;
//            }
//            cur.moveToFirst();
//            List<FoodItemsBean.GoodslistBean> list = new ArrayList<>();
//            if (cur.moveToFirst()) {
//                do {
//                    FoodItemsBean.GoodslistBean gb = new FoodItemsBean.GoodslistBean();
//                    gb.setGoodsid(cur.getString(cur.getColumnIndex("goodsid")));
//                    gb.setGoodsname(cur.getString(cur.getColumnIndex("goodsname")));
//                    gb.setPrice(cur.getString(cur.getColumnIndex("price")));
//                    gb.setThumb(cur.getString(cur.getColumnIndex("thumb")));
//                    gb.setHasoption(cur.getString(cur.getColumnIndex("hasoption")));
//                    gb.setOptions(null);
//                    list.add(gb);
//                } while (cur.moveToNext());
//            }
//            return list;
//        } catch (Exception e) {
//            throw new Exception("获取失败", e);
//        } finally {
//            cur.close();
//            db.close();
//        }
//    }
//
//
//    /**
//     * 清空表中数据
//     *
//     * @throws Exception
//     */
//    public void del() throws Exception {
//        try {
//            db = baseDao.getWritableDatabase();
//            String sql = "delete from gooddetails";
//            db.execSQL(sql);
//        } catch (Exception e) {
//            throw new Exception("清空失败", e);
//        } finally {
//            db.close();
//        }
//    }
//}
