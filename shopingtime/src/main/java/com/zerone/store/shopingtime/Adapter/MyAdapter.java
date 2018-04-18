package com.zerone.store.shopingtime.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zerone.store.shopingtime.Bean.ShopBean;
import com.zerone.store.shopingtime.Contants.IpConfig;
import com.zerone.store.shopingtime.R;

import java.util.List;

/**
 * Created by on 2018/4/9 0009 17 17.
 * Author  LiuXingWen
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ShopBean> mData;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;//声明接口

    public MyAdapter(List<ShopBean> data, Context mContext) {
        this.mData = data;
        this.mContext = mContext;
    }

    public void updateData(List<ShopBean> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_shoplistitem, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //设置名
        holder.shop_name.setText(mData.get(position).getName());
        //设置说明
        holder.shop_shopdiscount.setText(mData.get(position).getName());
        //设置价格
        holder.shopprice.setText(mData.get(position).getPrice());
        //商品数量
        //decrease_shop 减少商品
        if ("0".equals(mData.get(position).getShop_Count())) {
            holder.decrease_shop.setVisibility(View.INVISIBLE);
            holder.shopCount.setVisibility(View.INVISIBLE);
            holder.showTextCount.setVisibility(View.INVISIBLE);
        } else {
            holder.decrease_shop.setVisibility(View.VISIBLE);
            holder.shopCount.setVisibility(View.VISIBLE);
            holder.showTextCount.setVisibility(View.VISIBLE);
            holder.shopCount.setText(mData.get(position).getShop_Count());
        }
        //设置商品图
        String s = "";
        if (mData.get(position).getThumb().size() > 0) {
            s = IpConfig.URL_GETPICTURE + mData.get(position).getThumb().get(0).getThumb();
        }

        Glide.with(mContext).load(s).centerCrop().placeholder(R.mipmap.app_logo).crossFade().into(holder.shop_picture);

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                mOnItemClickListener.onItemClick(holder.root, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

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
