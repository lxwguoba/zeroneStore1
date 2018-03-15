package com.store.zerone.zeronestore.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.qzs.voiceannouncementlibrary.VoiceUtils;
import com.google.gson.Gson;
import com.store.zerone.zeronestore.MainActivity;
import com.store.zerone.zeronestore.R;
import com.store.zerone.zeronestore.Util;
import com.store.zerone.zeronestore.baseacticity.BaseAppActivity;
import com.store.zerone.zeronestore.contanst.ContantData;
import com.store.zerone.zeronestore.contanst.IpConfig;
import com.store.zerone.zeronestore.db.impl.SessionTabeDao;
import com.store.zerone.zeronestore.domain.SessionBean;
import com.store.zerone.zeronestore.domain.UserInfo;
import com.store.zerone.zeronestore.payutils.PayUtils;
import com.store.zerone.zeronestore.utils.GetGson;
import com.store.zerone.zeronestore.utils.LoadingUtils;
import com.store.zerone.zeronestore.utils.Utils;
import com.store.zerone.zeronestore.utils.net.HttpRequestDealOutTime;
import com.zyao89.view.zloading.ZLoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by on 2018/1/23 0023 09 49.
 * Author  LiuXingWen
 */

public class LoginActivity extends BaseAppActivity {
    //用户名
    private EditText user_name;
    //密码
    private EditText user_pwd;
    //记住密码
    private CheckBox re_box;
    //登录按钮
    private LinearLayout login_btn;
    private ZLoadingDialog dailog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        doListenner();
    }

    /**
     * 按钮的点击事件
     */
    private void doListenner() {
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotologdata();
            }
        });
    }

    /**
     * 获取数据并登录
     */
    private void gotologdata() {
        //1、获取用户名和密码 并判断是否是记住用户名
        String username = user_name.getText().toString().trim();
        String userpwd = user_pwd.getText().toString().toString().trim();
        if (username != null && username.length() > 0) {
            if (userpwd != null && userpwd.length() > 0) {
                //登录成功后判断是否是记住密码 若是择保存用户名和密码否则不用
                String url = IpConfig.URL+"act=module&name=bj_qmxk&do=app_api&opp=login&username="+username+"&password="+userpwd;
                Log.i("URL",url+"");
                dailog = LoadingUtils.getDailog(LoginActivity.this, Color.RED, "正在登陆中。。。。");
                dailog.show();
                HttpRequestDealOutTime.HttpGet(url,LoginActivity.this,handler, ContantData.LOGINRESPONSE);
            } else {
                Toast.makeText(this, "用户密码不能为空，请输入", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "用户名不能为空，请输入", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * View的初始化
     */
    private void initView() {
        String namepwd = Utils.getACache(LoginActivity.this).getAsString("namepwd");
        user_name = (EditText) findViewById(R.id.username);
        user_pwd = (EditText) findViewById(R.id.userpwd);
        if (namepwd!=null&&namepwd.length()>0){
            String[] split = namepwd.split("-");
             user_name.setText(split[0]);
             user_pwd.setText(split[1]);
        }
        re_box = (CheckBox) findViewById(R.id.rember_user);
        login_btn = (LinearLayout) findViewById(R.id.login_btn);
    }
    Handler  handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case ContantData.LOGINRESPONSE:
                    String jsonSt = (String) msg.obj;

                    try {
                        JSONObject jsonObject  = new JSONObject(jsonSt);
                        int loginstatus = jsonObject.getInt("loginstatus");
                        if (loginstatus==1){
                            Gson gson= GetGson.getGson();
                            UserInfo user= gson.fromJson(jsonSt, UserInfo.class);
                            addSessionIntoDB(user.getSession());

                        }else {

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    /**
     * 将session保存到数据库中
     * @param session
     */
    private void addSessionIntoDB(String session) {
        SessionTabeDao std= new SessionTabeDao(this);
        try {
            String username = user_name.getText().toString().trim();
            String userpwd = user_pwd.getText().toString().toString().trim();
            std.del();
            SessionBean sessionBean = new SessionBean("0",session);
            std.addSession(sessionBean);
            Intent intent =new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
//            PayUtils.pullUPPay("0.01",this);
//              PayUtils.print(this);
            //语音播报
//            VoiceUtils.with(this).Play(0.01+"",true);
            finish();
            if (re_box.isChecked()){
                String  namepwd=username+"-"+userpwd;
                Utils.getACache(LoginActivity.this).put("namepwd",namepwd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "登录失败哦", Toast.LENGTH_SHORT).show();
        }finally {
            dailog.dismiss();
        }
    }



}
