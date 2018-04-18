package com.zerone.store.shopingtime.Adapter.shopplistadapter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.zerone.store.shopingtime.Bean.ShopBean;
import com.zerone.store.shopingtime.Bean.shoplistbean.GoodsBeanUp;
import com.zerone.store.shopingtime.Bean.shoplistbean.GoodsCategroyListBean;
import com.zerone.store.shopingtime.Contants.IpConfig;
import com.zerone.store.shopingtime.R;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    //从新封装数据
    private List<GoodsBeanUp> list;
    private List<ShopBean> dataList;
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
    private int isgod = 0;
    private List<GoodsCategroyListBean.DataBean.CategorylistBean> goodscatrgoryEntities;
    //    private String[] mSectionLetters;
    private List<ShopBean> selectGoods = new ArrayList<>();
    private Handler handler;
//    private OnShopCartGoodsChangeListener mOnGoodsNunChangeListener = null;

    /**
     * @param context
     * @param items                 商品详情的集合
     * @param goodscatrgoryEntities 分类集合
     */
    public PersonAdapter(Context context, List<ShopBean> items
            , List<GoodsCategroyListBean.DataBean.CategorylistBean> goodscatrgoryEntities, Handler handler) {
        this.mContext = context;
        this.dataList = items;
        this.goodscatrgoryEntities = goodscatrgoryEntities;
        this.handler = handler;
        initGoodsNum();
        mSectionIndices = getSectionIndices();
//        mSectionLetters = getSectionLetters();
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
//            vh.tvGoodsSelectNum.setVisibility(View.GONE);
//            vh.ivGoodsMinus.setVisibility(View.GONE);
        } else {
//            vh.tvGoodsSelectNum.setVisibility(View.VISIBLE);
//            vh.tvGoodsSelectNum.setText(i + "");
//            vh.ivGoodsMinus.setVisibility(View.VISIBLE);
        }
    }

//    /**
//     * 存放每个组里的添加购物车的数量
//     *
//     * @return
//     */
//    public int[] getBuyNums() {
//        int[] letters = new int[goodscatrgoryEntities.size()];
//        for (int i = 0; i < goodscatrgoryEntities.size(); i++) {
////            letters[i] = goodscatrgoryEntities.get(i).getBugNum();
//            letters[i] =10;
//        }
//        return letters;
//    }

    /**
     * 存放每个分组的第一条的ID
     *
     * @return
     */
    private int[] getSectionIndices() {
        ArrayList<Integer> sectionIndices = new ArrayList<Integer>();
        int lastFirstPoi = -1;
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).getId() != lastFirstPoi) {
                lastFirstPoi = dataList.get(i).getId();
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
//        for (int i = 0; i < mSectionIndices.length; i++) {
//            Log.i("URL","分组数据:"+i);
//            letters[i] = goodscatrgoryEntities.get(i).getName();
//        }
        return letters;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shoplistitem, viewGroup, false);
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
//        mSectionLetters = new String[0];
        notifyDataSetChanged();
    }

    /**
     * 重置购物车
     */
    public void restore() {
        mSectionIndices = getSectionIndices();
//        mSectionLetters = getSectionLetters();
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //设置名
        holder.shop_name.setText(dataList.get(position).getName());
        //设置说明
        holder.shop_shopdiscount.setText(dataList.get(position).getName());
        //设置价格
        holder.shopprice.setText(dataList.get(position).getPrice());
        //商品数量
        //decrease_shop 减少商品
        if ("0".equals(dataList.get(position).getShop_Count())) {
            holder.decrease_shop.setVisibility(View.INVISIBLE);
            holder.shopCount.setVisibility(View.INVISIBLE);
            holder.showTextCount.setVisibility(View.INVISIBLE);
        } else {
            holder.decrease_shop.setVisibility(View.VISIBLE);
            holder.shopCount.setVisibility(View.VISIBLE);
            holder.showTextCount.setVisibility(View.VISIBLE);
            holder.shopCount.setText(dataList.get(position).getShop_Count());
        }
        //设置商品图片
        String s = IpConfig.URL_GETPICTURE + dataList.get(position).getThumb().get(0).getThumb();

        Log.i("URL", "picture::=" + s);

        Glide.with(mContext).load(IpConfig.URL_GETPICTURE + dataList.get(position).getThumb().get(0).getThumb()).centerCrop().placeholder(R.mipmap.app_logo).crossFade().into(holder.shop_picture);
        //通过判别对应位置的数量是否大于0来显示隐藏数量
        isSelected(goodsNum[position], holder);
        //加号按钮点击
        holder.add_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = new Message();
                message.what = 1000;
                message.obj = position;
                handler.sendMessage(message);
            }
        });
        //减号点击按钮点击
        holder.decrease_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = new Message();
                message.what = 1110;
                message.obj = position;
                handler.sendMessage(message);
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

//    public void setOnShopCartGoodsChangeListener(OnShopCartGoodsChangeListener e) {
//        mOnGoodsNunChangeListener = e;
//    }
//
//    public interface OnShopCartGoodsChangeListener {
//        public void onNumChange();
//    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView shop_picture;
        public final TextView shop_name;
        public final TextView shop_shopdiscount;
        public final TextView shopprice;
        public final TextView decrease_shop;
        public final TextView shopCount;
        public final TextView add_shop;
        public final LinearLayout showTextCount;
        public final View root;

        public ViewHolder(View root) {
            super(root);
            shop_picture = (ImageView) root.findViewById(R.id.picture);
            shop_name = (TextView) root.findViewById(R.id.shopname);
            shop_shopdiscount = (TextView) root.findViewById(R.id.shopdiscount);
            shopprice = (TextView) root.findViewById(R.id.shopprice);
            decrease_shop = (TextView) root.findViewById(R.id.decrease_shop);
            shopCount = (TextView) root.findViewById(R.id.shopCount);
            add_shop = (TextView) root.findViewById(R.id.add_shop);
            showTextCount = (LinearLayout) root.findViewById(R.id.showTextCount);
            this.root = root;
        }
    }
}
