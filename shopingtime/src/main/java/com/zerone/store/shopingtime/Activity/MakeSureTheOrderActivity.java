package com.zerone.store.shopingtime.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zerone.store.shopingtime.Adapter.cart_list.MakeOrderDetialsListItemAdapter;
import com.zerone.store.shopingtime.Base64AndMD5.CreateToken;
import com.zerone.store.shopingtime.BaseActivity.BaseAppActivity;
import com.zerone.store.shopingtime.Bean.UserInfo;
import com.zerone.store.shopingtime.Bean.order.SubmitDataBean;
import com.zerone.store.shopingtime.Bean.order.SubmitShopBean;
import com.zerone.store.shopingtime.Bean.shoplistbean.ShopMessageBean;
import com.zerone.store.shopingtime.Contants.IpConfig;
import com.zerone.store.shopingtime.DB.impl.UserInfoImpl;
import com.zerone.store.shopingtime.R;
import com.zerone.store.shopingtime.Utils.AppSharePreferenceMgr;
import com.zerone.store.shopingtime.Utils.DoubleUtils;
import com.zerone.store.shopingtime.Utils.LoadingUtils;
import com.zerone.store.shopingtime.Utils.NetUtils;
import com.zyao89.view.zloading.ZLoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by on 2018/3/30 0030 11 36.
 * Author  LiuXingWen
 * 这个是确认订单页面
 * 选完商品后的下单前确认订单
 */

public class MakeSureTheOrderActivity extends BaseAppActivity {

    private Intent intent;
    private ArrayList<ShopMessageBean> listObj;
    private RelativeLayout submitbtn;
    private UserInfo userInfo;
    private ZLoadingDialog loading;
    private List<SubmitShopBean> subbeanlist;
    private ZLoadingDialog loading_dailog;
    private ListView goodslist;
    private MakeOrderDetialsListItemAdapter mAdapter;
    private TextView sureOrderMoney;

    private double dSOMoney = 0.00;
    private TextView subMoney;
    private ImageView back;
    private TextView remark;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    loading.dismiss();
                    /**
                     *
                     * {
                     status: "1",
                     msg: "提交订单成功",
                     data: {
                     order_id: 35
                     }
                     }
                     *
                     */
                    String subJSon = (String) msg.obj;
                    try {
                        JSONObject jsonObject = new JSONObject(subJSon);
                        int status = jsonObject.getInt("status");
                        if (status == 1) {
                            //订单提交成功  获取商品数据 打印小票，吊起支付。
                            String orderid = jsonObject.getJSONObject("data").getString("order_id");
                            //订单提交成功 跳转到 订单详情
                            Intent intent = new Intent(MakeSureTheOrderActivity.this, OrderDetailsActivity.class);
                            intent.putExtra("orderid", orderid);
                            startActivity(intent);
                            AppSharePreferenceMgr.put(MakeSureTheOrderActivity.this, "orderid", orderid);
                            MakeSureTheOrderActivity.this.finish();
                        } else if (status == 0) {
                            //订单提交失败  提示用户失败的原因
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    String reinfo = (String) msg.obj;
                    if (reinfo != null) {
                        remark.setText(reinfo);
                    }
                    break;
                case 511:
                    Toast.makeText(MakeSureTheOrderActivity.this, "网络超时，请重试", Toast.LENGTH_SHORT).show();
                    loading_dailog.dismiss();
                    break;
            }
        }
    };
    private LinearLayout actionremark;
    private LinearLayout writeoff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makesuretheorder);
        intent = getIntent();
        initGetUserInfo();
        listObj = (ArrayList<ShopMessageBean>) getIntent().getSerializableExtra("listobj");
        initView();
        initAction();
    }

    /**
     * 获取用户信息
     */
    private void initGetUserInfo() {

        UserInfoImpl userInfoImpl = new UserInfoImpl(MakeSureTheOrderActivity.this);
        try {
            userInfo = userInfoImpl.getUserInfo("10");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * view的初始化
     */
    private void initView() {
        if (listObj == null) {
            return;
        }
        for (int i = 0; i < listObj.size(); i++) {
            listObj.get(i).getSp_price();
            dSOMoney += Double.parseDouble(listObj.get(i).getSp_price()) * Integer.parseInt(listObj.get(i).getSp_count());
        }
        goodslist = (ListView) findViewById(R.id.goodslist);
        mAdapter = new MakeOrderDetialsListItemAdapter(MakeSureTheOrderActivity.this, listObj);
        goodslist.setAdapter(mAdapter);
        subMoney = (TextView) findViewById(R.id.subMoney);
        sureOrderMoney = (TextView) findViewById(R.id.sureOrderMoney);
        sureOrderMoney.setText("￥" + dSOMoney);
        subMoney.setText("￥" + DoubleUtils.setSSWRDouble(dSOMoney));
        submitbtn = (RelativeLayout) findViewById(R.id.submitbtn);
        back = (ImageView) findViewById(R.id.back);
        remark = (TextView) findViewById(R.id.remark);
        actionremark = (LinearLayout) findViewById(R.id.actionremark);
        writeoff = (LinearLayout) findViewById(R.id.writeoff);
    }

    /**
     * 控件的点击事件
     */
    private void initAction() {
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoSubmit();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MakeSureTheOrderActivity.this.finish();
            }
        });

        actionremark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog();
            }
        });

        //取消订单
        writeoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MakeSureTheOrderActivity.this.finish();
            }
        });
    }

    /**
     * 设置提交订单时的数据整理
     */
    private void gotoSubmit() {
        if (userInfo == null) {
            return;
        }
        subbeanlist = new ArrayList<>();
        String timestamp = System.currentTimeMillis() + "";
        String token = CreateToken.createToken(userInfo.getUuid(), timestamp, userInfo.getAccount());
        Map<String, String> subMap = new HashMap<String, String>();
        if (userInfo.getOrganization_id() != null) {
            subMap.put("organization_id", userInfo.getOrganization_id());
        }

        if (userInfo.getAccount_id() != null) {
            subMap.put("account_id", userInfo.getAccount_id());
        }
        if (token != null) {
            subMap.put("token", token);
        }

        //0为散客
        subMap.put("user_id", "0");
        if (timestamp != null) {
            subMap.put("timestamp", timestamp);
        }
        String remarks = remark.getText().toString();
        if (!TextUtils.isEmpty(remarks)) {
            subMap.put("remarks", remarks);
        }

        for (int i = 0; i < listObj.size(); i++) {
            SubmitShopBean submitShopBean = new SubmitShopBean();
            submitShopBean.setId(listObj.get(i).getSp_id());
            submitShopBean.setNum(listObj.get(i).getSp_count());
            submitShopBean.setPrice(listObj.get(i).getSp_price());
            //商品规格
//               submitShopBean.setSpec("0");
            subbeanlist.add(submitShopBean);
        }

        SubmitDataBean sdb = new SubmitDataBean(subbeanlist);
        String listJSOn = JSON.toJSONString(sdb);
        String goodsdata = null;
        try {
            goodsdata = new String(listJSOn.getBytes(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("URL", goodsdata);
        if (goodsdata != null) {
            subMap.put("goodsdata", goodsdata);
        }
        loading = LoadingUtils.getDailog(MakeSureTheOrderActivity.this, Color.RED, "提交订单中。。。。");
        loading.show();
        NetUtils.netWorkByMethodPost(MakeSureTheOrderActivity.this, subMap, IpConfig.URL_SUBMITORDER, handler, 0);

    }

    /**
     * 自定义对话框
     */
    private void customDialog() {
        final Dialog dialog = new Dialog(this, R.style.NormalDialogStyle);
        View view = View.inflate(this, R.layout.activity_dialog_edit_view, null);
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        TextView confirm = (TextView) view.findViewById(R.id.confirm);
        final EditText writeremake = (EditText) view.findViewById(R.id.writeremake);
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
                String reinfo = writeremake.getText().toString();
                Message message = new Message();
                message.what = 2;
                message.obj = reinfo;
                handler.sendMessage(message);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
