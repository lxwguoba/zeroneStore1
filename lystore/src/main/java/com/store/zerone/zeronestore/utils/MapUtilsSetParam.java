package com.store.zerone.zeronestore.utils;

import android.content.Context;


import com.store.zerone.zeronestore.db.impl.SessionTabeDao;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/17.
 * 把相同的数据封装起来 这样有利于数据的维护
 */

public class MapUtilsSetParam {

    /**
     * 反问网络获取数据 这个是反问时的参数  把相同的参数整合起来；
     * @return map 返回的是带有参数的map 那些参数是不变的 所以集合在一起
     */
    public static Map<String,String > getMap(Context context){
        SessionTabeDao sessionTabeDao  = new SessionTabeDao(context);
        Map<String ,String > map =  new HashMap<>();
        map.put("act", "module");
        map.put("name", "bj_qmxk");
        map.put("do", "app_api");
        try {
            map.put("session",sessionTabeDao.getSession());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
