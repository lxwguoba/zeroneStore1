package com.zerone.store.shopingtime.Utils.payutils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.zerone.store.shopingtime.Utils.AppSharePreferenceMgr;

/**
 * Created by on 2018/3/14 0014 09 35.
 * Author  LiuXingWen
 * 这个用来吊起支付的页面
 */

public class PayUtils {


    /**
     * 拉起支付页面
     *
     * @param money   金额
     * @param context 上下文
     */
    public static void LiftThePayment(String money, Context context) {
        Log.i("UUUU", money);
        double dmoney = Double.parseDouble(money) * 100;
        long mone = new Double(dmoney).longValue();
        Intent intent = new Intent("sunmi.payment.L3");
        String transId = System.currentTimeMillis() + "";
        intent.putExtra("transId", transId);
        intent.putExtra("transType", 0);
        AppSharePreferenceMgr.put(context, "transType", "0");
//        用户自选 这里可以让用户选择  默认的是让用户自己选
        intent.putExtra("paymentType", "1");
        try {
            intent.putExtra("amount", mone);
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("URL", "消费金额有问题");
            return;
        }
        intent.putExtra("appId", context.getPackageName());
        if (Util.isIntentExisting(intent, context)) {
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "此机器上没有安装L3应用", Toast.LENGTH_SHORT).show();
        }
    }

    public static void print(Context context) {
        Intent intent = new Intent("sunmi.payment.L3");
        String transId = System.currentTimeMillis() + "";
        intent.putExtra("transId", transId);
        intent.putExtra("transType", 11);
        intent.putExtra("isLastTrade", true);

        intent.putExtra("appId", context.getPackageName());
        if (Util.isIntentExisting(intent, context)) {
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "此机器上没有安装L3应用", Toast.LENGTH_SHORT).show();
        }
    }
//    public static void getPosInfo(Context context) {
//        Intent intent = new Intent("sunmi.payment.L3");
//        String transId = System.currentTimeMillis() + "";
//        intent.putExtra("transId", transId);
//        intent.putExtra("transType", 13);
//        AppSharePreferenceMgr.put(context,"transType","13");
//        intent.putExtra("appId", context.getPackageName());
//        if (Util.isIntentExisting(intent, context)) {
//            context.startActivity(intent);
//        } else {
//            Toast.makeText(context, "此机器上没有安装L3应用", Toast.LENGTH_SHORT).show();
//        }
//    }
}
