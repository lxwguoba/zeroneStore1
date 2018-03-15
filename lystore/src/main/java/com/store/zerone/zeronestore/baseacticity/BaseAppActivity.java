package com.store.zerone.zeronestore.baseacticity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import android.view.Window;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;
import com.store.zerone.zeronestore.application.MyApplication;

/**
 * Created by on 2017/12/28 0028 16 35.
 * Author  LiuXingWen
 */

public class BaseAppActivity extends Activity {

    private MyApplication baseApp;
    private  BaseAppActivity oContext;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去掉标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#0eb468"));
        if (baseApp == null) {
            // 得到Application对象
            baseApp = (MyApplication) getApplication();
        }
        // 把当前的上下文对象赋值给BaseActivity
        oContext = this;
        // 调用添加方法
        addActivity();
    }
    /**
     *  添加Activity方法
     */
    public void addActivity() {
        //调用myApplication的添加Activity方法
        baseApp.addActivity(oContext);
    }

    /**
     * 销毁当个Activity方法
     */
    public void removeActivity() {
        baseApp.removeActivity(oContext);// 调用myApplication的销毁单个Activity方法
    }

    /**
     * 销毁所有Activity方法
     */
    public void removeALLActivity() {
        // 调用myApplication的销毁所有Activity方法
        baseApp.removeALLActivity();
    }
    /**
     * 把Toast定义成一个方法  可以重复使用，使用时只需要传入需要提示的内容即可
     * @param text
     */
    public void showToast(String text) {
        Toast.makeText(oContext, text, Toast.LENGTH_SHORT).show();
    }
}
