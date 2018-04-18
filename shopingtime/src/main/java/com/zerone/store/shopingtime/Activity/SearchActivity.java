package com.zerone.store.shopingtime.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zerone.store.shopingtime.Adapter.MyAdapter;
import com.zerone.store.shopingtime.Adapter.cart_list.ListGoodsDetails_Adapter;
import com.zerone.store.shopingtime.BaseActivity.BaseAppActivity;
import com.zerone.store.shopingtime.Bean.ShopBean;
import com.zerone.store.shopingtime.Bean.shoplistbean.ShopMessageBean;
import com.zerone.store.shopingtime.Contants.IpConfig;
import com.zerone.store.shopingtime.R;
import com.zerone.store.shopingtime.Utils.DoubleUtils;
import com.zerone.store.shopingtime.Utils.LoadingUtils;
import com.zerone.store.shopingtime.Utils.MapUtilsSetParam;
import com.zerone.store.shopingtime.Utils.NetUtils;
import com.zyao89.view.zloading.ZLoadingDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by on 2018/4/9 0009 16 25.
 * Author  LiuXingWen
 */

public class SearchActivity extends BaseAppActivity {

    private List<ShopBean> list;
    private List<ShopBean> lists;
    private EditText search_message;
    private ImageView search_img_btn;
    private ZLoadingDialog loading_dailog;
    private LinearLayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    private RecyclerView goods_recycleView;

    private List<ShopMessageBean> listBuy;
    private RelativeLayout showOrderList;
    private TextView goodsCount;
    private TextView search_money;
    private View shopview;
    private ListGoodsDetails_Adapter listAdapter;
    private LinearLayout settlement;
    private TextView pop_price;
    private ListView shoplistview;
    private LinearLayout clear_cart;
    private CheckBox allcheck;
    private TextView checkCount;
    private PopupWindow mPopupWindow;
    private LinearLayout parentView;
    private double money = 0.0;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    loading_dailog.dismiss();
                    String searJSon = (String) msg.obj;
                    list.clear();
                    try {
                        JSONObject jsonObject = new JSONObject(searJSon);
                        int status = jsonObject.getInt("status");
                        if (status == 1) {
                            JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("goodslist");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                ShopBean shopBean = new ShopBean();
                                shopBean.setShop_Count("10");
                                shopBean.setName(jsonArray.getJSONObject(i).getString("name"));
                                shopBean.setCategory_id(jsonArray.getJSONObject(i).getInt("category_id"));
                                shopBean.setCategory_name(jsonArray.getJSONObject(i).getString("category_name"));
                                shopBean.setDetails(jsonArray.getJSONObject(i).getString("details"));
                                shopBean.setId(jsonArray.getJSONObject(i).getInt("id"));
                                shopBean.setPrice(jsonArray.getJSONObject(i).getString("price"));
                                shopBean.setStock(jsonArray.getJSONObject(i).getInt("stock"));
                                JSONArray thumb1 = jsonArray.getJSONObject(i).getJSONArray("thumb");
                                List<ShopBean.ThumbBean> thumb = new ArrayList<>();
                                for (int j = 0; j < thumb1.length(); j++) {
                                    String thumb2 = thumb1.getJSONObject(j).getString("thumb");
                                    ShopBean.ThumbBean tb = new ShopBean.ThumbBean();
                                    tb.setThumb(thumb2);
                                    thumb.add(tb);
                                }
                                shopBean.setThumb(thumb);
                                list.add(shopBean);
                            }
                            mAdapter.notifyDataSetChanged();
                        } else if (status == 0) {
                            Toast.makeText(SearchActivity.this, "没有搜到该关键字的商品哦", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case 10:
                    //点击了条目
                    int index = (int) msg.obj;
                    if (listBuy.size() > 0) {
                        //1.0设置一个int值来记录通一个商品的下标 , 用一个Boolean 来判断是否是同一个商品 默认是不同一个商品
                        boolean thesamelean = false;
                        int tsIndex = 0;
                        //1、判断是否是同一个商品
                        for (int i = 0; i < listBuy.size(); i++) {
                            if (list.get(index).getId() == Integer.parseInt(listBuy.get(i).getSp_id())) {
                                thesamelean = true;
                                tsIndex = i;
                                break;
                            }
                        }
                        if (thesamelean) {
                            // 2、同一个商品 数量加一
                            String sp_count = listBuy.get(tsIndex).getSp_count();
                            int count = Integer.parseInt(sp_count);
                            count++;
                            ShopMessageBean smb = new ShopMessageBean();
                            smb.setSp_count(count + "");
                            smb.setSp_id(listBuy.get(tsIndex).getSp_id() + "");
                            smb.setCategory_id(listBuy.get(tsIndex).getCategory_id() + "");
                            smb.setSp_name(listBuy.get(tsIndex).getSp_name());
                            smb.setSp_picture_url(listBuy.get(tsIndex).getSp_picture_url());
                            smb.setSp_check(true);
                            smb.setSp_discount(listBuy.get(tsIndex).getSp_discount());
                            smb.setSp_price(listBuy.get(tsIndex).getSp_price());
                            listBuy.set(tsIndex, smb);
                        } else {
                            //3、不同商品 从新添加
                            ShopBean shopBean = list.get(index);
                            ShopMessageBean smb = new ShopMessageBean();
                            smb.setSp_count("1");
                            smb.setSp_id(shopBean.getId() + "");
                            smb.setCategory_id(shopBean.getCategory_id() + "");
                            smb.setSp_name(shopBean.getName());
                            if (shopBean.getThumb().size() > 0) {
                                smb.setSp_picture_url(shopBean.getThumb().get(0).getThumb());
                            } else {
                                smb.setSp_picture_url("");
                            }
                            smb.setSp_check(true);
                            smb.setSp_discount(shopBean.getDetails());
                            smb.setSp_price(shopBean.getPrice());
                            listBuy.add(smb);
                        }
                    } else {

                        ShopBean shopBean = list.get(index);
                        ShopMessageBean smb = new ShopMessageBean();
                        smb.setSp_count("1");
                        smb.setSp_id(shopBean.getId() + "");
                        smb.setCategory_id(shopBean.getCategory_id() + "");
                        smb.setSp_name(shopBean.getName());
                        if (shopBean.getThumb().size() > 0) {
                            smb.setSp_picture_url(shopBean.getThumb().get(0).getThumb());
                        } else {
                            smb.setSp_picture_url("");
                        }
                        smb.setSp_check(true);
                        smb.setSp_discount(shopBean.getDetails());
                        smb.setSp_price(shopBean.getPrice());
                        listBuy.add(smb);
                    }
                    //算数量的总个数
                    int dcount = 0;
                    money = 0.0;
                    for (int j = 0; j < listBuy.size(); j++) {
                        money += Double.parseDouble(listBuy.get(j).getSp_price()) * Integer.parseInt(listBuy.get(j).getSp_count());
                        dcount += Integer.parseInt(listBuy.get(j).getSp_count());
                    }
                    boolean thesamelean = false;
                    int tsIndex = 0;
                    if (listBuy.size() > 0) {
                        showOrderList.setVisibility(View.VISIBLE);
                    } else {
                        showOrderList.setVisibility(View.INVISIBLE);
                    }
                    goodsCount.setText("" + dcount);
                    search_money.setText("￥" + money);
                    break;
                case 511:
                    Toast.makeText(SearchActivity.this, "网络超时，请重试", Toast.LENGTH_SHORT).show();
                    loading_dailog.dismiss();
                    break;


                case 200:
                    //添加
                    int addIndex = (int) msg.obj;
                    //同一个商品数量加一
                    boolean thesame = false;
                    Integer tindex = null;
                    for (int i = 0; i < listBuy.size(); i++) {
                        if (listBuy.get(addIndex).getSp_id().equals(listBuy.get(i).getSp_id())) {
                            thesame = true;
                            tindex = i;
                            break;
                        }
                    }
                    if (thesame) {
                        //同一个商品数量加一
                        int tcount = Integer.parseInt(listBuy.get(tindex).getSp_count());
                        tcount++;
                        listBuy.get(tindex).setSp_count(tcount + "");
                    }
                    double dm = 0.0;
                    int ac = 0;
                    for (int i = 0; i < listBuy.size(); i++) {
                        dm += Double.parseDouble(listBuy.get(i).getSp_price()) * Integer.parseInt(listBuy.get(i).getSp_count());
                        ac += Integer.parseInt(listBuy.get(i).getSp_count());
                    }
                    goodsCount.setText("" + ac);
                    pop_price.setText("￥" + DoubleUtils.setSSWRDouble(dm));
                    search_money.setText("￥" + DoubleUtils.setSSWRDouble(dm));
                    if (listBuy.size() > 0) {
                        checkCount.setText("已选商品" + listBuy.size() + "件");
                    }
                    listAdapter.notifyDataSetChanged();
                    break;
                case 201:
                    //减少
                    //添加
                    int aIndex = (int) msg.obj;
                    //同一个商品数量加一
                    //
                    int acount = Integer.parseInt(listBuy.get(aIndex).getSp_count());
                    if (acount > 0) {
                        acount--;
                        if (acount == 0) {
                            listBuy.remove(aIndex);
                        } else {
                            listBuy.get(aIndex).setSp_count(acount + "");
                        }
                    }
                    double d = 0.0;
                    int count = 0;
                    for (int i = 0; i < listBuy.size(); i++) {
                        d += Double.parseDouble(listBuy.get(i).getSp_price()) * Integer.parseInt(listBuy.get(i).getSp_count());
                        count += Integer.parseInt(listBuy.get(i).getSp_count());
                    }
                    search_money.setText("￥" + DoubleUtils.setSSWRDouble(d));
                    goodsCount.setText("" + count);
                    pop_price.setText("￥" + DoubleUtils.setSSWRDouble(d));
                    if (listBuy.size() > 0) {
                        checkCount.setText("已选商品" + listBuy.size() + "件");
                    } else {
                        checkCount.setText("亲，没有商品了哦");
                    }

                    listAdapter.notifyDataSetChanged();
                    break;

                case 3:
                    /*清空购物车*/
                    listBuy.clear();
                    search_money.setText("￥ 0");
                    goodsCount.setText("");
                    pop_price.setText("0.00");
                    checkCount.setText("亲，没有商品了哦");
                    showOrderList.setVisibility(View.GONE);
                    listAdapter.notifyDataSetChanged();
                    break;


            }
        }
    };
    private LinearLayout shoppingcart;
    private Intent intent;
    private List<ShopMessageBean> listObj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_orderlist);
        intent = getIntent();
        lists = (ArrayList<ShopBean>) getIntent().getSerializableExtra("listobj");
        list = new ArrayList<>();
        listBuy = new ArrayList<>();
        initData();
        initView();
        action();
    }

    private void initData() {
        if (lists != null) {
            for (int i = 0; i < lists.size(); i++) {
                ShopBean shopBean = lists.get(i);
                ShopMessageBean smb = new ShopMessageBean();
                smb.setSp_count(lists.get(i).getShop_Count());
                smb.setSp_id(lists.get(i).getId() + "");
                smb.setCategory_id(lists.get(i).getCategory_id() + "");
                smb.setSp_name(lists.get(i).getName());
                if (lists.get(i).getThumb().size() > 0) {
                    smb.setSp_picture_url(lists.get(i).getThumb().get(0).getThumb());
                } else {
                    smb.setSp_picture_url("");
                }
                smb.setSp_discount(lists.get(i).getDetails());
                smb.setSp_price(lists.get(i).getPrice());
                listBuy.add(smb);
            }
        }

    }

    /**
     * 初始view
     */
    private void initView() {
        search_message = (EditText) findViewById(R.id.search_message);
        search_img_btn = (ImageView) findViewById(R.id.search_btn);
        showOrderList = (RelativeLayout) findViewById(R.id.showOrderList);
        goodsCount = (TextView) findViewById(R.id.goodsCount);
        search_money = (TextView) findViewById(R.id.search_money);
        int dcounts = 0;
        for (int i = 0; i < lists.size(); i++) {
            money += Double.parseDouble(lists.get(i).getPrice()) * Integer.parseInt(lists.get(i).getShop_Count());
            dcounts += Integer.parseInt(lists.get(i).getShop_Count());
        }
        search_money.setText("￥" + DoubleUtils.setSSWRDouble(money));
        if (dcounts > 0) {
            showOrderList.setVisibility(View.VISIBLE);
            goodsCount.setText(dcounts + "");
        }
        parentView = (LinearLayout) findViewById(R.id.shoppingcart);
        shoppingcart = (LinearLayout) findViewById(R.id.shoppingcart);

        goods_recycleView = (RecyclerView) findViewById(R.id.goods_recycleView);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        goods_recycleView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(list, SearchActivity.this);
        goods_recycleView.setAdapter(mAdapter);
    }

    /**
     * 控件的点击事件
     */
    private void action() {
        search_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = search_message.getText().toString().trim();
                intoSearch(msg);
            }
        });

        if (mAdapter != null) {
            mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Message message = new Message();
                    message.what = 10;
                    message.obj = position;
                    handler.sendMessage(message);
                }
            });
        }

        shoppingcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //把商品放入到购物车的集合中
                setData();
                if (listBuy.size() > 0 && listBuy != null) {
                    setPopWindow();
                } else {
                    Toast.makeText(SearchActivity.this, "还没有商品，请搜索", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 所搜商品
     */
    private void intoSearch(String msg) {
        Map<String, String> map = MapUtilsSetParam.getMap(SearchActivity.this);
        map.put("keyword", msg);
        loading_dailog = LoadingUtils.getDailog(SearchActivity.this, Color.RED, "搜索中。。。。");
        loading_dailog.show();
        NetUtils.netWorkByMethodPost(SearchActivity.this, map, IpConfig.URL_SERACH, handler, 0);
    }

    /**
     * 数据的倒换
     */
    private void setData() {
        //popwindow-》商品列表
        if (shopview == null) {
            shopview = LayoutInflater.from(SearchActivity.this).inflate(R.layout.activity_shopping_cart_pop_, null);
        }
        checkCount = (TextView) shopview.findViewById(R.id.checkCount);
        checkCount.setText(listBuy.size() + "件商品");
        clear_cart = (LinearLayout) shopview.findViewById(R.id.clear_cart);
        shoplistview = (ListView) shopview.findViewById(R.id.shoplistview);
        pop_price = (TextView) shopview.findViewById(R.id.pop_price);
        //结算
        settlement = (LinearLayout) shopview.findViewById(R.id.settlement);
        if (listBuy != null) {
            int dmoney = 0;
            for (int i = 0; i < listBuy.size(); i++) {
                dmoney += Integer.parseInt(listBuy.get(i).getSp_count()) * Double.parseDouble(listBuy.get(i).getSp_price());
            }
            pop_price.setText("￥" + DoubleUtils.setSSWRDouble(dmoney));
            listAdapter = new ListGoodsDetails_Adapter(SearchActivity.this, listBuy, handler);
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

        settlement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listBuy.size() == 0) {
                    Toast.makeText(SearchActivity.this, "没有商品了哦，请重新选择", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(SearchActivity.this, MakeSureTheOrderActivity.class);
                intent.putExtra("listobj", (Serializable) listBuy);
                startActivity(intent);
                mPopupWindow.dismiss();
                listBuy.clear();
                SearchActivity.this.finish();
            }
        });

    }

    public void setPopWindow() {

        mPopupWindow = new PopupWindow(shopview, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mPopupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
    }
}
