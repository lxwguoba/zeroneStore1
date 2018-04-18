package com.zerone.store.shopingtime.Activity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;
import com.zerone.store.shopingtime.Base64AndMD5.CreateToken;
import com.zerone.store.shopingtime.BaseActivity.BaseAppActivity;
import com.zerone.store.shopingtime.Bean.UserInfo;
import com.zerone.store.shopingtime.Contants.IpConfig;
import com.zerone.store.shopingtime.DB.impl.UserInfoImpl;
import com.zerone.store.shopingtime.R;
import com.zerone.store.shopingtime.Utils.LoadingUtils;
import com.zerone.store.shopingtime.Utils.NetUtils;
import com.zyao89.view.zloading.ZLoadingDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by on 2018/3/30 0030 13 54.
 * Author  LiuXingWen
 * 设置页面
 */

public class SystemSettingsActivity extends BaseAppActivity {

    private CheckBox system_login_rember_account;
    private CheckBox system_kaidan;
    private CheckBox system_fkjkc;
    private CheckBox system_xdjkc;
    private ZLoadingDialog loading_dailog;
    private SystemSettingsActivity mContext;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    String kaidanJson = (String) msg.obj;
                    Log.i("URL", "" + kaidanJson);
                    loading_dailog.dismiss();
                    try {
                        JSONObject jfk = new JSONObject(kaidanJson);
                        int status = jfk.getInt("status");
                        if (status == 1) {
                            JSONObject data = jfk.getJSONObject("data");
                            if ("1".equals(data.getString("vfg_value"))) {
                                system_kaidan.setChecked(true);
                            } else if ("2".equals(data.getString("vfg_value"))) {
                                system_kaidan.setChecked(false);
                            }
                        }
                    } catch (JSONException e) {
                    }
                    break;

                case 2:
                    String fkJson = (String) msg.obj;
                    Log.i("URL", "" + fkJson);
                    loading_dailog.dismiss();
                    try {
                        JSONObject jfk = new JSONObject(fkJson);
                        int status = jfk.getInt("status");
                        if (status == 1) {
                            JSONObject data = jfk.getJSONObject("data");
                            if ("1".equals(data.getString("vfg_value"))) {
                                system_kaidan.setChecked(true);
                                system_fkjkc.setChecked(true);
                                system_xdjkc.setChecked(false);
                            } else if ("2".equals(data.getString("vfg_value"))) {
                                system_fkjkc.setChecked(false);
                                system_xdjkc.setChecked(true);
                            }
                        }
                    } catch (JSONException e) {
                    }
                    break;
                case 3:
                    String xdJson = (String) msg.obj;
                    loading_dailog.dismiss();
                    try {
                        JSONObject jfk = new JSONObject(xdJson);
                        int status = jfk.getInt("status");
                        if (status == 1) {
                            JSONObject data = jfk.getJSONObject("data");
                            if ("1".equals(data.getString("vfg_value"))) {
                                system_fkjkc.setChecked(true);
                                system_xdjkc.setChecked(false);
                            } else if ("2".equals(data.getString("vfg_value"))) {
                                system_fkjkc.setChecked(false);
                                system_xdjkc.setChecked(true);
                            }
                        }
                    } catch (JSONException e) {
                    }

                    break;
                case 4:
                    String dJson = (String) msg.obj;
                    loading_dailog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(dJson);
                        Log.i("URL", "" + jsonObject);
                        int status = jsonObject.getInt("status");
                        if (status == 0) {
                            Toast.makeText(mContext, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                        } else if (status == 1) {
                            JSONObject data1 = jsonObject.getJSONObject("data");
                            JSONArray data = data1.getJSONArray("cfglist");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject jbe = data.getJSONObject(i);
                                if ("allow_zero_stock".equals(jbe.getString("cfg_name"))) {
                                    //开启/关闭零库存开单接口
                                    String cfg_value = jbe.getString("cfg_value");
                                    if ("1".equals(cfg_value)) {
                                        //开启0库存开单   值为  1
                                        system_kaidan.setChecked(true);
                                    } else if ("2".equals(cfg_value)) {
                                        //关闭0库存开单  值为  2
                                        system_kaidan.setChecked(false);
                                    }
                                } else if ("change_stock_role".equals(jbe.getString("cfg_name"))) {
                                    //下单减库存
                                    String cfg_value = jbe.getString("cfg_value");
                                    if ("1".equals(cfg_value)) {
                                        //   //付款后减库存   值为  1
                                        system_fkjkc.setChecked(true);
                                        system_xdjkc.setChecked(false);
                                    } else if ("2".equals(cfg_value)) {
                                        //下单后减库存  值为  2
                                        system_xdjkc.setChecked(true);
                                        system_fkjkc.setChecked(false);
                                    }
                                }
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case 511:
                    Toast.makeText(SystemSettingsActivity.this, "网络超时，请重试", Toast.LENGTH_SHORT).show();
                    loading_dailog.dismiss();
                    break;
            }
        }
    };
    private UserInfo userInfo;
    private ImageView system_back;
    private Button systemout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systemsettings);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#ffffff"));
        mContext = SystemSettingsActivity.this;
        initGetUserInfo();
        initView();
        aciton();
        initCheckBoxStates();
        checkBoxAction();
    }

    private void aciton() {
        system_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SystemSettingsActivity.this.finish();
            }
        });
        systemout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog();
            }
        });
    }

    private void initCheckBoxStates() {
        //默认是没有开启的
        String timestamp = System.currentTimeMillis() + "";
        String token = CreateToken.createToken(userInfo.getUuid(), timestamp, userInfo.getAccount());
        Map<String, String> kdMap = new HashMap<String, String>();
        kdMap.put("account_id", userInfo.getAccount_id());
        kdMap.put("organization_id", userInfo.getOrganization_id());
        kdMap.put("token", token);
        kdMap.put("timestamp", timestamp);
        loading_dailog = LoadingUtils.getDailog(mContext, Color.RED, "修改中。。。。");
        loading_dailog.show();
        NetUtils.netWorkByMethodPost(mContext, kdMap, IpConfig.URL_DPSZ, handler, 4);
    }

    /**
     * 获取用户信息
     */
    private void initGetUserInfo() {
        UserInfoImpl userInfoImpl = new UserInfoImpl(mContext);
        try {
            userInfo = userInfoImpl.getUserInfo("10");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * view的初始
     */
    private void initView() {
        system_login_rember_account = (CheckBox) findViewById(R.id.system_login_rember_account);
        system_kaidan = (CheckBox) findViewById(R.id.system_kaidan);
        system_fkjkc = (CheckBox) findViewById(R.id.system_fkjkc);
        system_xdjkc = (CheckBox) findViewById(R.id.system_xdjkc);
        system_back = (ImageView) findViewById(R.id.system_back);
        systemout = (Button) findViewById(R.id.systemout);
    }

    /**
     * 点击checkbox的提交信息到服务器
     */
    private void checkBoxAction() {
        system_kaidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //默认是没有开启的
                String timestamp = System.currentTimeMillis() + "";
                String token = CreateToken.createToken(userInfo.getUuid(), timestamp, userInfo.getAccount());
                Map<String, String> kdMap = new HashMap<String, String>();
                kdMap.put("account_id", userInfo.getAccount_id());
                kdMap.put("organization_id", userInfo.getOrganization_id());
                if (system_kaidan.isChecked()) {
                    //开启0库存开单   值为  1
                    kdMap.put("cfg_value", "1");
                } else {
                    //关闭0库存开单  值为  2
                    kdMap.put("cfg_value", "2");
                }
                kdMap.put("token", token);
                kdMap.put("timestamp", timestamp);
                loading_dailog = LoadingUtils.getDailog(mContext, Color.RED, "修改中。。。。");
                loading_dailog.show();
                NetUtils.netWorkByMethodPost(mContext, kdMap, IpConfig.URL_KQLKC, handler, 0);
            }
        });

        system_fkjkc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //默认是没有开启的
                String timestamp = System.currentTimeMillis() + "";
                String token = CreateToken.createToken(userInfo.getUuid(), timestamp, userInfo.getAccount());
                Map<String, String> kdMap = new HashMap<String, String>();
                kdMap.put("account_id", userInfo.getAccount_id());
                kdMap.put("organization_id", userInfo.getOrganization_id());
                kdMap.put("cfg_value", "1");
//                if (system_kaidan.isChecked()){
//                    //付款后减库存   值为  1
//                    kdMap.put("cfg_value","1");
//                }else {
//                    //下单后减库存  值为  2
//                    kdMap.put("cfg_value","2");
//                }
                kdMap.put("token", token);
                kdMap.put("timestamp", timestamp);
                loading_dailog = LoadingUtils.getDailog(mContext, Color.RED, "修改中。。。。");
                loading_dailog.show();
                NetUtils.netWorkByMethodPost(mContext, kdMap, IpConfig.URL_FKJKC, handler, 2);
            }
        });
        system_xdjkc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //默认是没有开启的
                String timestamp = System.currentTimeMillis() + "";
                String token = CreateToken.createToken(userInfo.getUuid(), timestamp, userInfo.getAccount());
                Map<String, String> kdMap = new HashMap<String, String>();
                kdMap.put("account_id", userInfo.getAccount_id());
                kdMap.put("organization_id", userInfo.getOrganization_id());
//                if (system_kaidan.isChecked()){
//                    //付款后减库存   值为  1
//                    kdMap.put("cfg_value","1");
//                }else {
//                    //下单后减库存  值为  2
//                    kdMap.put("cfg_value","2");
//                }
                kdMap.put("cfg_value", "2");
                kdMap.put("token", token);
                kdMap.put("timestamp", timestamp);
                loading_dailog = LoadingUtils.getDailog(mContext, Color.RED, "修改中。。。。");
                loading_dailog.show();
                NetUtils.netWorkByMethodPost(mContext, kdMap, IpConfig.URL_FKJKC, handler, 3);
            }
        });
    }

    /**
     * 自定义对话框
     */
    private void customDialog() {
        final Dialog dialog = new Dialog(this, R.style.NormalDialogStyle);
        View view = View.inflate(this, R.layout.activity_dialog_out_view, null);
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        TextView confirm = (TextView) view.findViewById(R.id.confirm);
        dialog.setContentView(view);
        //使得点击对话框外部不消失对话框
        dialog.setCanceledOnTouchOutside(true);
        //设置对话框的大小
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SystemSettingsActivity.this.finish();
                System.exit(0);
            }
        });
        dialog.show();
    }


}
