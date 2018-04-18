package com.zerone.store.shopingtime.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zerone.store.shopingtime.Adapter.cart_list.OrderListItemAdapter;
import com.zerone.store.shopingtime.Base64AndMD5.CreateToken;
import com.zerone.store.shopingtime.Bean.UserInfo;
import com.zerone.store.shopingtime.Bean.order.OrderBean;
import com.zerone.store.shopingtime.Contants.IpConfig;
import com.zerone.store.shopingtime.DB.impl.UserInfoImpl;
import com.zerone.store.shopingtime.R;
import com.zerone.store.shopingtime.Utils.DoubleUtils;
import com.zerone.store.shopingtime.Utils.LoadingUtils;
import com.zerone.store.shopingtime.Utils.NetUtils;
import com.zyao89.view.zloading.ZLoadingDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by on 2018/3/30 0030 13 52.
 * Author  LiuXingWen
 * 订单类表
 */

public class TheOrderListActivity extends AppCompatActivity {

    private ListView orderlistview;
    private List<OrderBean> list;
    private OrderListItemAdapter orderListItemAdapter;
    private TextView alltvorder;
    private TextView alltvdfk;
    private TextView alltvywc;
    private TextView alltvyqx;
    private View alltvorder_line;
    private View alltvdfk_line;
    private View alltvywc_line;
    private View alltvyqx_line;
    private UserInfo userInfo;
    private TheOrderListActivity mContext;
    private ZLoadingDialog loading_dailog;
    private TextView orderTotal;
    private TextView orderTotalPrice;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    list.clear();
                    String orderListJSon = (String) msg.obj;
                    Log.i("URL", orderListJSon);
                    loading_dailog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(orderListJSon);
                        int status = jsonObject.getInt("status");
                        if (status == 1) {
                            JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("orderlist");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject orderbean = jsonArray.getJSONObject(i);
                                OrderBean ob = new OrderBean();
                                ob.setId(orderbean.getString("id"));
                                ob.setStatus(orderbean.getString("status"));
                                ob.setOrder_price(orderbean.getString("order_price"));
                                ob.setOrdersn(orderbean.getString("ordersn"));
                                long created_at = Long.parseLong(orderbean.getString("created_at")) * 1000;
                                Date d = new Date(created_at);
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                ob.setCreated_at(sdf.format(d));
                                list.add(ob);
                            }
                            orderTotal.setText(jsonObject.getJSONObject("data").getString("total_num"));
                            orderTotalPrice.setText(DoubleUtils.setSSWRDouble(Double.parseDouble(jsonObject.getJSONObject("data").getString("total_amount"))));
                        } else if (status == 0) {
                            //获取失败
                        }
                    } catch (JSONException e) {
                    } finally {
                        orderListItemAdapter.notifyDataSetChanged();
                    }

                    break;
                case 511:
                    Toast.makeText(TheOrderListActivity.this, "网络超时，请重试", Toast.LENGTH_SHORT).show();
                    loading_dailog.dismiss();
                    break;
                case 20000:
                    int postion = (int) msg.obj;
                    Intent intent = new Intent(TheOrderListActivity.this, OrderDetailsActivity.class);
                    intent.putExtra("orderid", list.get(postion).getId());
                    startActivityForResult(intent, 1220);
                    break;
            }
        }
    };
    private ImageView back;
    private int post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //去除状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_theorderlsit);
        list = new ArrayList<>();
        post = 0;
        mContext = this;
        initGetUserInfo();
        initView();
        action();
        initGetDataOrderList("");
    }

    /**
     * 初始化数据
     */
    private void initView() {
        orderTotal = (TextView) findViewById(R.id.orderTotal);
        orderTotalPrice = (TextView) findViewById(R.id.orderTotalPrice);
        //导航按钮============================
        alltvorder = (TextView) findViewById(R.id.alltvorder);
        alltvdfk = (TextView) findViewById(R.id.alltvdfk);
        alltvywc = (TextView) findViewById(R.id.alltvywc);
        alltvyqx = (TextView) findViewById(R.id.alltvyqx);
        alltvorder_line = (View) findViewById(R.id.alltvorder_line);
        alltvdfk_line = (View) findViewById(R.id.alltvdfk_line);
        alltvywc_line = (View) findViewById(R.id.alltvywc_line);
        alltvyqx_line = (View) findViewById(R.id.alltvyqx_line);
        //导航按钮============================
        orderlistview = (ListView) findViewById(R.id.orderlistview);
        orderListItemAdapter = new OrderListItemAdapter(this, list, handler);
        orderlistview.setAdapter(orderListItemAdapter);
        back = (ImageView) findViewById(R.id.back);
    }

    /**
     * 导航按钮的点击事件
     */
    private void action() {
        //所有订单
        alltvorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post = 0;
                alltvdfk.setTextColor(Color.parseColor("#7c7c7c"));
                alltvywc.setTextColor(Color.parseColor("#7c7c7c"));
                alltvyqx.setTextColor(Color.parseColor("#7c7c7c"));
                alltvorder.setTextColor(Color.parseColor("#000000"));
                alltvorder_line.setVisibility(View.VISIBLE);
                alltvdfk_line.setVisibility(View.GONE);
                alltvywc_line.setVisibility(View.GONE);
                alltvyqx_line.setVisibility(View.GONE);
                initGetDataOrderList("");
            }
        });
        //待付款
        alltvdfk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post = 1;
                alltvdfk.setTextColor(Color.parseColor("#000000"));
                alltvywc.setTextColor(Color.parseColor("#7c7c7c"));
                alltvyqx.setTextColor(Color.parseColor("#7c7c7c"));
                alltvorder.setTextColor(Color.parseColor("#7c7c7c"));
                alltvorder_line.setVisibility(View.GONE);
                alltvdfk_line.setVisibility(View.VISIBLE);
                alltvywc_line.setVisibility(View.GONE);
                alltvyqx_line.setVisibility(View.GONE);
                initGetDataOrderList("'0'");

            }
        });
        //已完成
        alltvywc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alltvdfk.setTextColor(Color.parseColor("#7c7c7c"));
                alltvywc.setTextColor(Color.parseColor("#000000"));
                alltvyqx.setTextColor(Color.parseColor("#7c7c7c"));
                alltvorder.setTextColor(Color.parseColor("#7c7c7c"));
                alltvorder_line.setVisibility(View.GONE);
                alltvdfk_line.setVisibility(View.GONE);
                alltvywc_line.setVisibility(View.VISIBLE);
                alltvyqx_line.setVisibility(View.GONE);
                initGetDataOrderList("1");
            }
        });
        //已取消
        alltvyqx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alltvdfk.setTextColor(Color.parseColor("#7c7c7c"));
                alltvywc.setTextColor(Color.parseColor("#7c7c7c"));
                alltvyqx.setTextColor(Color.parseColor("#000000"));
                alltvorder.setTextColor(Color.parseColor("#7c7c7c"));
                alltvorder_line.setVisibility(View.GONE);
                alltvdfk_line.setVisibility(View.GONE);
                alltvywc_line.setVisibility(View.GONE);
                alltvyqx_line.setVisibility(View.VISIBLE);
                initGetDataOrderList("-1");
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TheOrderListActivity.this.finish();
            }
        });
    }

    /**
     * 获取订单列表数据
     */
    private void initGetDataOrderList(String status) {
        //默认是没有开启的
        String timestamp = System.currentTimeMillis() + "";
        String token = CreateToken.createToken(userInfo.getUuid(), timestamp, userInfo.getAccount());
        Map<String, String> getOrderDetails = new HashMap<String, String>();
        getOrderDetails.put("account_id", userInfo.getAccount_id());
        getOrderDetails.put("organization_id", userInfo.getOrganization_id());
        getOrderDetails.put("status", status);
        getOrderDetails.put("token", token);
        getOrderDetails.put("timestamp", timestamp);
        loading_dailog = LoadingUtils.getDailog(mContext, Color.RED, "获取订单列表。。。。");
        loading_dailog.show();
        NetUtils.netWorkByMethodPost(mContext, getOrderDetails, IpConfig.URL_ORDERLIST, handler, 0);
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
     * 返回的数据处理
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1220:
                if (resultCode == RESULT_OK) {
                    //这个是返回的页面
                    initGetDataOrderList("'0'");
                } else if (resultCode == 300) {
                    if (post == 0) {
                        initGetDataOrderList("");
                    } else if (post == 1) {
                        initGetDataOrderList("'0'");
                    }
                }
                break;
        }

    }
}
