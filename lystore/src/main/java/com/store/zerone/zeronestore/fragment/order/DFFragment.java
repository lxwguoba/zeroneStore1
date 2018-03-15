package com.store.zerone.zeronestore.fragment.order;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.store.zerone.zeronestore.R;
import com.store.zerone.zeronestore.adapter.BranchSelectedAdapter;
import com.store.zerone.zeronestore.adapter.order.ListOrdeDetailsItemrAdapter;
import com.store.zerone.zeronestore.adapter.order.OrderListCycleAdapter;
import com.store.zerone.zeronestore.contanst.ContantData;
import com.store.zerone.zeronestore.contanst.IpConfig;
import com.store.zerone.zeronestore.domain.DaiFuKuanOrderBean;
import com.store.zerone.zeronestore.domain.OrderInfo;
import com.store.zerone.zeronestore.utils.LoadingUtils;
import com.store.zerone.zeronestore.utils.MapUtilsSetParam;
import com.store.zerone.zeronestore.utils.NetUtils;
import com.store.zerone.zeronestore.utils.Utils;
import com.store.zerone.zeronestore.utils.UtilsTime;
import com.zyao89.view.zloading.ZLoadingDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by on 2018/1/31 0031 10 50.
 * Author  LiuXingWen
 * 待付款页面
 */

public class DFFragment extends Fragment {

    private View view;
    private RecyclerView ordercycle;
    private OrderListCycleAdapter cycAdapter;
    List<DaiFuKuanOrderBean.DataBean.ListBean> getOrderList;
    private ZLoadingDialog loading_dailog;
    private PopupWindow mPopupWindow;
    private View mContentView;
    private LinearLayout parentview;
    private ListView popListview;
    private ListOrdeDetailsItemrAdapter popAdapter;
    private ZLoadingDialog loadingDetails;
    //存放goodsbean实体类的集合
    private List<OrderInfo.DataBean.ItemBean.GoodsBean> listBeen;
    private TextView tablename;
    private TextView ordertime;
    private TextView order_details_num;
    private TextView order_money;
    private TextView zhekou_money;
    private EditText zhekou_edt;
    private TextView dingdanjine;
    private TextView seale_money;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_order_df, null);
        }
        listBeen = new ArrayList<>();
        LoadingData();
        initView();
        recycleListenner();

        return view;
    }

    private void recycleListenner() {
        cycAdapter.setOnItemClickListener(new BranchSelectedAdapter.OnItemClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemClick(View view, int position) {
                //启动popwindow
                loadingDetails = LoadingUtils.getDailog(getContext(), Color.RED, "获取订单详情中。。。。");
                loadingDetails.show();
                String id = getOrderList.get(position).getId();
                Map<String, String> map = MapUtilsSetParam.getMap(getContext());
                map.put("opp", "getorderdetail");
                //获取分店id
                map.put("branchid", Utils.getBranch(getContext()).getId());
                map.put("orderid", id);
                NetUtils.netWorkByMethodPost(getContext(), map, IpConfig.URL, handler, ContantData.GETORDERLISTDETAILS);
                setPopWindow();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void LoadingData() {
        parentview = (LinearLayout)view.findViewById(R.id.parentview);
        getOrderList = new ArrayList<>();
        loading_dailog = LoadingUtils.getDailog(getContext(), Color.RED, "获取订单中。。。。");
        loading_dailog.show();
        //获取order的数据
        Map<String, String> mapOrder = MapUtilsSetParam.getMap(getContext());
        mapOrder.put("opp", "orderlist");
//        mapOrder.put("branchid",  Utils.getBranch(getContext()).getId());
        mapOrder.put("page", "1");
        mapOrder.put("status", "0");
        NetUtils.netWorkByMethodPost(getActivity(), mapOrder, IpConfig.URL, handler, ContantData.GETORDERLISTDF);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initView() {
        ordercycle = (RecyclerView)view.findViewById(R.id.order_recycle);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 1);
        ordercycle.setLayoutManager(manager);
        cycAdapter = new OrderListCycleAdapter(getOrderList);
        ordercycle.setAdapter(cycAdapter);
        //------------------------------------pop---------------------------------
        if(mContentView == null){
            mContentView = LayoutInflater.from(getContext()).inflate(R.layout.activity_order_df_details_pop, null);
        }
        tablename = (TextView) mContentView.findViewById(R.id.tablename);
        ordertime = (TextView) mContentView.findViewById(R.id.ordertime);
        order_details_num = (TextView) mContentView.findViewById(R.id.order_details_num);
        dingdanjine = (TextView)mContentView.findViewById(R.id.dingdanjine);
        seale_money = (TextView)mContentView.findViewById(R.id.seale_money);
        order_money = (TextView) mContentView.findViewById(R.id.order_money);
        zhekou_money = (TextView) mContentView.findViewById(R.id.zhekou_money);
        zhekou_edt = (EditText) mContentView.findViewById(R.id.zhekou_edt);
        popListview = (ListView) mContentView.findViewById(R.id.order_details_list_goods);
        popAdapter = new ListOrdeDetailsItemrAdapter(getContext(),listBeen);
        popListview.setAdapter(popAdapter);
        //------------------------------------pop---------------------------------
    }


    Handler handler  =  new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case ContantData.GETORDERLISTDF:
                    String  orderJon= (String) msg.obj;
                    Log.i("URL","=======================================================");
                    Log.d("URL",orderJon);
                    Log.i("URL","=======================================================");
                    String orderJson = (String) msg.obj;
//                    DaiFuKuanOrderBean daiFuKuanOrderBean = JSON.parseObject(orderJson, DaiFuKuanOrderBean.class);
//                    if (daiFuKuanOrderBean.getStatus() == 0) {
//                        return;
//                    }
//                    List<DaiFuKuanOrderBean.DataBean.ListBean> list = daiFuKuanOrderBean.getData().getList();
//                    getOrderList.clear();
//                    if (list.size() > 0) {
//                        for (int i = 0; i < list.size(); i++) {
//                            getOrderList.add(list.get(i));
//                        }
//                    }
                    cycAdapter.notifyDataSetChanged();
                    loading_dailog.dismiss();
                    break;

                case ContantData.GETORDERLISTDETAILS:
                    String orderdetailsJson = (String) msg.obj;
                    Log.i("TAG", "orderjson7777=" + orderdetailsJson);
                    try {
                        JSONObject    json = new JSONObject(orderdetailsJson);
                        String userstr = json.getJSONObject("data").getJSONObject("item").getString("user");
                        String shrname = json.getJSONObject("data").getJSONObject("orderownner").getString("realname");
                        String ordersn = json.getJSONObject("data").getJSONObject("item").getString("ordersn");
                        String createTime = json.getJSONObject("data").getJSONObject("item").getString("createtime");
                        long time = Long.parseLong(createTime);
                        ordertime.setText("下单时间："+ UtilsTime.getTime(time));
                        if ("1.00".equals(json.getJSONObject("data").getJSONObject("item").getString("sale"))) {
                            zhekou_edt.setText("未打折");
                            zhekou_money.setText(json.getJSONObject("data").getJSONObject("item").getString("price")+"");
                            //订单总金额
                            order_money.setText(json.getJSONObject("data").getJSONObject("item").getString("price")+"");
                        } else {
                            double yhzk = Double.parseDouble(json.getJSONObject("data").getJSONObject("item").getString("sale")) * 10;
                            zhekou_edt.setText(yhzk + "折");
                            double ysje = Double.parseDouble(json.getJSONObject("data").getJSONObject("item").getString("price")) * Double.parseDouble(json.getJSONObject("data").getJSONObject("item").getString("sale"));
                            zhekou_money.setText(ysje+"");
                             order_money.setText(ysje+"");
                        }

                        dingdanjine.setText("￥" + json.getJSONObject("data").getJSONObject("item").getString("goodsprice"));
                        order_details_num.setText(ordersn);
                        String jsonObject = json.getJSONObject("data").getString("sharemem");
                        tablename.setText("桌子名称："+json.getJSONObject("data").getJSONObject("tableinfo").getString("tablename"));
                        String datenum = json.getJSONObject("data").getJSONObject("tableinfo").getString("datenum");

                        seale_money.setText("￥" + json.getJSONObject("data").getJSONObject("item").getString("seat_fee"));

                        if ("1".equals(json.getJSONObject("data").getJSONObject("item").getString("dispatchchoice"))) {
                            //外卖
//                            addfood.setVisibility(View.GONE);
//                            submit_pay.setText("确认付款");
//                            zhuozi.setText("关闭订单");
                        } else if ("2".equals(json.getJSONObject("data").getJSONObject("item").getString("dispatchchoice"))) {
                            //2.预约就餐订单
//                            addfood.setVisibility(View.GONE);
//                            submit_pay.setText("确认付款");
//                            zhuozi.setText("关闭订单");
                        } else if ("3".equals(json.getJSONObject("data").getJSONObject("item").getString("dispatchchoice"))) {
                            //2.现场订单
//                                Log.d("NewOrderDetails", json.getJSONObject("data").getJSONObject("tableinfo").getString("tableid"));
//                            if (json.getJSONObject("data").getJSONObject("tableinfo").getString("tableid") == null) {
//                                zhuozi.setText("绑定桌子");
//                            } else {
//                                zhuozi.setText("换桌子");
//                            }
                        }


                        ///--------------------
                        //支付方式

//                        if ("1".equals(json.getJSONObject("data").getJSONObject("item").getString("paytype"))) {
//                            zhifufangshi.setText("在线余额支付");
//                        } else if ("2".equals(json.getJSONObject("data").getJSONObject("item").getString("paytype"))) {
//
//                            zhifufangshi.setText("在线微信支付,微支付单号：" + json.getJSONObject("data").getJSONObject("item").getString("transid"));
//                        } else if ("3".equals(json.getJSONObject("data").getJSONObject("item").getString("paytype"))) {
//
//                            zhifufangshi.setText("现场现金支付，,现场确认人ID号：" + json.getJSONObject("data").getJSONObject("item").getString("confirmedby"));
//
//                        } else if ("4".equals(json.getJSONObject("data").getJSONObject("item").getString("paytype"))) {
//                            zhifufangshi.setText("现金支付,现场确认人ID号：" + json.getJSONObject("data").getJSONObject("item").getString("confirmedby"));
//                        } else if ("5".equals(json.getJSONObject("data").getJSONObject("item").getString("paytype"))) {
//                            zhifufangshi.setText("现场刷卡支付,现场确认人ID号：" + json.getJSONObject("data").getJSONObject("item").getString("confirmedby"));
//                        } else if ("6".equals(json.getJSONObject("data").getJSONObject("item").getString("paytype"))) {
//                            zhifufangshi.setText("现场支付宝付,现场确认人ID号：" + json.getJSONObject("data").getJSONObject("item").getString("confirmedby"));
//                        } else if ("7".equals(json.getJSONObject("data").getJSONObject("item").getString("paytype"))) {
//                            zhifufangshi.setText("现场微信支付,现场确认人ID号：" + json.getJSONObject("data").getJSONObject("item").getString("confirmedby"));
//
//                        } else if ("8".equals(json.getJSONObject("data").getJSONObject("item").getString("paytype"))) {
//                            zhifufangshi.setText("在线手动确认,现场确认人ID号：" + json.getJSONObject("data").getJSONObject("item").getString("confirmedby"));
//
//                        }
                        //订单状态

//                        if ("0".equals(json.getJSONObject("data").getJSONObject("item").getString("status"))) {
//                            dingdanzhuantai.setText("待付款");
//                        } else if ("1".equals(json.getJSONObject("data").getJSONObject("item").getString("status"))) {
//                            dingdanzhuantai.setText("已付款");
//                        } else if ("2".equals(json.getJSONObject("data").getJSONObject("item").getString("status"))) {
//                            dingdanzhuantai.setText("待收货");
//                        } else if ("3".equals(json.getJSONObject("data").getJSONObject("item").getString("status"))) {
//                            dingdanzhuantai.setText("已完成");
//                        } else if ("-1".equals(json.getJSONObject("data").getJSONObject("item").getString("status"))) {
//                            dingdanzhuantai.setText("已关闭");
//                        } else if ("-2".equals(json.getJSONObject("data").getJSONObject("item").getString("status"))) {
//                            dingdanzhuantai.setText("退款中");
//                        } else if ("-3".equals(json.getJSONObject("data").getJSONObject("item").getString("status"))) {
//                            dingdanzhuantai.setText("换货中");
//                        } else if ("-4".equals(json.getJSONObject("data").getJSONObject("item").getString("status"))) {
//                            dingdanzhuantai.setText("退货中");
//                        } else if ("-5".equals(json.getJSONObject("data").getJSONObject("item").getString("status"))) {
//                            dingdanzhuantai.setText("已退货");
//                        } else if ("-6".equals(json.getJSONObject("data").getJSONObject("item").getString("status"))) {
//                            dingdanzhuantai.setText("已退款");
//                        }
//                        dispatchchoice = json.getJSONObject("data").getJSONObject("item").getString("dispatchchoice");
//                        if ("1".equals(json.getJSONObject("data").getJSONObject("item").getString("dispatchchoice"))) {
//                            xiadanzhuantai.setText("外卖订单");
//                        } else if ("2".equals(json.getJSONObject("data").getJSONObject("item").getString("dispatchchoice"))) {
//                            xiadanzhuantai.setText("预约就餐订单");
//                        } else if ("3".equals(json.getJSONObject("data").getJSONObject("item").getString("dispatchchoice"))) {
//                            xiadanzhuantai.setText("现场订单");
//                        }
                        ///--------------------
                        JSONArray jsonArray = json.getJSONObject("data").getJSONObject("item").getJSONArray("goods");
                        if (jsonArray != null) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                OrderInfo.DataBean.ItemBean.GoodsBean goods = new OrderInfo.DataBean.ItemBean.GoodsBean();
                                goods.setId(jsonArray.getJSONObject(i).getString("id"));
                                goods.setCommission(jsonArray.getJSONObject(i).getString("commission"));
                                goods.setGoodssn(jsonArray.getJSONObject(i).getString("goodssn"));
                                goods.setMarketprice(jsonArray.getJSONObject(i).getString("marketprice"));
                                goods.setOid(jsonArray.getJSONObject(i).getString("oid"));
                                goods.setOptionid(jsonArray.getJSONObject(i).getString("optionid"));
                                goods.setOptionname(jsonArray.getJSONObject(i).getString("optionname"));
                                goods.setPrice(jsonArray.getJSONObject(i).getString("price"));
                                goods.setProductsn(jsonArray.getJSONObject(i).getString("productsn"));
                                goods.setThumb(jsonArray.getJSONObject(i).getString("thumb"));
                                goods.setTitle(jsonArray.getJSONObject(i).getString("title"));
                                goods.setTotal(jsonArray.getJSONObject(i).getString("total"));
                                goods.setType(jsonArray.getJSONObject(i).getString("type"));
                                goods.setUnit(jsonArray.getJSONObject(i).getString("unit"));
                                listBeen.add(goods);
                            }
                            popAdapter.notifyDataSetChanged();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                loadingDetails.dismiss();
                    break;
            }
        }
    };


    /**
     * popwindow的初始化
     */
    public void setPopWindow(){

        mPopupWindow = new PopupWindow(mContentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mPopupWindow.showAtLocation(parentview, Gravity.CENTER,0,0);
    }
}
