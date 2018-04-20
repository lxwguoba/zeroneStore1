package com.zerone.store.shopingtime.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zerone.store.shopingtime.Adapter.cart_list.ListGoodsDetails_Adapter;
import com.zerone.store.shopingtime.Adapter.shopplistadapter.PersonAdapter;
import com.zerone.store.shopingtime.Adapter.shopplistadapter.RecycleGoodsCategoryListAdapter;
import com.zerone.store.shopingtime.Base64AndMD5.CreateToken;
import com.zerone.store.shopingtime.BaseActivity.BaseAppActivity;
import com.zerone.store.shopingtime.Bean.ShopBean;
import com.zerone.store.shopingtime.Bean.UserInfo;
import com.zerone.store.shopingtime.Bean.shoplistbean.GoodsCategroyListBean;
import com.zerone.store.shopingtime.Bean.shoplistbean.ShopMessageBean;
import com.zerone.store.shopingtime.Contants.IpConfig;
import com.zerone.store.shopingtime.DB.impl.UserInfoImpl;
import com.zerone.store.shopingtime.R;
import com.zerone.store.shopingtime.Utils.DoubleUtils;
import com.zerone.store.shopingtime.Utils.LoadingUtils;
import com.zerone.store.shopingtime.Utils.MapUtilsSetParam;
import com.zerone.store.shopingtime.Utils.NetUtils;
import com.zerone.store.shopingtime.Utils.NetworkUtil;
import com.zyao89.view.zloading.ZLoadingDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by on 2018/3/31 0031 09 51.
 * Author  LiuXingWen
 * OnHeaderClickListener
 */

public class OrderListActvity extends BaseAppActivity {
    private static final String TAG = "OrderListActvity";
    //商品类别列表
    private List<GoodsCategroyListBean.DataBean.CategorylistBean> catelist = new ArrayList<>();
    //存储含有标题的第一个含有商品类别名称的条目的下表
    private List<Integer> titlePois = new ArrayList<>();
    //商品列表
    private List<ShopBean> goodsitemlist = new ArrayList<>();
    private List<ShopBean> buyShoppingList = new ArrayList<>();
    //    private StickyHeadersItemDecoration top;
    //商品分类
    private RecyclerView mGoodsCateGoryList;
    //上一个标题的小标位置
    private int lastTitlePoi;
    //商品信息
    private RecyclerView mGoodsInfoRecyclerView;
    private RecycleGoodsCategoryListAdapter mGoodsCategoryListAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private PersonAdapter personAdapter;
    //    private BigramHeaderAdapter headerAdapter;
    private ZLoadingDialog loading_dailog;
    private UserInfo userInfo;
    private List<ShopMessageBean> buycartshoplist;
    private View shopview;
    private CheckBox allcheck;
    private TextView checkCount;
    private LinearLayout clear_cart;
    private ListView shoplistview;
    private TextView shopCount;
    private LinearLayout parentView;
    private ListGoodsDetails_Adapter listAdapter;

    private int popupWidth;
    private int popupHeight;
    private TextView pop_price;
    private View menuView;
    private LinearLayout systemset;
    private LinearLayout ordermenu;
    private PopupWindow mPopupWindowMenu;
    private TextView quxiaoMenu;
    private LinearLayout f_menu;
    private MyLayoutManage myLayoutManage;
    private RelativeLayout showOrderList;
    private PopupWindow mPopupWindow;
    private LinearLayout settlement;
    private LinearLayout scan_btn;
    private LinearLayout search_head_btn;
    private TextView selectedgoodsmoney;
    Handler handler = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    String shoplistJson = (String) msg.obj;
                    Log.i("URL", "000000000000==" + shoplistJson);
                    loading_dailog.dismiss();
                    try {
                        JSONObject jsonshoplist = new JSONObject(shoplistJson);
                        int status = jsonshoplist.getInt("status");
                        if (status == 1) {
                            JSONArray jsonArray = jsonshoplist.getJSONObject("data").getJSONArray("goodslist");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject shopObject = jsonArray.getJSONObject(i);
                                ShopBean shopBean = new ShopBean();
                                shopBean.setName(shopObject.getString("name"));
                                shopBean.setId(shopObject.getInt("id"));
                                shopBean.setCategory_id(shopObject.getInt("category_id"));
                                shopBean.setCategory_name(shopObject.getString("category_name"));
                                shopBean.setDetails(shopObject.getString("details"));
                                shopBean.setPrice(shopObject.getString("price"));
                                shopBean.setStock(shopObject.getInt("stock"));
                                shopBean.setShop_Count("0");
                                List<ShopBean.ThumbBean> Tlist = new ArrayList<ShopBean.ThumbBean>();
                                ShopBean.ThumbBean thumbBean = new ShopBean.ThumbBean();
                                if (shopObject.getJSONArray("thumb").length() > 0) {
                                    thumbBean.setThumb(shopObject.getJSONArray("thumb").getJSONObject(0).getString("thumb"));
                                } else {
                                    thumbBean.setThumb("");
                                }
                                Tlist.add(thumbBean);
                                shopBean.setThumb(Tlist);
                                goodsitemlist.add(shopBean);
                            }
                        }
                        getCategoryInfo();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;

                case 10:
                    String cateJson = (String) msg.obj;
                    Log.i("URL", "111111111111==" + cateJson);
                    loading_dailog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(cateJson);
                        int status = jsonObject.getInt("status");
                        if (status == 1) {
                            JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("categorylist");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonBean = jsonArray.getJSONObject(i);
                                GoodsCategroyListBean.DataBean.CategorylistBean clb = new GoodsCategroyListBean.DataBean.CategorylistBean();
                                clb.setDisplayorder(jsonBean.getInt("displayorder"));
                                clb.setId(jsonBean.getInt("id"));
                                clb.setName(jsonBean.getString("name"));
                                catelist.add(clb);
                                titlePois.add(i);
                            }
                            initData(catelist);
                        } else if (status == 0) {

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;
                case 3:
                    /*清空购物车*/
                    for (int i = 0; i < goodsitemlist.size(); i++) {
                        goodsitemlist.get(i).setShop_Count("0");
                    }
//                    for (int i=0;i<buycartshoplist.size();i++){
//                         if (buycartshoplist.get(i).isSp_check()){
//                              buycartshoplist.remove(i);
//                         }
//                    }
                    selectedgoodsmoney.setText("￥ 0");
                    buycartshoplist.clear();
                    buyShoppingList.clear();
//                    allcheck.setChecked(false);
                    checkCount.setText("没有商品");
//                    buycartshoplist.clear();
                    pop_price.setText("0.00");
                    listAdapter.notifyDataSetChanged();
                    showOrderList.setVisibility(View.GONE);
                    personAdapter.notifyDataSetChanged();
                    break;
                case 4:
                    /**
                     * 全选和全不选
                     */
                    String money = "";
                    double v = 0.00;
                    if (allcheck.isChecked()) {
                        for (int i = 0; i < buycartshoplist.size(); i++) {
                            buycartshoplist.get(i).setSp_check(true);
                            v += Double.parseDouble(buycartshoplist.get(i).getSp_price()) * Integer.parseInt(buycartshoplist.get(i).getSp_count());
                        }
                        pop_price.setText(DoubleUtils.setSSWRDouble(v) + "");
                    } else {
                        for (int i = 0; i < buycartshoplist.size(); i++) {
                            buycartshoplist.get(i).setSp_check(false);
                        }
                        pop_price.setText("0.00");
                    }
                    listAdapter.notifyDataSetChanged();
                    break;
                case 33:
                    Intent intent = new Intent(OrderListActvity.this, SearchActivity.class);
                    if (buyShoppingList != null) {
                        intent.putExtra("listobj", (Serializable) buyShoppingList);
                    }
                    startActivityForResult(intent, 1100);
                      /*清空购物车*/
                    for (int i = 0; i < goodsitemlist.size(); i++) {
                        goodsitemlist.get(i).setShop_Count("0");
                    }
                    selectedgoodsmoney.setText("0");
                    buycartshoplist.clear();
                    buyShoppingList.clear();
                    shopCount.setText("0");
                    showOrderList.setVisibility(View.GONE);
                    personAdapter.notifyDataSetChanged();
                    break;

                case 1000:
                    //按了添加商品这个按钮
                    boolean sameShopboolean = false;
                    Integer sameIndex = null;
                    int index = (int) msg.obj;
                    //给列表设置个数
                    int count = Integer.parseInt(goodsitemlist.get(index).getShop_Count());
                    count++;
                    goodsitemlist.get(index).setShop_Count(count + "");
                    //给购物车添加商品
                    if (buyShoppingList.size() > 0) {
                        // 1、购物车已有商品
                        for (int i = 0; i < buyShoppingList.size(); i++) {
                            //判断是否是同一个商品
                            if (goodsitemlist.get(index).getId() == buyShoppingList.get(i).getId()) {
                                sameShopboolean = true;
                                sameIndex = i;
                                break;
                            }
                        }
                        if (sameShopboolean) {
                            //相同商品 数量加一
                            if (sameIndex != null) {
                                //把这个商品从新设置一下
                                buyShoppingList.set(sameIndex, goodsitemlist.get(index));
                            }
                        } else {
                            //不同商品
                            buyShoppingList.add(goodsitemlist.get(index));
                        }
                    } else {
                        //2、购物车没有商品
                        buyShoppingList.add(goodsitemlist.get(index));
                    }
                    double dmoney = 0.00;
                    if (buyShoppingList.size() > 0) {
                        showOrderList.setVisibility(View.VISIBLE);
                        int buyNum = 0;
                        for (int i = 0; i < buyShoppingList.size(); i++) {
                            dmoney += Double.parseDouble(buyShoppingList.get(i).getPrice()) * Integer.parseInt(buyShoppingList.get(i).getShop_Count());
                            buyNum += Integer.parseInt(buyShoppingList.get(i).getShop_Count());
                            Log.i("URL", "{" + buyNum + "}");
                        }
                        shopCount.setText(buyNum + "");
                    }
                    selectedgoodsmoney.setText("" + DoubleUtils.setSSWRDouble(dmoney));
                    personAdapter.notifyDataSetChanged();
                    break;

                case 1110:
                    //减号点击按钮点击
                    boolean sameDShopboolean = false;
                    Integer sameDIndex = null;
                    //按了减少商品这个按钮
                    int indx = (int) msg.obj;
                    int cou = Integer.parseInt(goodsitemlist.get(indx).getShop_Count());
                    if (cou > 0) {
                        cou--;
                        if (cou == 0) {
                            goodsitemlist.get(indx).setShop_Count("0");
                            buyShoppingList.remove(goodsitemlist.get(indx));
                        } else {
                            goodsitemlist.get(indx).setShop_Count(cou + "");
                        }
                    }

                    if (buyShoppingList.size() > 0) {
                        int buyNum = 0;
                        double dmone = 0;
                        for (int i = 0; i < buyShoppingList.size(); i++) {
                            buyNum += Integer.parseInt(buyShoppingList.get(i).getShop_Count());
                            dmone += Double.parseDouble(buyShoppingList.get(i).getPrice()) * Integer.parseInt(buyShoppingList.get(i).getShop_Count());
                        }
                        if (buyNum == 0) {
                            showOrderList.setVisibility(View.GONE);
                            selectedgoodsmoney.setText("0.0");
                        } else {
                            showOrderList.setVisibility(View.VISIBLE);
                            selectedgoodsmoney.setText(DoubleUtils.setSSWRDouble(dmone));
                        }
                        shopCount.setText(buyNum + "");
                    } else {
                        showOrderList.setVisibility(View.GONE);
                        selectedgoodsmoney.setText("0.0");
                        shopCount.setText("0");
                    }
                    personAdapter.notifyDataSetChanged();
                    break;

                case 12:
                    //扫描结果获取服务器获取商品的处理
                    loading_dailog.dismiss();
                    String scanJson = (String) msg.obj;
                    try {
                        JSONObject jsonObject = new JSONObject(scanJson);
                        int status = jsonObject.getInt("status");
                        if (status == 1) {
                            //判断是否有这个商品 没有加入购物车 有的话 数量加一
                            JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("goodslist");
                            int id = jsonArray.getJSONObject(0).getInt("id");
                            boolean thesamelean = false;
                            Integer tsIndex = null;
                            if (buycartshoplist.size() > 0) {
                                for (int i = 0; i < buycartshoplist.size(); i++) {
                                    if (id == Integer.parseInt(buycartshoplist.get(i).getSp_id())) {
                                        thesamelean = true;
                                        tsIndex = i;
                                        break;
                                    }
                                }
                                if (thesamelean) {
                                    //同一个商品
                                    // 2、同一个商品 数量加一
                                    Log.i(TAG, "999999999999999999999999999999999999999999999");
                                    String sp_count = buycartshoplist.get(tsIndex).getSp_count();
                                    int coun = Integer.parseInt(sp_count);
                                    coun++;
                                    ShopMessageBean smb = new ShopMessageBean();
                                    smb.setSp_count(coun + "");
                                    smb.setSp_id(buycartshoplist.get(tsIndex).getSp_id() + "");
                                    smb.setCategory_id(buycartshoplist.get(tsIndex).getCategory_id() + "");
                                    smb.setSp_name(buycartshoplist.get(tsIndex).getSp_name());
                                    smb.setSp_picture_url(buycartshoplist.get(tsIndex).getSp_picture_url());
                                    smb.setSp_check(true);
                                    smb.setSp_discount(buycartshoplist.get(tsIndex).getSp_discount());
                                    smb.setSp_price(buycartshoplist.get(tsIndex).getSp_price());
                                    buycartshoplist.set(tsIndex, smb);
                                    Log.i(TAG, "购物车里的东西：：：" + buycartshoplist.toString());
                                } else {
                                    //不同一个商品
                                    Log.i(TAG, "888888888888888888888888888888888888888888");
                                    ShopMessageBean smb = new ShopMessageBean();
                                    smb.setSp_count("1");
                                    smb.setSp_id(jsonArray.getJSONObject(0).getString("id"));
                                    smb.setCategory_id(jsonArray.getJSONObject(0).getString("id"));
                                    smb.setSp_name(jsonArray.getJSONObject(0).getString("id"));
                                    if (jsonArray.getJSONObject(0).getJSONArray("thumb").length() > 0) {
                                        smb.setSp_picture_url(jsonArray.getJSONObject(0).getJSONArray("thumb").getJSONObject(0).getString("thumb"));
                                    } else {
                                        smb.setSp_picture_url("");
                                    }
                                    smb.setSp_check(true);
                                    smb.setSp_discount(jsonArray.getJSONObject(0).getString("details"));
                                    smb.setSp_price(jsonArray.getJSONObject(0).getString("price"));
                                    buycartshoplist.add(smb);
                                }
                            } else {
                                //购车里没有商品的时候
                                Log.i(TAG, "777777777777777777777777777777777777777777");
                                ShopMessageBean smb = new ShopMessageBean();
                                smb.setSp_count("1");
                                smb.setSp_id(jsonArray.getJSONObject(0).getString("id"));
                                smb.setCategory_id(jsonArray.getJSONObject(0).getString("id"));
                                smb.setSp_name(jsonArray.getJSONObject(0).getString("id"));
                                if (jsonArray.getJSONObject(0).getJSONArray("thumb").length() > 0) {
                                    smb.setSp_picture_url(jsonArray.getJSONObject(0).getJSONArray("thumb").getJSONObject(0).getString("thumb"));
                                } else {
                                    smb.setSp_picture_url("");
                                }
                                smb.setSp_check(true);
                                smb.setSp_discount(jsonArray.getJSONObject(0).getString("details"));
                                smb.setSp_price(jsonArray.getJSONObject(0).getString("price"));
                                buycartshoplist.add(smb);
                            }
                            //算数量的总个数
                            int dcount = 0;
                            double mone = 0.0;
                            for (int j = 0; j < buycartshoplist.size(); j++) {
                                mone += Double.parseDouble(buycartshoplist.get(j).getSp_price()) * Integer.parseInt(buycartshoplist.get(j).getSp_count());
                                dcount += Integer.parseInt(buycartshoplist.get(j).getSp_count());
                            }
                            if (buycartshoplist.size() > 0) {
                                showOrderList.setVisibility(View.VISIBLE);
                            } else {
                                showOrderList.setVisibility(View.INVISIBLE);
                            }

                            for (int l = 0; l < goodsitemlist.size(); l++) {
                                if (id == goodsitemlist.get(l).getId()) {
                                    int oldCount = Integer.parseInt(goodsitemlist.get(l).getShop_Count());
                                    oldCount++;
                                    goodsitemlist.get(l).setShop_Count("" + oldCount);
                                    if (!thesamelean) {
                                        buyShoppingList.add(goodsitemlist.get(l));
                                    }
                                    break;
                                }
                            }
                            shopCount.setText("" + dcount);
                            selectedgoodsmoney.setText(mone + "");
                            personAdapter.notifyDataSetChanged();
                        } else if (status == 0) {
                            Toast.makeText(OrderListActvity.this, "没有该商品", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;

                case 110:
                    Intent intent01 = new Intent(OrderListActvity.this, MakeSureTheOrderActivity.class);
                    if (buycartshoplist.size() > 0 && buyShoppingList.size() > 0) {
                        intent01.putExtra("listobj", (Serializable) buycartshoplist);
                        startActivity(intent01);
                    }
                    if (mPopupWindow != null) {
                        mPopupWindow.dismiss();
                    }
                    for (int i = 0; i < goodsitemlist.size(); i++) {
                        goodsitemlist.get(i).setShop_Count("0");
                    }
                    buycartshoplist.clear();
                    buyShoppingList.clear();
                    showOrderList.setVisibility(View.INVISIBLE);
                    shopCount.setText("0");
                    selectedgoodsmoney.setText("0.00");
                    personAdapter.notifyDataSetChanged();
                    break;

                case 120:
                    Intent inten = new Intent(OrderListActvity.this, MakeSureTheOrderActivity.class);
                    if (buycartshoplist.size() > 0) {
                        inten.putExtra("listobj", (Serializable) buycartshoplist);
                        startActivity(inten);
                    }
                    for (int i = 0; i < goodsitemlist.size(); i++) {
                        goodsitemlist.get(i).setShop_Count("0");
                    }
                    buycartshoplist.clear();
                    buyShoppingList.clear();
                    showOrderList.setVisibility(View.INVISIBLE);
                    shopCount.setText("0");
                    selectedgoodsmoney.setText("0.00");
                    personAdapter.notifyDataSetChanged();
                    break;
                case 200:
                    //popwindow 里 添加按钮  步骤如下：
                    //1、商品添加 2个购物车的数量也要添加  要做到一致
                    //这个是选中的商品  数量要加一  这里不存在没有的商品 所以 保存购物车的集合是有数据的  数量++就好了
                    Log.i("URL", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                    int addIndex = (int) msg.obj;
                    for (int i = 0; i < buycartshoplist.size(); i++) {
                        if (buycartshoplist.get(addIndex).getSp_id().equals(buycartshoplist.get(i).getSp_id())) {
                            int acount = Integer.parseInt(buycartshoplist.get(i).getSp_count());
                            acount++;
                            buycartshoplist.get(i).setSp_count(acount + "");
                            break;
                        }
                    }

                    for (int l = 0; l < buyShoppingList.size(); l++) {
                        if (buycartshoplist.get(addIndex).getSp_id().equals(buyShoppingList.get(l).getId() + "")) {
                            int acoun = Integer.parseInt(buyShoppingList.get(l).getShop_Count());
                            acoun++;
                            buyShoppingList.get(l).setShop_Count(acoun + "");
                            break;
                        }
                    }
                    int mcount = 0;
                    double mprice = 0.0;
                    for (int m = 0; m < buycartshoplist.size(); m++) {
                        mcount += Integer.parseInt(buycartshoplist.get(m).getSp_count());
                        mprice += Double.parseDouble(buycartshoplist.get(m).getSp_price()) * Integer.parseInt(buycartshoplist.get(m).getSp_count());
                    }
                    shopCount.setText("" + mcount);
                    checkCount.setText("已选" + buycartshoplist.size() + "件商品");
                    selectedgoodsmoney.setText(DoubleUtils.setSSWRDouble(mprice));
                    pop_price.setText(DoubleUtils.setSSWRDouble(mprice));
                    personAdapter.notifyDataSetChanged();
                    listAdapter.notifyDataSetChanged();

                    break;
                case 201:
                    //popwindow 里 减少按钮
                    int lessenIndex = (int) msg.obj;
                    //第一种做法
                    //第二种做法
                    for (int i = 0; i < buyShoppingList.size(); i++) {
                        if (buycartshoplist.get(lessenIndex).getSp_id().equals(buyShoppingList.get(i).getId() + "")) {
                            int acount = Integer.parseInt(buyShoppingList.get(i).getShop_Count());
                            if (acount > 0) {
                                acount--;
                                buyShoppingList.get(i).setShop_Count(acount + "");
                                if (acount == 0) {
                                    buyShoppingList.remove(i);
                                }
                            }
                            break;
                        }
                    }

                    int lcount = Integer.parseInt(buycartshoplist.get(lessenIndex).getSp_count());
                    if (lcount > 0) {
                        lcount--;
                        buycartshoplist.get(lessenIndex).setSp_count(lcount + "");
                        if (lcount == 0) {
                            buycartshoplist.remove(lessenIndex);
                        }
                    }
                    double oldmoney = 0;
                    for (int m = 0; m < buycartshoplist.size(); m++) {
                        oldmoney += Double.parseDouble(buycartshoplist.get(m).getSp_price()) * Integer.parseInt(buycartshoplist.get(m).getSp_count());
                    }
                    selectedgoodsmoney.setText(DoubleUtils.setSSWRDouble(oldmoney));
                    pop_price.setText(DoubleUtils.setSSWRDouble(oldmoney));
                    int mcoun = 0;
                    for (int m = 0; m < buycartshoplist.size(); m++) {
                        mcoun += Integer.parseInt(buycartshoplist.get(m).getSp_count());
                    }
                    shopCount.setText(mcoun + "");
                    if (buycartshoplist != null && buycartshoplist.size() > 0) {
                        checkCount.setText("商品" + buycartshoplist.size() + "件");
                    } else {
                        checkCount.setText("请选择商品");
                    }
                    if (buyShoppingList.size() == 0) {
                        showOrderList.setVisibility(View.GONE);
                    }
                    personAdapter.notifyDataSetChanged();
                    listAdapter.notifyDataSetChanged();
                    break;
                case 202:
                    int cinex = (int) msg.obj;
                    if (buycartshoplist.get(cinex).isSp_check()) {
                        buycartshoplist.get(cinex).setSp_check(false);
                    } else {
                        buycartshoplist.get(cinex).setSp_check(true);
                    }
                    listAdapter.notifyDataSetChanged();
                    break;
                case 511:
                    Toast.makeText(OrderListActvity.this, "网络超时，请重试", Toast.LENGTH_SHORT).show();
                    loading_dailog.dismiss();
                    break;

            }
        }
    };
    private LinearLayout jiesaun;
    private OrderListActvity mContent;
    private ImageView cart_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlist);
        mContent = OrderListActvity.this;
        buycartshoplist = new ArrayList<>();
        initView();
        initGetUserInfo();
        initGetData();
        action();
    }

    private void action() {
        showOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //把商品放入到购物车的集合中
                if (buyShoppingList.size() > 0) {
                    setData();
                    setPopWindow();
                }
            }
        });
        shopCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        f_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPopWindowMenu();
            }
        });


        ordermenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderListActvity.this, TheOrderListActivity.class));
                mPopupWindowMenu.dismiss();
            }
        });
        systemset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderListActvity.this, SystemSettingsActivity.class));
                mPopupWindowMenu.dismiss();
            }
        });
        quxiaoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindowMenu.dismiss();
            }
        });
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                //启动扫码
                Intent intent = new Intent("com.summi.scan");
                intent.setPackage("com.sunmi.sunmiqrcodescanner");
                intent.putExtra("CURRENT_PPI", 0X0003);//当前分辨率
                intent.putExtra("PLAY_SOUND", true);// 扫描完成声音提示  默认true
                intent.putExtra("PLAY_VIBRATE", true);
                //扫描完成震动,默认false，目前M1硬件支持震动可用该配置，V1不支持
                intent.putExtra("IDENTIFY_INVERSE_QR_CODE", true);// 识别反色二维码，默认true
                intent.putExtra("IDENTIFY_MORE_CODE", false);// 识别画面中多个二维码，默认false
                intent.putExtra("IS_SHOW_SETTING", false);// 是否显示右上角设置按钮，默认true
                intent.putExtra("IS_SHOW_ALBUM", false);// 是否显示从相册选择图片按钮，默认true
                startActivityForResult(intent, 520);

            }
        });
        search_head_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData();
                Message message = new Message();
                message.what = 33;
                handler.sendMessage(message);
            }
        });

        jiesaun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCarData();
                Message message = new Message();
                message.what = 120;
                handler.sendMessage(message);
            }
        });
    }

    private void setCarData() {
        buycartshoplist.clear();
        for (int i = 0; i < buyShoppingList.size(); i++) {
            ShopMessageBean smb = new ShopMessageBean();
            smb.setSp_check(true);
            smb.setSp_discount(buyShoppingList.get(i).getDetails());
            smb.setSp_price(buyShoppingList.get(i).getPrice());
            smb.setSp_count(buyShoppingList.get(i).getShop_Count());
            int scount = Integer.parseInt(buyShoppingList.get(i).getShop_Count());
            double lmoney = Double.parseDouble(buyShoppingList.get(i).getPrice());
            smb.setSp_picture_url(IpConfig.URL_GETPICTURE + buyShoppingList.get(i).getThumb().get(0).getThumb());
            smb.setCategory_id(buyShoppingList.get(i).getCategory_id() + "");
            smb.setSp_name(buyShoppingList.get(i).getName());
            smb.setSp_id(buyShoppingList.get(i).getId() + "");
            buycartshoplist.add(smb);
        }
    }

    /**
     * 数据的倒换
     */
    private void setData() {
        //先清展示购物车的数据
        buycartshoplist.clear();
        double smoney = 0.00;
        for (int i = 0; i < buyShoppingList.size(); i++) {
            ShopMessageBean smb = new ShopMessageBean();
            smb.setSp_check(true);
            smb.setSp_discount(buyShoppingList.get(i).getDetails());
            smb.setSp_price(buyShoppingList.get(i).getPrice());
            smb.setSp_count(buyShoppingList.get(i).getShop_Count());
            int scount = Integer.parseInt(buyShoppingList.get(i).getShop_Count());
            double lmoney = Double.parseDouble(buyShoppingList.get(i).getPrice());
            smoney += scount * lmoney;
            smb.setSp_picture_url(IpConfig.URL_GETPICTURE + buyShoppingList.get(i).getThumb().get(0).getThumb());
            smb.setCategory_id(buyShoppingList.get(i).getCategory_id() + "");
            smb.setSp_name(buyShoppingList.get(i).getName());
            smb.setSp_id(buyShoppingList.get(i).getId() + "");
            buycartshoplist.add(smb);
        }
        //popwindow-》商品列表
        if (shopview == null) {
            shopview = LayoutInflater.from(OrderListActvity.this).inflate(R.layout.activity_shopping_cart_pop_, null);
        }
//        allcheck = (CheckBox) shopview.findViewById(R.id.allCheckGoods);
        checkCount = (TextView) shopview.findViewById(R.id.checkCount);
        if (buycartshoplist.size() > 0) {
            checkCount.setText(buycartshoplist.size() + "件商品");
        }
        clear_cart = (LinearLayout) shopview.findViewById(R.id.clear_cart);
        shoplistview = (ListView) shopview.findViewById(R.id.shoplistview);
        pop_price = (TextView) shopview.findViewById(R.id.pop_price);
        cart_logo = (ImageView) shopview.findViewById(R.id.cart_logo);
        //结算
        settlement = (LinearLayout) shopview.findViewById(R.id.settlement);

        pop_price.setText("￥" + smoney);
        if (buycartshoplist != null) {
            listAdapter = new ListGoodsDetails_Adapter(OrderListActvity.this, buycartshoplist, handler);
            shoplistview.setAdapter(listAdapter);
        }

        clear_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = new Message();
                message.what = 3;
                handler.sendMessage(message);

            }
        });
        cart_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

//        allcheck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Message message = new Message();
//                message.what = 4;
//                handler.sendMessage(message);
//
//            }
//        });

        settlement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = new Message();
                message.what = 110;
                handler.sendMessage(message);

            }
        });
    }

    private void initGetData() {
        if (userInfo == null) {
            return;
        }
        String timestamp = System.currentTimeMillis() + "";
        String token = CreateToken.createToken(userInfo.getUuid(), timestamp, userInfo.getAccount());
        Map<String, String> loginMap = new HashMap<String, String>();
        loginMap.put("organization_id", userInfo.getOrganization_id());
        loginMap.put("account_id", userInfo.getAccount_id());
        Log.i("URL", "token" + token);
        loginMap.put("token", token);
        loginMap.put("timestamp", timestamp);
        Log.i("URL", "timestamp" + timestamp);
        if (!NetworkUtil.isNetworkAvailable(mContent)) {
            Toast.makeText(OrderListActvity.this, "网络不可用，请检查", Toast.LENGTH_SHORT).show();
            return;
        }
        loading_dailog = LoadingUtils.getDailog(mContent, Color.RED, "获取数据中。。。。");
        loading_dailog.show();
        NetUtils.netWorkByMethodPost(mContent, loginMap, IpConfig.URL_GOODSLIST, handler, 0);

    }

    /**
     * 获取用户信息
     */
    private void initGetUserInfo() {
        UserInfoImpl userInfoImpl = new UserInfoImpl(OrderListActvity.this);
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

        search_head_btn = (LinearLayout) findViewById(R.id.search_head_btn);
        jiesaun = (LinearLayout) findViewById(R.id.jiesaun);
        scan_btn = (LinearLayout) findViewById(R.id.scan_btn);
        showOrderList = (RelativeLayout) findViewById(R.id.showOrderList);
        parentView = (LinearLayout) findViewById(R.id.shoppingcart);
        f_menu = (LinearLayout) findViewById(R.id.f_menu);
        shopCount = (TextView) findViewById(R.id.goodsCount);
        mGoodsCateGoryList = (RecyclerView) findViewById(R.id.goods_category_list);
        mGoodsInfoRecyclerView = (RecyclerView) findViewById(R.id.goods_recycleView);
        selectedgoodsmoney = (TextView) findViewById(R.id.selectedgoodsmoney);
        //获取自身的长宽高
        parentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        popupHeight = parentView.getMeasuredHeight();
        popupWidth = parentView.getMeasuredWidth();
        //popwindow
        //menuViewPopwindow
        if (menuView == null) {
            menuView = LayoutInflater.from(OrderListActvity.this).inflate(R.layout.activity_seting_menu, null);
        }
        systemset = (LinearLayout) menuView.findViewById(R.id.systemset);
        ordermenu = (LinearLayout) menuView.findViewById(R.id.ordermenu);
        quxiaoMenu = (TextView) menuView.findViewById(R.id.quxiao);
        //menuViewPopwindow
    }

    //这个是有用的需要改动
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initData(List<GoodsCategroyListBean.DataBean.CategorylistBean> list) {
        mGoodsCategoryListAdapter = new RecycleGoodsCategoryListAdapter(catelist, OrderListActvity.this);
        mGoodsCateGoryList.setLayoutManager(new LinearLayoutManager(OrderListActvity.this));
        mGoodsCateGoryList.setAdapter(mGoodsCategoryListAdapter);
        mGoodsCategoryListAdapter.setOnItemClickListener(new RecycleGoodsCategoryListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mGoodsInfoRecyclerView.scrollToPosition(titlePois.get(position));
                mGoodsCategoryListAdapter.setCheckPosition(position);
            }
        });

        mLinearLayoutManager = new LinearLayoutManager(OrderListActvity.this, LinearLayoutManager.VERTICAL, false);
        myLayoutManage = new MyLayoutManage(OrderListActvity.this, LinearLayoutManager.VERTICAL, false);
        mGoodsInfoRecyclerView.setLayoutManager(mLinearLayoutManager);
        personAdapter = new PersonAdapter(OrderListActvity.this, goodsitemlist, catelist, handler);
        personAdapter.setmActivity(OrderListActvity.this);
        mGoodsInfoRecyclerView.setAdapter(personAdapter);
        mGoodsInfoRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                for (int i = 0; i < titlePois.size(); i++) {
                    if (mLinearLayoutManager.findFirstVisibleItemPosition() >= titlePois.get(i)) {
                        mGoodsCategoryListAdapter.setCheckPosition(i);
                    }
                }
            }
        });
    }

//    /**
//     * 商品信息列表的标题点击事件
//     *
//     * @param header
//     * @param headerId
//     */
//    @Override
//    public void onHeaderClick(View header, long headerId) {
//        TextView text = (TextView) header.findViewById(R.id.tvGoodsItemTitle);
//        Toast.makeText(OrderListActvity.this, "Click on " + text.getText(), Toast.LENGTH_SHORT).show();
//    }

    /**
     * 获取分类信息
     */
    public void getCategoryInfo() {

        if (userInfo == null) {
            return;
        }
        String timestamp = System.currentTimeMillis() + "";
        String token = CreateToken.createToken(userInfo.getUuid(), timestamp, userInfo.getAccount());
        Map<String, String> loginMap = new HashMap<String, String>();
        loginMap.put("organization_id", userInfo.getOrganization_id());
        loginMap.put("account_id", userInfo.getAccount_id());

        loginMap.put("token", token);
        loginMap.put("timestamp", timestamp);
        loading_dailog = LoadingUtils.getDailog(OrderListActvity.this, Color.RED, "获取数据中。。。。");
        loading_dailog.show();
        NetUtils.netWorkByMethodPost(OrderListActvity.this, loginMap, IpConfig.URL_CATEGORY, handler, 10);

    }

    public void setPopWindow() {

        mPopupWindow = new PopupWindow(shopview, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mPopupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
    }

    public void setPopWindowMenu() {
        mPopupWindowMenu = new PopupWindow(menuView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        mPopupWindowMenu.setTouchable(true);
        mPopupWindowMenu.setOutsideTouchable(false);
        mPopupWindowMenu.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mPopupWindowMenu.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //处理扫描结果（在界面上显示）
        if (requestCode == 520 && data != null) {
                Bundle bundle = data.getExtras();
            ArrayList<HashMap<String, String>> result = (ArrayList<HashMap<String, String>>) bundle.getSerializable("data");
            Iterator<HashMap<String, String>> it = result.iterator();
            while (it.hasNext()) {
                HashMap<String, String> hashMap = it.next();
                Log.w("URL", "扫描结果：：：" + hashMap.get("VALUE"));
                intoSearchGoods(hashMap.get("VALUE"));
                }
            }
    }

    /**
     * 这个是扫码后的结果
     *
     * @param result
     */
    private void intoSearchGoods(String result) {
        Map<String, String> map = MapUtilsSetParam.getMap(OrderListActvity.this);
        map.put("scan_code", result);
        loading_dailog = LoadingUtils.getDailog(OrderListActvity.this, Color.RED, "搜索中。。。。");
        loading_dailog.show();
        NetUtils.netWorkByMethodPost(OrderListActvity.this, map, IpConfig.URL_SERACH, handler, 12);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                customDialog();
                break;
        }
        return true;
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
                OrderListActvity.this.finish();
                System.exit(0);
            }
        });
        dialog.show();
    }

    /**
     * 处理滑动 是两个ListView联动
     * 需要改动
     */
    private class MyOnGoodsScrollListener extends RecyclerView.OnScrollListener implements AbsListView.OnScrollListener {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {


        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (!(lastTitlePoi == goodsitemlist.get(firstVisibleItem).getId())) {
                lastTitlePoi = goodsitemlist.get(firstVisibleItem).getId();
                mGoodsCategoryListAdapter.setCheckPosition(goodsitemlist.get(firstVisibleItem).getId());
                mGoodsCategoryListAdapter.notifyDataSetChanged();
            }
        }
    }

    class MyLayoutManage extends LinearLayoutManager {

        public MyLayoutManage(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        @Override
        public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
            if (getChildCount() > 0) {
                View view = recycler.getViewForPosition(0);
                if (view != null) {
                    measureChild(view, widthSpec, heightSpec);
                    int measuredWidth = View.MeasureSpec.getSize(widthSpec);
                    int measuredHeight = view.getMeasuredHeight();
                    setMeasuredDimension(measuredWidth, measuredHeight);
                }
            } else {
                super.onMeasure(recycler, state, widthSpec, heightSpec);
            }
        }
    }
}
