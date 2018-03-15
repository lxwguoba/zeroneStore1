package com.store.zerone.zeronestore.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.eowise.recyclerview.stickyheaders.OnHeaderClickListener;
import com.eowise.recyclerview.stickyheaders.StickyHeadersBuilder;
import com.eowise.recyclerview.stickyheaders.StickyHeadersItemDecoration;
import com.store.zerone.zeronestore.R;
import com.store.zerone.zeronestore.adapter.BigramHeaderAdapter;
import com.store.zerone.zeronestore.adapter.PersonAdapter;
import com.store.zerone.zeronestore.adapter.RecycleGoodsCategoryListAdapter;
import com.store.zerone.zeronestore.adapter.options.ListViewOptionTitle;
import com.store.zerone.zeronestore.contanst.ContantData;
import com.store.zerone.zeronestore.contanst.IpConfig;
import com.store.zerone.zeronestore.domain.GoodsListBean;
import com.store.zerone.zeronestore.domain.TableDBean;
import com.store.zerone.zeronestore.domain.options.OptionsBean;
import com.store.zerone.zeronestore.domain.options.OptionsBeanItem;
import com.store.zerone.zeronestore.event.AcceptMessage;
import com.store.zerone.zeronestore.event.GoodsListEvent;
import com.store.zerone.zeronestore.utils.LoadingUtils;
import com.store.zerone.zeronestore.utils.MapUtilsSetParam;
import com.store.zerone.zeronestore.utils.NetUtils;
import com.store.zerone.zeronestore.utils.Utils;
import com.zyao89.view.zloading.ZLoadingDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * Created by on 2018/1/30 0030 11 31.
 * Author  LiuXingWen
 */

public class ShopingFragment extends Fragment implements PersonAdapter.OnShopCartGoodsChangeListener, OnHeaderClickListener {
    //商品类别列表
    //商品类别列表
    private List<GoodsListBean.DataEntity.GoodscatrgoryEntity> goodscatrgoryEntities = new ArrayList<>();
    //商品列表
    private List<GoodsListBean.DataEntity.GoodscatrgoryEntity.GoodsitemEntity> goodsitemEntities = new ArrayList<>();
    private View view;
    //    private TextView table_btn;
    private RecyclerView mGoodsCateGoryList;
    private RecyclerView recyclerView;
    private PersonAdapter personAdapter;
    private BigramHeaderAdapter headerAdapter;
    //分类的适配器
    private RecycleGoodsCategoryListAdapter mGoodsCategoryListAdapter;
    //recycle的线性布局的样式
    private LinearLayoutManager mLinearLayoutManager;
    //存储含有标题的第一个含有商品类别名称的条目的下表
    private List<Integer> titlePois = new ArrayList<>();
    //上一个标题的小标位置
    private int lastTitlePoi;
    private StickyHeadersItemDecoration top;
    private ZLoadingDialog loading_dailog;
    //默认选中的规格现拼接的数据
    private String specs = "";
    //这个是规格的详情页面集合
    private List<OptionsBeanItem> optionBeenList;
    //这个是规格的titlte
    private List<OptionsBean> optionslist;
    private View moptions;
    private TextView price;
    private Button bsure;
    private ListView oplist;
    private LinearLayout parentview;
    private ListViewOptionTitle opAdapter;
    private PopupWindow mPopupWindow;
    private TextView qrScan;

    public  static   final  Integer REQUEST_CODE=520;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_order_dc, null);
        }
//-----------------------------测试--------------------------
//        qrScan = (TextView) view.findViewById(R.id.qr_scan);
//        qrScan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getContext(), ScanQR.class));
//            }
//        });
        //-----------------------------测试--------------------------
        //注册广播
        if(!EventBus.getDefault().isRegistered(this)){//加上判断
            EventBus.getDefault().register(this);//订阅
        }

        initView(view);
        initListenner();
        initLoadingData();
        initOptopnsListenner();
        return view;
    }


    /**
     * 规格选择
     */
    private void initOptopnsListenner() {
        oplist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                     //这个需要改动
                    if (specs.length() > 0) {
                        specs = specs + "_" + optionslist.get(position).getId();
                    } else {
                        specs = optionslist.get(position).getId();
                    }
                 String goodsid = optionslist.get(position).getGoodsid();

                getMoney(specs,goodsid);
            }
        });

        bsure.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                JSONObject optionsbean = Utils.getACache(getContext()).getAsJSONObject("optionsbean");
                String position_gg = Utils.getACache(getContext()).getAsString("position_gg");
                 if (position_gg==null){
                     position_gg="0";
                 }
                 Log.i("URLL",position_gg);
                try {
                    personAdapter.setListOptions(optionsbean,Integer.parseInt(position_gg));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mPopupWindow.dismiss();
            }
        });
    }

    /**
     * 网若获取数据
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initLoadingData() {
        /**
         * profile_id ：用户id
         * branchid：分店id
         */
        Map<String, String> map = MapUtilsSetParam.getMap(getContext());
        try {
            map.put("opp", "goodslist");
            map.put("branchid","4");
            map.put("profile_id", "-1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        loading_dailog = LoadingUtils.getDailog(getContext(), Color.RED, "获取商品数据中。。。。");
        loading_dailog.show();
        NetUtils.netWorkByMethodPost(getContext(), map, IpConfig.URL, handler, ContantData.GETGOODSINFO);

    }

    private void initListenner() {

    }

    /**
     * view的实力化
     *
     * @param view
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initView(View view) {
        parentview = (LinearLayout) view.findViewById(R.id.parentview);
        optionslist =new ArrayList<>();
        mGoodsCateGoryList = (RecyclerView) view.findViewById(R.id.goods_category_list);
        recyclerView = (RecyclerView) view.findViewById(R.id.goods_recycleView);
        //-----------------------------------opopwindow--------------------------------
        if(moptions == null){
            moptions = LayoutInflater.from(getContext()).inflate(R.layout.activity_options, null);
        }
            oplist = (ListView)moptions.findViewById(R.id.title);
            //选择价格后需要改变
            price = (TextView)moptions.findViewById(R.id.price);
            bsure = (Button)moptions.findViewById(R.id.options_btn);
            opAdapter = new ListViewOptionTitle(getContext(), optionslist, handler);
            oplist.setAdapter(opAdapter);
        //-----------------------------------opopwindow--------------------------------
    }

    /**
     * 启动Activity的返回页面
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ContantData.REQUESTCODE && resultCode == RESULT_OK) {
            TableDBean.DataBean tableBean = (TableDBean.DataBean) data.getSerializableExtra("tableInfo");
            String peoplecount = data.getStringExtra("peoplecount");
            Utils.getACache(getContext()).put("peoplecount", peoplecount);
            Utils.getACache(getContext()).put("tableBean", tableBean);
            Message message = new Message();
            message.what = 10;
            message.obj = tableBean;
            handler.sendMessage(message);
        }
    }

    Handler handler = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 2:
                    String title_item = (String) msg.obj;
                    String[] arr = title_item.split("==");
                    Integer indexFu = Integer.parseInt(arr[0]);
                    Integer indexZi = Integer.parseInt(arr[1]);
                    String goodsid  = optionslist.get(indexFu).getGoodsid();
                    String title = optionslist.get(indexFu).getItems().get(indexZi).getTitle();
                    String id = optionslist.get(indexFu).getItems().get(indexZi).getId();
                    String[] sarr = specs.split("_");
                    for (int i = 0; i < sarr.length; i++) {
                        if (i == 0) {
                            if (indexFu == 0) {
                                specs = id;
                            } else {
                                specs = sarr[i];
                            }
                        } else {
                            if (indexFu == i) {
                                specs = specs + "_" + id;
                            } else {
                                specs = specs + "_" + sarr[i];
                            }
                        }
                    }
                    getMoney(specs,goodsid);
                    break;
                //获取到了桌子的数据和人数
                case 10:
                    String peoplecount = Utils.getACache(getContext()).getAsString("peoplecount");
                    TableDBean.DataBean tableBean = (TableDBean.DataBean) msg.obj;
                    break;
                case ContantData.GETGOODSINFO:
                    String goodsinfojson = (String) msg.obj;
                    Log.i("BBB",goodsinfojson);
                    try {
                        int f = 0;
                        int l = 0;
                        boolean isFirst;
                        JSONObject jsob = new JSONObject(goodsinfojson);
                        int goodsnum = jsob.getInt("goodsnum");
                        JSONArray goodslist = jsob.getJSONArray("goodslist");
//                       //获取分类第一级：：：
                        List<GoodsListBean.DataEntity.GoodscatrgoryEntity> list = new ArrayList<>();
                        for (int i = 0; i < goodslist.length(); i++) {
                            isFirst = true;
                            GoodsListBean.DataEntity.GoodscatrgoryEntity onBean = new GoodsListBean.DataEntity.GoodscatrgoryEntity();
                            JSONObject oneobj = goodslist.getJSONObject(i);
                            onBean.setName(oneobj.getString("name"));
                            onBean.setC_id(oneobj.getInt("id"));
                            List<GoodsListBean.DataEntity.GoodscatrgoryEntity.GoodsitemEntity> onelist = new ArrayList<>();
                            JSONArray gooslist = oneobj.getJSONArray("gooslist");
                            for (int j = 0; j < gooslist.length(); j++) {
                                GoodsListBean.DataEntity.GoodscatrgoryEntity.GoodsitemEntity twoBean = new GoodsListBean.DataEntity.GoodscatrgoryEntity.GoodsitemEntity();
                                JSONObject twoObj = gooslist.getJSONObject(j);
                                twoBean.setHasoption(twoObj.getString("hasoption"));
                                twoBean.setId(twoObj.getString("id"));
                                twoBean.setMarketprice(twoObj.getString("marketprice"));
                                twoBean.setThumb(twoObj.getString("thumb"));
                                twoBean.setTotal(twoObj.getString("total"));
                                twoBean.setTitle(twoObj.getString("title"));
                                twoBean.setYuanjia(twoObj.getString("yuanjia"));
                                twoBean.setTotal_in_cart(twoObj.getInt("total_in_cart"));
                                List<GoodsListBean.DataEntity.GoodscatrgoryEntity.GoodsitemEntity.OptionsBean> option = new ArrayList<>();
                                JSONArray options = twoObj.getJSONArray("options");
                                for (int k = 0; k < options.length(); k++) {
                                    GoodsListBean.DataEntity.GoodscatrgoryEntity.GoodsitemEntity.OptionsBean ob = new GoodsListBean.DataEntity.GoodscatrgoryEntity.GoodsitemEntity.OptionsBean();
                                    JSONObject jsonObject = options.getJSONObject(k);
                                    ob.setOptionid(jsonObject.getInt("optionid"));
                                    ob.setOptionname(jsonObject.getString("optionname"));
                                    option.add(ob);
                                }
                                if (isFirst) {
                                    titlePois.add(l);
                                    isFirst = false;
                                }
                                twoBean.setOptions(option);
                                onelist.add(twoBean);
                                l++;
                                twoBean.setGb_id(f);
                                goodsitemEntities.add(twoBean);
                            }
                            onBean.setGoodsitem(onelist);
                            goodscatrgoryEntities.add(onBean);
                            f++;
                        }
                        initData(list);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        loading_dailog.dismiss();
                    }
                    break;

                case 10000:
                    //打开选择规格页面
                   int pos= (int) msg.obj;
                    Utils.getACache(getContext()).put("position_gg",pos+"");
                    goodsitemEntities.get(pos).getHasoption();
                    //获取规格和规格项
                    Map<String, String> map = MapUtilsSetParam.getMap(getContext());
                    map.put("opp", "getGoodsSpecs");
                    map.put("goodsid", goodsitemEntities.get(pos).getId());
                    NetUtils.netWorkByMethodPost(getContext(), map, IpConfig.URL, handler, ContantData.GETOPTIONS);
                    break;

                case  ContantData.GETOPTIONS:
                    String optionsJson = (String) msg.obj;
                    optionslist.clear();
                    Log.d("OptionsActivity", "1106::::::::::::::"+optionsJson);
                    try {
                        JSONObject jsonObject = new JSONObject(optionsJson);
                        JSONArray array = jsonObject.getJSONArray("data");
                        specs="";
                        for (int i = 0; i < array.length(); i++) {
                            OptionsBean opb = new OptionsBean();
                            opb.setId(array.getJSONObject(i).getString("id"));
                            opb.setTitle(array.getJSONObject(i).getString("title"));
                            opb.setContent(array.getJSONObject(i).getString("content"));
                            opb.setDescription(array.getJSONObject(i).getString("description"));
                            opb.setDisplayorder(array.getJSONObject(i).getString("displayorder"));
                            opb.setDisplaytype(array.getJSONObject(i).getString("displaytype"));
                            opb.setGoodsid(array.getJSONObject(i).getString("goodsid"));
                            opb.setWeid(array.getJSONObject(i).getString("weid"));
                            JSONArray array1 = array.getJSONObject(i).getJSONArray("items");
                            List<OptionsBeanItem> opblist = new ArrayList<>();
                            for (int j = 0; j < array1.length(); j++) {
                                OptionsBeanItem obi = new OptionsBeanItem();
                                JSONObject ob = array1.getJSONObject(j);
                                //拼规格项
                                //拼规格项
                                if (j == 0) {
                                    if (specs.length() > 0) {
                                        specs = specs + "_" + ob.getString("id");
                                    } else {
                                        specs = ob.getString("id");
                                    }
                                }
                                obi.setWeid(ob.getString("weid"));
                                obi.setDisplayorder(ob.getString("displayorder"));
                                obi.setId(ob.getString("id"));
                                obi.setShow(ob.getString("show"));
                                obi.setSpecid(ob.getString("specid"));
                                obi.setThumb(ob.getString("thumb"));
                                obi.setTitle(ob.getString("title"));
                                opblist.add(obi);
                            }
                            opb.setItems(opblist);
                            optionslist.add(opb);
                        }
                        opAdapter.notifyDataSetChanged();
                        setPopWindow();
                        getMoney(specs,array.getJSONObject(0).getString("goodsid"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case ContantData.GETOPTIONSPRICE:
                    String moneyJson = (String) msg.obj;
                    Log.i("CCC",moneyJson);
                    try {
                        JSONObject jbt = new JSONObject(moneyJson);
                        int status = jbt.getInt("status");
                        if (status == 1) {
                            JSONObject data = jbt.getJSONObject("data");
                            price.setText(data.getString("marketprice"));
                            Utils.getACache(getContext()).put("optionsbean",data);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    //获取价格
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getMoney(String specs, String goodsid) {
        Log.i("FFF",specs);
        String memberid ="";
        Map<String, String> ma = MapUtilsSetParam.getMap(getContext());
        ma.put("opp", "getOptionPrice");
        ma.put("goodsid", goodsid);
        if (memberid==null){
            memberid="-1";
        }
        ma.put("memberid", memberid);
        ma.put("specs", specs);

        NetUtils.netWorkByMethodPost(getContext(), ma, IpConfig.URL, handler, ContantData.GETOPTIONSPRICE);
    }

    @Override
    public void onHeaderClick(View header, long headerId) {
        TextView text = (TextView) header.findViewById(R.id.tvGoodsItemTitle);
        Toast.makeText(getActivity(), "Click on " + text.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNumChange() {

    }

    /**
     * 处理滑动 是两个ListView联动
     * 需要改动
     */
    private class MyOnGoodsScrollListener implements AbsListView.OnScrollListener {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (!(lastTitlePoi == goodsitemEntities.get(firstVisibleItem).getGb_id())) {
                lastTitlePoi = goodsitemEntities.get(firstVisibleItem).getGb_id();
                mGoodsCategoryListAdapter.setCheckPosition(goodsitemEntities.get(firstVisibleItem).getGb_id());
                mGoodsCategoryListAdapter.notifyDataSetChanged();
            }
        }
    }

    //这个是有用的需要改动
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initData(List<GoodsListBean.DataEntity.GoodscatrgoryEntity> list) {
        mGoodsCategoryListAdapter = new RecycleGoodsCategoryListAdapter(goodscatrgoryEntities, getActivity());
        mGoodsCateGoryList.setLayoutManager(new LinearLayoutManager(getContext()));
        mGoodsCateGoryList.setAdapter(mGoodsCategoryListAdapter);
        mGoodsCategoryListAdapter.setOnItemClickListener(new RecycleGoodsCategoryListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                recyclerView.scrollToPosition(titlePois.get(position) + position + 2);
                mGoodsCategoryListAdapter.setCheckPosition(position);
            }
        });
        mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        personAdapter = new PersonAdapter(getActivity(), goodsitemEntities, goodscatrgoryEntities,handler);
        personAdapter.setmActivity(getActivity());
        headerAdapter = new BigramHeaderAdapter(getActivity(), goodsitemEntities, goodscatrgoryEntities);
        top = new StickyHeadersBuilder()
                .setAdapter(personAdapter)
                .setRecyclerView(recyclerView)
                .setStickyHeadersAdapter(headerAdapter)
                .setOnHeaderClickListener(this)
                .build();
        recyclerView.addItemDecoration(top);
        recyclerView.setAdapter(personAdapter);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
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

        loading_dailog.dismiss();
    }

    /**
     * 添加 或者  删除  商品发送的消息处理
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(GoodsListEvent event) {
        if (event.buyNums.length > 0) {
            for (int i = 0; i < event.buyNums.length; i++) {
                goodscatrgoryEntities.get(i).setBugNum(event.buyNums[i]);
            }
            mGoodsCategoryListAdapter.changeData(goodscatrgoryEntities);
        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))//加上判断
            EventBus.getDefault().unregister(this);//解除订阅
    }

    /**
     *
     * 清空数据
     * @param mess
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAcceptMessage(AcceptMessage mess) {

        personAdapter.cleardata();
        for (int i = 0; i < goodscatrgoryEntities.size(); i++) {
            goodscatrgoryEntities.get(i).setBugNum(0);
        }
        mGoodsCategoryListAdapter.changeData(goodscatrgoryEntities);
    }


    /**
     * popwindow的初始化
     */
    public void setPopWindow(){
        mPopupWindow = new PopupWindow(moptions, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mPopupWindow.showAtLocation(parentview, Gravity.CENTER,0,0);
    }

    //--------------------扫码返回的结果------------------------
}
