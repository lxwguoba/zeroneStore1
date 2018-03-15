package com.store.zerone.zeronestore.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.store.zerone.zeronestore.R;
import com.store.zerone.zeronestore.domain.GoodsBeanUp;
import com.store.zerone.zeronestore.domain.GoodsListBean;
import com.store.zerone.zeronestore.event.GoodsListEvent;
import com.store.zerone.zeronestore.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    //从新封装数据
    private List<GoodsBeanUp> list;
    private List<GoodsListBean.DataEntity.GoodscatrgoryEntity.GoodsitemEntity> dataList;
    private Context mContext;
    private int[] goodsNum;
    private int buyNum;
    private double totalPrice;
    private int[] mSectionIndices;
    private int[] mGoodsCategoryBuyNums;
    private Activity mActivity;
    private TextView shopCart;
    private ImageView buyImg;
    //isgod是为了让有规格的菜品点击后只能选中后 才能点击  这样防止多次启动popwindow
    private  int  isgod=0;
    private List<GoodsListBean.DataEntity.GoodscatrgoryEntity> goodscatrgoryEntities;
    private String[] mSectionLetters;
    private List<GoodsListBean.DataEntity.GoodscatrgoryEntity.GoodsitemEntity> selectGoods = new ArrayList<>();

    private Handler handler;

    /**
     * @param context
     * @param items                 商品详情的集合
     * @param goodscatrgoryEntities 分类集合
     */
    public PersonAdapter(Context context, List<GoodsListBean.DataEntity.GoodscatrgoryEntity.GoodsitemEntity> items
            , List<GoodsListBean.DataEntity.GoodscatrgoryEntity> goodscatrgoryEntities, Handler handler) {
        this.mContext = context;
        this.dataList = items;
        this.goodscatrgoryEntities = goodscatrgoryEntities;
        this.handler = handler;
        initGoodsNum();
        mSectionIndices = getSectionIndices();
        mSectionLetters = getSectionLetters();
        mGoodsCategoryBuyNums = getBuyNums();
        list = new ArrayList<>();
        setHasStableIds(true);
    }

    public void setShopCart(TextView shopCart) {
        this.shopCart = shopCart;
    }

    public void setmActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

    /**
     * 初始化各个商品的购买数量
     */
    private void initGoodsNum() {
        int leng = dataList.size();
        goodsNum = new int[leng];
        for (int i = 0; i < leng; i++) {
            goodsNum[i] = 0;
        }
    }

    /**
     * 开始动画
     *
     * @param view
     */
    private void startAnim(View view) {
        buyImg = new ImageView(mActivity);
        buyImg.setBackgroundResource(R.mipmap.icon_goods_add_item);// 设置buyImg的图片
        int[] loc = new int[2];
        view.getLocationInWindow(loc);
        int[] startLocation = new int[2];// 一个整型数组，用来存储按钮的在屏幕的X、Y坐标
        view.getLocationInWindow(startLocation);// 这是获取购买按钮的在屏幕的X、Y坐标（这也是动画开始的坐标）

    }

    /**
     * 判断商品是否有添加到购物车中
     *
     * @param i  条目下标
     * @param vh ViewHolder
     */
    private void isSelected(int i, ViewHolder vh) {
        if (i == 0) {
            vh.tvGoodsSelectNum.setVisibility(View.GONE);
            vh.ivGoodsMinus.setVisibility(View.GONE);
        } else {
            vh.tvGoodsSelectNum.setVisibility(View.VISIBLE);
            vh.tvGoodsSelectNum.setText(i + "");
            vh.ivGoodsMinus.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 存放每个组里的添加购物车的数量
     *
     * @return
     */
    public int[] getBuyNums() {
        int[] letters = new int[goodscatrgoryEntities.size()];
        for (int i = 0; i < goodscatrgoryEntities.size(); i++) {
            letters[i] = goodscatrgoryEntities.get(i).getBugNum();
        }
        return letters;
    }

    /**
     * 存放每个分组的第一条的ID
     *
     * @return
     */
    private int[] getSectionIndices() {
        ArrayList<Integer> sectionIndices = new ArrayList<Integer>();
        int lastFirstPoi = -1;
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).getGb_id() != lastFirstPoi) {
                lastFirstPoi = dataList.get(i).getGb_id();
                sectionIndices.add(i);
            }
        }
        int[] sections = new int[sectionIndices.size()];
        for (int i = 0; i < sectionIndices.size(); i++) {
            sections[i] = sectionIndices.get(i);
        }
        return sections;
    }

    /**
     * 填充每一个分组要展现的数据
     *
     * @return
     */
    private String[] getSectionLetters() {
        String[] letters = new String[mSectionIndices.length];
        for (int i = 0; i < mSectionIndices.length; i++) {
            letters[i] = goodscatrgoryEntities.get(i).getName();
        }
        return letters;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_goods_list, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public long getItemId(int position) {
        return dataList.get(position).hashCode();
    }

    /**
     * 清楚数据，有待发现
     */
    public void clear() {
        mSectionIndices = new int[0];
        mSectionLetters = new String[0];
        notifyDataSetChanged();
    }

    /**
     * 重置购物车
     */
    public void restore() {
        mSectionIndices = getSectionIndices();
        mSectionLetters = getSectionLetters();
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //设置名
        holder.goodsCategoryName.setText(dataList.get(position).getTitle());
        //设置说明
        holder.tvGoodsDescription.setText(dataList.get(position).getTitle());
        //设置价格
        holder.tvGoodsPrice.setText("¥" + dataList.get(position).getMarketprice());
        //设置商品图片
        Glide.with(mContext).load(dataList.get(position).getThumb()).centerCrop().placeholder(R.mipmap.icon_logo_image_default).crossFade().into(holder.ivGoodsImage);
        //通过判别对应位置的数量是否大于0来显示隐藏数量
        isSelected(goodsNum[position], holder);
        //加号按钮点击
        holder.ivGoodsAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ivGoodsAdd.setEnabled(true);
                if ("1".equals(dataList.get(position).getHasoption())) {
                    //发送消息 打开规格页面 并把位置传过主页面
                    Message mes = new Message();
                    mes.what = 10000;
                    mes.obj = position;
                    if (isgod==0){
                        handler.sendMessage(mes);
                    }
                    isgod=1;
                } else {
                    goodsNum[position]++;
                    selectGoods.add(dataList.get(position));
                    if (list.size() == 0) {
                        GoodsBeanUp gbu = new GoodsBeanUp();
                        gbu.setTotal(dataList.get(position).getTotal());
                        gbu.setG_count(1);
                        gbu.setGb_id(dataList.get(position).getGb_id());
                        gbu.setHasoption(dataList.get(position).getHasoption());
                        gbu.setId(dataList.get(position).getId());
                        gbu.setMarketprice(dataList.get(position).getMarketprice());
                        gbu.setOptions(null);
                        gbu.setThumb(dataList.get(position).getThumb());
                        gbu.setTitle(dataList.get(position).getTitle());
                        gbu.setTotal_in_cart(dataList.get(position).getTotal_in_cart());
                        list.add(gbu);
                    } else {
                        boolean isgoodslike = false;
                        int index = 0;
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getId().equals(dataList.get(position).getId())) {
                                isgoodslike = true;
                                index = i;
                                break;
                            }
                        }
                        if (isgoodslike) {
                            //相同
                            int count = list.get(index).getG_count();
                            count++;
                            GoodsBeanUp gbu = new GoodsBeanUp();
                            gbu.setG_count(count);
                            gbu.setTitle(dataList.get(position).getTitle());
                            gbu.setGb_id(dataList.get(position).getGb_id());
                            gbu.setYuanjia(dataList.get(position).getYuanjia());
                            gbu.setId(dataList.get(position).getId());
                            gbu.setHasoption(dataList.get(position).getHasoption());
                            gbu.setMarketprice(dataList.get(position).getMarketprice());
                            gbu.setOptions(null);
                            gbu.setThumb(dataList.get(position).getThumb());
                            gbu.setTotal(dataList.get(position).getTotal());
                            gbu.setTotal_in_cart(dataList.get(position).getTotal_in_cart());
                            list.set(index, gbu);
                        } else {
                            //不相同
                            GoodsBeanUp gbu = new GoodsBeanUp();
                            gbu.setTotal(dataList.get(position).getTotal());
                            gbu.setG_count(1);
                            gbu.setGb_id(dataList.get(position).getGb_id());
                            gbu.setHasoption(dataList.get(position).getHasoption());
                            gbu.setId(dataList.get(position).getId());
                            gbu.setMarketprice(dataList.get(position).getMarketprice());
                            gbu.setOptions(null);
                            gbu.setThumb(dataList.get(position).getThumb());
                            gbu.setTitle(dataList.get(position).getTitle());
                            gbu.setTotal_in_cart(dataList.get(position).getTotal_in_cart());
                            list.add(gbu);
                        }
                    }
                    mGoodsCategoryBuyNums[dataList.get(position).getGb_id()]++;
                    buyNum++;
                    totalPrice += Double.parseDouble(dataList.get(position).getMarketprice());
                    if (goodsNum[position] <= 1) {
                        holder.ivGoodsMinus.setAnimation(getShowAnimation());
                        holder.tvGoodsSelectNum.setAnimation(getShowAnimation());
                        holder.ivGoodsMinus.setVisibility(View.VISIBLE);
                        holder.tvGoodsSelectNum.setVisibility(View.VISIBLE);
                    }
                    startAnim(holder.ivGoodsAdd);
                    changeShopCart();
                    if (mOnGoodsNunChangeListener != null)
                        mOnGoodsNunChangeListener.onNumChange();
                    isSelected(goodsNum[position], holder);
                }
            }
        });
        //减号点击按钮点击
        holder.ivGoodsMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (goodsNum[position] > 0) {
//                    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                    String hasoption = dataList.get(position).getHasoption();
                    if ("1".equals(hasoption)) {
                        boolean isgoodslike = false;
                        int index = 0;
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getId().equals(dataList.get(position).getId())) {
                                index = i;
                                break;
                            }
                        }
                        int count = list.get(index).getG_count();
                        count--;
                        GoodsBeanUp gbu = new GoodsBeanUp();
                        gbu.setTotal_in_cart(list.get(index).getTotal_in_cart());
                        gbu.setTotal(list.get(index).getTotal());
                        gbu.setTitle(list.get(index).getTitle());
                        gbu.setThumb(list.get(index).getThumb());
                        gbu.setG_count(count);
                        gbu.setGb_id(list.get(index).getGb_id());
                        gbu.setHasoption(list.get(index).getHasoption());
                        gbu.setId(list.get(index).getId());
                        gbu.setMarketprice(list.get(index).getMarketprice());
                        gbu.setOptions(list.get(index).getOptions());
                        gbu.setYuanjia(list.get(index).getYuanjia());
                        if (count == 0) {
                            list.remove(index);
                        } else {
                            list.set(index, gbu);
                        }
                    } else {
                        boolean isgoodslike = false;
                        int index = 0;
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getId().equals(dataList.get(position).getId())) {
                                isgoodslike = true;
                                index = i;
                                break;
                            }
                        }
                        int count = list.get(index).getG_count();
                        count--;
                        GoodsBeanUp gbu = new GoodsBeanUp();
                        gbu.setTotal_in_cart(list.get(index).getTotal_in_cart());
                        gbu.setTotal(list.get(index).getTotal());
                        gbu.setTitle(list.get(index).getTitle());
                        gbu.setThumb(list.get(index).getThumb());
                        gbu.setG_count(count);
                        gbu.setGb_id(list.get(index).getGb_id());
                        gbu.setHasoption(list.get(index).getHasoption());
                        gbu.setId(list.get(index).getId());
                        gbu.setMarketprice(list.get(index).getMarketprice());
                        gbu.setOptions(list.get(index).getOptions());
                        gbu.setYuanjia(list.get(index).getYuanjia());
                        if (count == 0) {
                            list.remove(index);
                        } else {
                            list.set(index, gbu);
                        }
                    }
                    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                    goodsNum[position]--;
                    selectGoods.remove(dataList.get(position));
                    mGoodsCategoryBuyNums[dataList.get(position).getGb_id()]--;
                    isSelected(goodsNum[position], holder);
                    buyNum--;
                    totalPrice -= Double.parseDouble(dataList.get(position).getMarketprice());
                    if (goodsNum[position] <= 0) {
                        holder.ivGoodsMinus.setAnimation(getHiddenAnimation());
                        holder.tvGoodsSelectNum.setAnimation(getHiddenAnimation());
                        holder.ivGoodsMinus.setVisibility(View.GONE);
                        holder.tvGoodsSelectNum.setVisibility(View.GONE);
                    }
                    changeShopCart();
                    if (mOnGoodsNunChangeListener != null)
                        mOnGoodsNunChangeListener.onNumChange();
                } else {

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    /**
     * 显示减号的动画
     *
     * @return
     */
    private Animation getShowAnimation() {
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0, 720, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 2f
                , TranslateAnimation.RELATIVE_TO_SELF, 0
                , TranslateAnimation.RELATIVE_TO_SELF, 0
                , TranslateAnimation.RELATIVE_TO_SELF, 0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }


    /**
     * 隐藏减号的动画
     *
     * @return
     */
    private Animation getHiddenAnimation() {
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0, 720, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0
                , TranslateAnimation.RELATIVE_TO_SELF, 4f
                , TranslateAnimation.RELATIVE_TO_SELF, 0
                , TranslateAnimation.RELATIVE_TO_SELF, 0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(1, 0);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }

    public void cleardata() {
        initGoodsNum();
        list.clear();
        selectGoods.clear();
        buyNum = 0;
        totalPrice = 0;
        for (int i = 0; i < mGoodsCategoryBuyNums.length; i++) {
            mGoodsCategoryBuyNums[i] = 0;
        }
        notifyDataSetChanged();
    }

    /**
     * 修改购物车状态
     */
    private void changeShopCart() {
        DecimalFormat df = new DecimalFormat("#.00");
        double totalmoney = Double.parseDouble(df.format(totalPrice));
        EventBus.getDefault().post(new MessageEvent(buyNum, totalmoney, list, selectGoods));
        EventBus.getDefault().post(new GoodsListEvent(mGoodsCategoryBuyNums));
        if (shopCart == null) return;
        if (buyNum > 0) {
            shopCart.setVisibility(View.VISIBLE);
            shopCart.setText(buyNum + "");
        } else {
            shopCart.setVisibility(View.GONE);
        }

    }

    private OnShopCartGoodsChangeListener mOnGoodsNunChangeListener = null;

    public void setOnShopCartGoodsChangeListener(OnShopCartGoodsChangeListener e) {
        mOnGoodsNunChangeListener = e;
    }

    public interface OnShopCartGoodsChangeListener {
        public void onNumChange();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView ivGoodsImage;
        public final TextView goodsCategoryName;
        public final TextView tvGoodsDescription;
        public final LinearLayout goodsInfo;
        public final TextView tvGoodsPrice;
        public final TextView tvGoodsIntegral;
        public final ImageView ivGoodsMinus;
        public final TextView tvGoodsSelectNum;
        public final ImageView ivGoodsAdd;
        public final View root;

        public ViewHolder(View root) {
            super(root);
            ivGoodsImage = (ImageView) root.findViewById(R.id.ivGoodsImage);
            goodsCategoryName = (TextView) root.findViewById(R.id.goodsCategoryName);
            tvGoodsDescription = (TextView) root.findViewById(R.id.tvGoodsDescription);
            goodsInfo = (LinearLayout) root.findViewById(R.id.goodsInfo);
            tvGoodsPrice = (TextView) root.findViewById(R.id.tvGoodsPrice);
            tvGoodsIntegral = (TextView) root.findViewById(R.id.tvGoodsIntegral);
            ivGoodsMinus = (ImageView) root.findViewById(R.id.ivGoodsMinus);
            tvGoodsSelectNum = (TextView) root.findViewById(R.id.tvGoodsSelectNum);
            ivGoodsAdd = (ImageView) root.findViewById(R.id.ivGoodsAdd);
            this.root = root;
        }
    }


    public void setListOptions(JSONObject obj, int position) throws JSONException {
        if (list.size() > 0) {
            //说明不止一个商品
//                 1、判断是否是通一个商品是数量加一不是添加商品
//                 2、判断商品是否有规格  因为这里是有规格的 如果所有的商品的没有规格说明是第一个有规格的商品  若是有则
//                      比较两个商品的规格id是否相同若是相同数量加一  若是不同 则添加新商品
            // 判断是否是同一个商品
            boolean goodslike = false;
            // 判断是否有规格
            boolean hasoplean = false;
            //用来保存同一个商品的下标
            int flikeindex = 0;
            for (int i = 0; i < list.size(); i++) {
                //这个是有规格的商品
                if ("1".equals(list.get(i).getHasoption())) {
                    try {
                        if (list.get(i).getOptions().getOptionid() == obj.getInt("id")) {
                            goodslike = true;
                            flikeindex = i;
                            break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (goodslike) {
                //是否是同一个商品 获取商品的个数
                int g_count = list.get(flikeindex).getG_count();
                g_count++;
                GoodsBeanUp gbu = new GoodsBeanUp();
                gbu.setTotal(list.get(flikeindex).getTotal());
                gbu.setG_count(g_count);
                gbu.setGb_id(list.get(flikeindex).getGb_id());
                gbu.setHasoption(list.get(flikeindex).getHasoption());
                gbu.setId(list.get(flikeindex).getId());
                gbu.setMarketprice(obj.getString("marketprice"));
                gbu.setOptions(list.get(flikeindex).getOptions());
                gbu.setThumb(list.get(flikeindex).getThumb());
                gbu.setTitle(list.get(flikeindex).getTitle());
                gbu.setTotal_in_cart(list.get(flikeindex).getTotal_in_cart());
                list.set(flikeindex, gbu);
            } else {
                //不是同一个商品
                GoodsBeanUp.OptionsBean ob = new GoodsBeanUp.OptionsBean();
                try {
                    ob.setOptionid(obj.getInt("id"));
                    ob.setOptionname(obj.getString("title"));
                    ob.setGoodsId(obj.getString("goodsid"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                GoodsBeanUp gbu = new GoodsBeanUp();
                gbu.setTotal(dataList.get(position).getTotal());
                gbu.setG_count(1);
                gbu.setGb_id(dataList.get(position).getGb_id());
                gbu.setHasoption(dataList.get(position).getHasoption());
                gbu.setId(dataList.get(position).getId());
                gbu.setMarketprice(obj.getString("marketprice"));
                gbu.setOptions(ob);
                gbu.setThumb(dataList.get(position).getThumb());
                gbu.setTitle(dataList.get(position).getTitle());
                gbu.setTotal_in_cart(dataList.get(position).getTotal_in_cart());
                list.add(gbu);
            }
        } else {
            //这个是第一个商品 list集合里没有规格
            //不是同一个商品
            GoodsBeanUp.OptionsBean ob = new GoodsBeanUp.OptionsBean();
            try {
                ob.setOptionid(obj.getInt("id"));
                ob.setOptionname(obj.getString("title"));
                ob.setGoodsId(obj.getString("goodsid"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            GoodsBeanUp gbu = new GoodsBeanUp();
            gbu.setTotal(dataList.get(position).getTotal());
            gbu.setG_count(1);
            gbu.setGb_id(dataList.get(position).getGb_id());
            gbu.setHasoption(dataList.get(position).getHasoption());
            gbu.setId(dataList.get(position).getId());
            gbu.setMarketprice(obj.getString("marketprice"));
            gbu.setOptions(ob);
            gbu.setThumb(dataList.get(position).getThumb());
            gbu.setTitle(dataList.get(position).getTitle());
            gbu.setTotal_in_cart(dataList.get(position).getTotal_in_cart());
            list.add(gbu);
        }
        goodsNum[position]++;
        mGoodsCategoryBuyNums[dataList.get(position).getGb_id()]++;
        buyNum++;
        totalPrice += Double.parseDouble(obj.getString("marketprice"));
        DecimalFormat df = new DecimalFormat("#.00");
        double totalmoney = Double.parseDouble(df.format(totalPrice));
        EventBus.getDefault().post(new MessageEvent(buyNum, totalmoney, list, selectGoods));
        EventBus.getDefault().post(new GoodsListEvent(mGoodsCategoryBuyNums));
        isgod=0;
        notifyDataSetChanged();
    }
}
