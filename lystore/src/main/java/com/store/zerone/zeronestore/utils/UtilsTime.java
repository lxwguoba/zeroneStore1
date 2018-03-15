package com.store.zerone.zeronestore.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/18.
 * 将毫秒数转换为时间值
 */

public class UtilsTime {
    public static String getTime(Long longtime) {
        long l =longtime*1000;
        Date nowTime = new Date(l);
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String retStrFormatNowDate = sdFormatter.format(nowTime);
        return retStrFormatNowDate;

    }

    public static String getNowTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        // new Date()为获取当前系统时间
        return df.format(new Date());
    }
}
