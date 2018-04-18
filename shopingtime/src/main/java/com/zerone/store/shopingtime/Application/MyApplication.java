package com.zerone.store.shopingtime.Application;

import android.app.Activity;
import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.zerone.store.shopingtime.Utils.printutils.AidlUtil;

import java.util.ArrayList;
import java.util.List;

//import com.android.volley.RequestQueue;
//import com.android.volley.toolbox.Volley;
//import com.zerone.store.shopingtime.Utils.AidlUtil;

/**
 * Created by on 2018/3/29 0029 10 12.
 * Author  LiuXingWen
 */

public class MyApplication extends Application {

    public static RequestQueue queues;
    /**
     * 请求栈
     */
    private boolean isAidl;
    //用于存放所有启动的Activity的集合 便于删除
    private List<Activity> activityList;

    public static RequestQueue getQueues() {
        return queues;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        activityList = new ArrayList<Activity>();
        queues = Volley.newRequestQueue(getApplicationContext());
        isAidl = true;
        AidlUtil.getInstance().connectPrinterService(this);
    }

    /**
     * 添加Activity
     */
    public void addActivity(Activity activity) {
        // 判断当前集合中不存在该Activity
        if (!activityList.contains(activity)) {
            activityList.add(activity);//把当前Activity添加到集合中
        }
    }

    /**
     * 销毁单个Activity
     */
    public void removeActivity(Activity activity) {
        //判断当前集合中存在该Activity
        if (activityList.contains(activity)) {
            activityList.remove(activity);//从集合中移除
            activity.finish();//销毁当前Activity
        }
    }


    /**
     * 销毁所有的Activity
     */
    public void removeALLActivity() {
        //通过循环，把集合中的所有Activity销毁
        for (Activity activity : activityList) {
            activity.finish();
        }
    }


    public boolean isAidl() {
        return isAidl;
    }

    public void setAidl(boolean aidl) {
        isAidl = aidl;
    }
}
