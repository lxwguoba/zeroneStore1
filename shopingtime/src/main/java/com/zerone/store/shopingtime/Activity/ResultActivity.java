package com.zerone.store.shopingtime.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.qzs.voiceannouncementlibrary.VoiceUtils;
import com.zerone.store.shopingtime.Base64AndMD5.CreateToken;
import com.zerone.store.shopingtime.Bean.UserInfo;
import com.zerone.store.shopingtime.Bean.print.PrintBean;
import com.zerone.store.shopingtime.Bean.print.PrintItem;
import com.zerone.store.shopingtime.Contants.IpConfig;
import com.zerone.store.shopingtime.Contants.StringData;
import com.zerone.store.shopingtime.DB.impl.UserInfoImpl;
import com.zerone.store.shopingtime.R;
import com.zerone.store.shopingtime.Utils.AppSharePreferenceMgr;
import com.zerone.store.shopingtime.Utils.LoadingUtils;
import com.zerone.store.shopingtime.Utils.NetUtils;
import com.zerone.store.shopingtime.Utils.UtilsTime;
import com.zerone.store.shopingtime.Utils.printutils.PrintUtils;
import com.zyao89.view.zloading.ZLoadingDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xurong on 2017/5/15.
 */

public class ResultActivity extends AppCompatActivity {

    private static final String TAG = "ResultActivity";

    TextView result_tv;
    private ImageView result_iv;
    private TextView result_info;
    private int errorCode;
    private TextView pay_complete;
    private int paymentType;
    private UserInfo userInfo;
    private ZLoadingDialog loading_dailog;
    private ResultActivity mContext;
    Handler handler = new Handler() {
        private List<PrintItem> printItemList;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    loading_dailog.dismiss();
                    String upDataStates = (String) msg.obj;
                    AppSharePreferenceMgr.remove(ResultActivity.this, "orderid");
                    break;
                case 2:
                    String upprint = (String) msg.obj;
                    loading_dailog.dismiss();
                    PrintBean printBean = new PrintBean();
                    printItemList = new ArrayList<>();
                    try {
                        JSONObject jsonObject = new JSONObject(upprint);
                        int status = jsonObject.getInt("status");
                        if (status == 1) {
                            //封装打印类 的数据
                            long aLong = jsonObject.getJSONObject("data").getJSONObject("orderdata").getLong("created_at");
                            //下单时间
                            String otime = UtilsTime.getTime(aLong);
                            printBean.setCreateTime(otime);
                            printBean.setOrdersn(jsonObject.getJSONObject("data").getJSONObject("orderdata").getString("ordersn"));
                            int payStatus = jsonObject.getJSONObject("data").getJSONObject("orderdata").getInt("status");
                            if (payStatus == -1) {
                                printBean.setOrderTuype("取消状态");
                            } else if (payStatus == 0) {
                                printBean.setOrderTuype("待付款");
                            } else if (payStatus == 1) {
                                printBean.setOrderTuype("已付款");
                            }
                            printBean.setPmoney(jsonObject.getJSONObject("data").getJSONObject("orderdata").getString("order_price"));
                            printBean.setRemark(jsonObject.getJSONObject("data").getJSONObject("orderdata").getString("remarks"));
                            /**
                             *  这个是商品列表
                             */
                            JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("ordergoods");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject Item = jsonArray.getJSONObject(i);
                                PrintItem printItem = new PrintItem();
                                printItem.setGcount(Item.getString("total"));
                                printItem.setGoodsname(Item.getString("title"));
                                printItem.setGprice(Item.getString("price"));
                                printItemList.add(printItem);
                            }
                            printBean.setList(printItemList);
                            if (printBean != null) {
                                PrintUtils.print(StringData.STORE_NAME, printBean);
                            }
                            goUpDataOrderState();
                        } else if (status == 0) {
                            Toast.makeText(mContext, "没有这个订单，打印失败", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case 511:
                    Toast.makeText(ResultActivity.this, "网络超时，请重试", Toast.LENGTH_SHORT).show();
                    loading_dailog.dismiss();
                    break;
            }
        }
    };
    private String orderid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mContext = this;
        initGetUserInfo();
        initView();
        action();

    }

    private void action() {
        pay_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultActivity.this.finish();
            }
        });
    }

    private void initView() {
        result_tv = (TextView) findViewById(R.id.result_tv);
        result_iv = (ImageView) findViewById(R.id.result_iv);
        result_info = (TextView) findViewById(R.id.result_info);
        pay_complete = (TextView) findViewById(R.id.pay_complete);
        int resultCode = getIntent().getIntExtra("resultCode", -1);
        String errorMsg = getIntent().getStringExtra("errorMsg");
        String answerCode = getIntent().getStringExtra("answerCode");
        String resultInfo = getIntent().getStringExtra("resultInfo");
        paymentType = getIntent().getIntExtra("paymentType", 10);
        String money = getIntent().getStringExtra("money");
        errorCode = getIntent().getIntExtra("errorCode", 0);
        if (resultCode == 0) {
            // 交易成功
            result_info.setText("支付成功！");
            double dmoney = Double.parseDouble(money) / 100;
            //语音播报
            VoiceUtils.with(this).Play(dmoney + "", true);
            //打印小票  获取订单详情
            orderid = (String) AppSharePreferenceMgr.get(ResultActivity.this, "orderid", "");
            gotoPrint(orderid);
        } else if (resultCode == -1) {
            // 交易失败
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
            Log.e(TAG, errorMsg);
            result_info.setText("支付失败！");
            result_info.setTextColor(Color.parseColor("#ff0000"));
            result_iv.setImageResource(R.mipmap.pay_fails);
            if (errorCode == 401) {
                result_tv.setText("交易失败");
            } else if (errorCode == 418) {
                result_tv.setText("支付取消");
            } else {
                result_tv.setText("支付失败！！！！");
            }
            result_tv.setText("Result:" + resultInfo);
        }
    }

    /**
     * 获取订单详情打印
     *
     * @param orderid
     */
    private void gotoPrint(String orderid) {

        if (userInfo == null) {
            return;
        }
        String timestamp = System.currentTimeMillis() + "";
        String token = CreateToken.createToken(userInfo.getUuid(), timestamp, userInfo.getAccount());
        Map<String, String> getOrderDetails = new HashMap<String, String>();
        getOrderDetails.put("account_id", userInfo.getAccount_id());
        getOrderDetails.put("organization_id", userInfo.getOrganization_id());
        getOrderDetails.put("order_id", orderid);
        getOrderDetails.put("token", token);
        getOrderDetails.put("timestamp", timestamp);
        loading_dailog = LoadingUtils.getDailog(mContext, Color.RED, "获取订单中。。。。");
        loading_dailog.show();
        NetUtils.netWorkByMethodPost(mContext, getOrderDetails, IpConfig.URL_ORDERDETAILS, handler, 2);

    }

    /**
     * 发送数据给服务器修改订单状态
     */
    private void goUpDataOrderState() {
        if (userInfo == null) {
            return;
        }
        String timestamp = System.currentTimeMillis() + "";
        String token = CreateToken.createToken(userInfo.getUuid(), timestamp, userInfo.getAccount());
        Map<String, String> loginMap = new HashMap<String, String>();
        loginMap.put("organization_id", userInfo.getOrganization_id());
        loginMap.put("account_id", userInfo.getAccount_id());
        loginMap.put("token", token);
        String orderid = (String) AppSharePreferenceMgr.get(ResultActivity.this, "orderid", "");
        Log.i("URL", "orderid==" + orderid);
        if (orderid != null && orderid.length() > 0) {
            Log.i("URL", "orderid=========" + orderid);
            loginMap.put("order_id", orderid);
        }
        Log.i("URL", "paymentType=" + paymentType);
        loginMap.put("paytype", paymentType + "");
        loginMap.put("timestamp", timestamp);
        loginMap.put("payment_company", "盛付通支付");
        loading_dailog = LoadingUtils.getDailog(ResultActivity.this, Color.RED, "整理数据中。。。");
        loading_dailog.show();
        NetUtils.netWorkByMethodPost(ResultActivity.this, loginMap, IpConfig.URL_UPDATAPAY, handler, 0);
    }

    /**
     * 获取用户信息
     */
    private void initGetUserInfo() {
        UserInfoImpl userInfoImpl = new UserInfoImpl(ResultActivity.this);
        try {
            userInfo = userInfoImpl.getUserInfo("10");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
