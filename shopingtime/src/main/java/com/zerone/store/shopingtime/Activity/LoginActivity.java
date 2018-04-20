package com.zerone.store.shopingtime.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zerone.store.shopingtime.BaseActivity.BaseAppActivity;
import com.zerone.store.shopingtime.Bean.UserInfo;
import com.zerone.store.shopingtime.Bean.login.Account;
import com.zerone.store.shopingtime.Contants.IpConfig;
import com.zerone.store.shopingtime.DB.impl.AccountInfoDao;
import com.zerone.store.shopingtime.DB.impl.UserInfoImpl;
import com.zerone.store.shopingtime.R;
import com.zerone.store.shopingtime.Utils.AppSharePreferenceMgr;
import com.zerone.store.shopingtime.Utils.LoadingUtils;
import com.zerone.store.shopingtime.Utils.NetUtils;
import com.zerone.store.shopingtime.Utils.NetworkUtil;
import com.zerone.store.shopingtime.Utils.SystemUIUtils;
import com.zyao89.view.zloading.ZLoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by on 2018/3/29 0029 10 28.
 * Author  LiuXingWen
 */

public class LoginActivity extends BaseAppActivity {

    private ZLoadingDialog loading_dailog;
    private EditText username;
    private EditText password;
    private LoginActivity mContext;
    private AccountInfoDao accountInfoDao;
    private CheckBox login_rember_account;

    private boolean checkboolen = false;
    private ImageView showpassword;
    private boolean showBoolean = false;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    String loginJson = (String) msg.obj;
                    Log.i("URL", "loginJson=" + loginJson);
                    loading_dailog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(loginJson);
                        int status = jsonObject.getInt("status");
                        if (status == 0) {
                            //失败
                            String errormsg = jsonObject.getString("msg");
                            customDialog(errormsg);
                        } else if (status == 1) {
                            JSONObject data = jsonObject.getJSONObject("data");
                            String uuid = data.getString("uuid");
                            UserInfo userInfo = new UserInfo();
                            userInfo.setUuid(data.getString("uuid"));
                            userInfo.setOrganization_id(data.getString("organization_id"));
                            userInfo.setAccount_id(data.getString("account_id"));
                            userInfo.setAccount(data.getString("account"));
                            saveUserInfo(userInfo);
                            //记住用账号
                            Account account = new Account();
                            account.setAccount_name(username.getText().toString());
                            account.setAccount_pwd(password.getText().toString());
                            if (checkboolen) {
                                saveAccountIntoTable(account);
                            } else {
                                clearAccount();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    if (showBoolean) {
                        password.setInputType(InputType.TYPE_CLASS_TEXT);
                    } else {
                        password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    }

                    break;
                case 511:
                    Toast.makeText(LoginActivity.this, "网络超时，请重试", Toast.LENGTH_SHORT).show();
                    loading_dailog.dismiss();
                    break;
            }
        }
    };
    private TextView agreement;
    private ImageView closeactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = LoginActivity.this;
        //页面全屏显示
        SystemUIUtils.setStickFullScreen(getWindow().getDecorView());
        initPosInfo();
        initview();
        action();
    }

    private void initPosInfo() {
        Intent intent = new Intent("sunmi.payment.L3");
        String transId = System.currentTimeMillis() + "";
        intent.putExtra("transId", transId);
        intent.putExtra("transType", 13);
        intent.putExtra("appId", getPackageName());
        if (isIntentExisting(intent)) {
            startActivity(intent);
            AppSharePreferenceMgr.put(LoginActivity.this, "transType", "13");
        } else {
            Toast.makeText(this, "此机器上没有安装L3应用", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 控件的点击事件处理
     */
    private void action() {
        showpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showBoolean) {
                    showBoolean = false;
                } else {
                    showBoolean = true;
                }
                Message message = new Message();
                message.what = 2;
                handler.sendMessage(message);
            }
        });

        closeactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
    }

    private void initview() {
        closeactivity = (ImageView) findViewById(R.id.closeactivity);
        agreement = (TextView) findViewById(R.id.agreement);
        accountInfoDao = new AccountInfoDao(mContext);
        showpassword = (ImageView) findViewById(R.id.showpassword);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login_rember_account = (CheckBox) findViewById(R.id.login_rember_account);
        checkboolen = login_rember_account.isChecked();
        try {
            if (accountInfoDao != null) {
                Account account = accountInfoDao.getAccount("10");
                username.setText(account.getAccount_name());
                password.setText(account.getAccount_pwd());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        findViewById(R.id.loginbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intoLoginAction();
            }
        });
    }

    private void intoLoginAction() {

        String user = username.getText().toString();
        String pwd = password.getText().toString();
        Map<String, String> loginMap = new HashMap<String, String>();
        loginMap.put("account", user);
        loginMap.put("password", pwd);

        //终端号
        String terminalId = (String) AppSharePreferenceMgr.get(LoginActivity.this, "terminalId", "");
        //pos商户号
        String merchantId = (String) AppSharePreferenceMgr.get(LoginActivity.this, "merchantId", "");
        //POS机终端号
        if (terminalId == null && merchantId == null) {
            return;
        }

        Log.i("URL", "pos商户号=====" + merchantId + "终端号=====" + terminalId);
        loginMap.put("terminal_num", terminalId);
        //POS商户号
        loginMap.put("sft_pos_num", merchantId);
        if (!NetworkUtil.isNetworkAvailable(LoginActivity.this)) {
            Toast.makeText(LoginActivity.this, "网络不可用，请检查", Toast.LENGTH_SHORT).show();
            return;
        }
        loading_dailog = LoadingUtils.getDailog(mContext, Color.RED, "登录中。。。。");
        loading_dailog.show();
        NetUtils.netWorkByMethodPost(mContext, loginMap, IpConfig.URL_LOGIN, handler, 0);

    }

    /**
     * 自定义对话框
     */
    private void customDialog(String msg) {
        final Dialog dialog = new Dialog(this, R.style.NormalDialogStyle);
        View view = View.inflate(this, R.layout.activity_dialog_view, null);
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        TextView confirm = (TextView) view.findViewById(R.id.confirm);
        TextView errormsg = (TextView) view.findViewById(R.id.errormsg);
        errormsg.setText(msg);
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
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * 保存账号信息到数据库 用来做返显
     *
     * @param account
     */
    private void saveAccountIntoTable(Account account) {
        try {
            accountInfoDao.saveAccount(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清楚数据库中的表
     */
    private void clearAccount() {
        try {
            accountInfoDao.deltable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 保存用户信息
     */
    private void saveUserInfo(UserInfo userInfo) {
        UserInfoImpl userimpl = new UserInfoImpl(LoginActivity.this);
        try {
            userimpl.deltable();
            userimpl.saveUserInfo(userInfo);
            Intent intent = new Intent(LoginActivity.this, OrderListActvity.class);
            startActivity(intent);
            LoginActivity.this.finish();
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("BBBBB", "保存失败");
        }
    }


    public boolean isIntentExisting(Intent intent) {
        final PackageManager packageManager = getPackageManager();
        List<ResolveInfo> resolveInfo =
                packageManager.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.size() > 0;
    }
}
