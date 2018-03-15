package com.store.zerone.zeronestore.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;

import com.store.zerone.zeronestore.domain.Branch;


/**
 * Created by Administrator on 2017/6/28.
 */

public class Utils {
    /**
     * 判断wifi是否链接
     * @param inContext
     * @return
     */
    public static boolean isWiFiActive(Context inContext) {
        WifiManager mWifiManager = (WifiManager) inContext
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = mWifiManager.getConnectionInfo();
        int ipAddress = wifiInfo == null ? 0 : wifiInfo.getIpAddress();
        if (mWifiManager.isWifiEnabled() && ipAddress != 0) {
            Toast.makeText(inContext,"wifi可以使用", Toast.LENGTH_LONG).show();
            return true;
        } else {
            Toast.makeText(inContext,"没有链接wifi，请检查网络", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    /**
     * 判断是wifi、移动数据流量 是否连接
     * @param context
     * @return
     */
    public static boolean checkNetworkConnection(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo wifi =connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        final NetworkInfo mobile =connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        //getState()方法是查询是否连接了数据网络
        if(wifi.isAvailable()||mobile.isAvailable()) {

                return true;

            } else{

            return false;

            }

    }







    /**
     *  判断网络是否已经连接
     * @return
     */
    public boolean isNetworkConnected(Context inContext) {
        ConnectivityManager cm = (ConnectivityManager) inContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    /**
     *  获得网络工作的类型
     * @return
     */
    public int getNetworkType(Context inContext) {
        int netType = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager) inContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
//            if (!StringUtils.isEmpty(extraInfo)) {
//                if (extraInfo.toLowerCase().equals("cmnet")) {
//                    netType =0;
//                } else {
//                    netType = 0;
//                }
//            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType =1;
        }
        return netType;
    }

    /**
     *
     * 获取session的值
     * @param context 通过上下文获取
     * @return session 返回登录时获取的session值
     *
     */
    public static String getSeesion(Context context){

        return getACache(context).getAsString("session");

    }


    /**
     *
     * 获取缓存的对象
     * @param context
     * @return  ACache 缓存对象
     *
     */
    public  static ACache getACache(Context context){
         return ACache.get(context);
    }


    /**
     * 获取分店实体类
     * @param context
     * @return  branch 分店实体类
     */
    public  static Branch getBranch(Context context){
        return (Branch) getACache(context).getAsObject("branch");
    }


}
