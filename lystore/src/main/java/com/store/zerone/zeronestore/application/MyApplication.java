package com.store.zerone.zeronestore.application;
import android.app.Activity;
import android.app.Application;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.store.zerone.zeronestore.utils.AidlUtil;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by on 2017/1/23 0028 10 55.
 * Author  LiuXingWen
 */

public class MyApplication extends Application {
    /**
     * 请求栈
     */
    private boolean isAidl;
    public static RequestQueue queues;
    //用于存放所有启动的Activity的集合 便于删除
    private List<Activity> activityList;
    @Override
    public void onCreate() {
        super.onCreate();
        activityList=new ArrayList<Activity>();
        ZXingLibrary.initDisplayOpinion(this);
        queues = Volley.newRequestQueue(getApplicationContext());
        isAidl = true;
        AidlUtil.getInstance().connectPrinterService(this);
    }

    public static RequestQueue getQueues() {
        return queues;
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
