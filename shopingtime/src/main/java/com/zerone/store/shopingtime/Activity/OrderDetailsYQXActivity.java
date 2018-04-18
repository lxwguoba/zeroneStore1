package com.zerone.store.shopingtime.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;
import com.zerone.store.shopingtime.Adapter.cart_list.OrderDetialsListItemAdapter;
import com.zerone.store.shopingtime.Base64AndMD5.CreateToken;
import com.zerone.store.shopingtime.Bean.UserInfo;
import com.zerone.store.shopingtime.Bean.order.GoodsBean;
import com.zerone.store.shopingtime.Contants.IpConfig;
import com.zerone.store.shopingtime.DB.impl.UserInfoImpl;
import com.zerone.store.shopingtime.R;
import com.zerone.store.shopingtime.Utils.LoadingUtils;
import com.zerone.store.shopingtime.Utils.NetUtils;
import com.zerone.store.shopingtime.Utils.UtilsTime;
import com.zyao89.view.zloading.ZLoadingDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by on 2018/4/2 0002 19 32.
 * Author  LiuXingWen
 */

public class OrderDetailsYQXActivity extends AppCompatActivity {

    private ListView listView;
    private OrderDetailsYQXActivity mContext;
    private UserInfo userInfo;
    private ZLoadingDialog loading_dailog;
    private Intent intent;
    private OrderDetialsListItemAdapter odlia;
    private List<GoodsBean> list;
    private TextView totalmoney;
    //    private TextView zhifufangshi;
    private TextView xiaofeizhe;
    private TextView jiedaiy;
    private TextView ordersn;
    private TextView beizhu;
    private ImageView back;
    private TextView ordertime;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    String yqxJSon = (String) msg.obj;
                    loading_dailog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(yqxJSon);
                        int status = jsonObject.getInt("status");
                        if (status == 1) {
                            long aLong = jsonObject.getJSONObject("data").getJSONObject("orderdata").getLong("created_at");
                            //下单时间
                            String otime = UtilsTime.getTime(aLong);
                            JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("ordergoods");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                GoodsBean gb = new GoodsBean();
                                gb.setGoods_total(jsonArray.getJSONObject(i).getString("total"));
                                gb.setGoods_price(jsonArray.getJSONObject(i).getString("price"));
                                gb.setGoods_title(jsonArray.getJSONObject(i).getString("title"));
                                gb.setGoods_details(jsonArray.getJSONObject(i).getString("details"));
                                gb.setGoods_id(jsonArray.getJSONObject(i).getString("goods_id"));
                                gb.setGoods_thumb(jsonArray.getJSONObject(i).getString("thumb"));
                                list.add(gb);
                            }
                            totalmoney.setText("￥" + jsonObject.getJSONObject("data").getJSONObject("orderdata").getString("order_price"));
                            String paytype = jsonObject.getJSONObject("data").getJSONObject("orderdata").getString("paytype");
//                            if ("-1".equals(paytype)){
//                                zhifufangshi.setText("现金支付");
//                            }else if ("0".equals(paytype)){
//                                zhifufangshi.setText("银行卡支付");
//                            }else if ("1".equals(paytype)){
//                                zhifufangshi.setText("支付宝扫码");
//                            }else if ("2".equals(paytype)){
//                                zhifufangshi.setText("支付宝二维码");
//                            }else if ("3".equals(paytype)){
//                                zhifufangshi.setText("微信扫码");
//                            }else if ("4".equals(paytype)){
//                                zhifufangshi.setText("微信二维码");
//                            }
                            xiaofeizhe.setText("散客");
                            ordertime.setText(otime);
                            jiedaiy.setText(jsonObject.getJSONObject("data").getJSONObject("orderdata").getString("operator_account"));
                            ordersn.setText(jsonObject.getJSONObject("data").getJSONObject("orderdata").getString("ordersn"));
                            beizhu.setText(jsonObject.getJSONObject("data").getJSONObject("orderdata").getString("remarks"));
                        } else if (status == 0) {

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } finally {
                        odlia.notifyDataSetChanged();
                    }
                    break;
                case 511:
                    Toast.makeText(OrderDetailsYQXActivity.this, "网络超时，请重试", Toast.LENGTH_SHORT).show();
                    loading_dailog.dismiss();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails_yqx);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#ffffff"));
        intent = getIntent();
        list = new ArrayList<>();
        mContext = this;
        initGetUserInfo();
        initView();
        initGetDataOrderDetails();
        action();

    }

    private void action() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderDetailsYQXActivity.this.finish();
            }
        });
    }

    /**
     * 初始化view
     */
    private void initView() {
        totalmoney = (TextView) findViewById(R.id.totalmoney);
//        zhifufangshi = (TextView) findViewById(R.id.zhifufangshi);
        xiaofeizhe = (TextView) findViewById(R.id.xiaofeizhe);
        jiedaiy = (TextView) findViewById(R.id.jiedaiy);
        ordersn = (TextView) findViewById(R.id.ordersn);
        beizhu = (TextView) findViewById(R.id.beizhu);
        back = (ImageView) findViewById(R.id.back);
        ordertime = (TextView) findViewById(R.id.ordertime);
        listView = (ListView) findViewById(R.id.goodslist);
        odlia = new OrderDetialsListItemAdapter(this, list);
        listView.setAdapter(odlia);

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

    private void initGetDataOrderDetails() {
        //默认是没有开启的
        String timestamp = System.currentTimeMillis() + "";
        String token = CreateToken.createToken(userInfo.getUuid(), timestamp, userInfo.getAccount());
        Map<String, String> getOrderDetails = new HashMap<String, String>();
        getOrderDetails.put("account_id", userInfo.getAccount_id());
        getOrderDetails.put("organization_id", userInfo.getOrganization_id());
        getOrderDetails.put("order_id", intent.getStringExtra("orderid"));
        getOrderDetails.put("token", token);
        getOrderDetails.put("timestamp", timestamp);
        loading_dailog = LoadingUtils.getDailog(mContext, Color.RED, "获取订单详情中。。。。");
        loading_dailog.show();
        NetUtils.netWorkByMethodPost(mContext, getOrderDetails, IpConfig.URL_ORDERDETAILS, handler, 0);
    }
}
