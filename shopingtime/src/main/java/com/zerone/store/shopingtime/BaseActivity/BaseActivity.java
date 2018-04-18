package com.zerone.store.shopingtime.BaseActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;
import com.zerone.store.shopingtime.Application.MyApplication;
import com.zerone.store.shopingtime.Bean.UserInfo;
import com.zerone.store.shopingtime.DB.impl.UserInfoImpl;

/**
 * Created by on 2018/3/29 0029 10 25.
 * Author  LiuXingWen
 */

public class BaseActivity extends Activity {

    private MyApplication application;
    private BaseActivity oContext;
    private UserInfo userInfo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initGetUserInfo(this);
        //去掉标题栏8be7b2
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#8be7b2"));
        //        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        if (application == null) {
            // 得到Application对象
            application = (MyApplication) getApplication();
        }
        // 把当前的上下文对象赋值给BaseActivity
        oContext = this;
        // 调用添加方法
        addActivity();
    }

    /**
     * 添加Activity方法
     */
    public void addActivity() {
        //调用myApplication的添加Activity方法
        application.addActivity(oContext);
    }

    /**
     * 销毁当个Activity方法
     */
    public void removeActivity() {
        application.removeActivity(oContext);// 调用myApplication的销毁单个Activity方法
    }

    /**
     * 销毁所有Activity方法
     */
    public void removeALLActivity() {
        // 调用myApplication的销毁所有Activity方法
        application.removeALLActivity();
    }

    /**
     * 把Toast定义成一个方法  可以重复使用，使用时只需要传入需要提示的内容即可
     *
     * @param text
     */
    public void showToast(String text) {
        Toast.makeText(oContext, text, Toast.LENGTH_SHORT).show();
    }


    private void initGetUserInfo(Context mContext) {
        UserInfoImpl userInfoImpl = new UserInfoImpl(mContext);
        try {
            userInfo = userInfoImpl.getUserInfo("10");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
