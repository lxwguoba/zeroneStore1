package com.store.zerone.zeronestore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.store.zerone.zeronestore.R;
import com.store.zerone.zeronestore.domain.GoodsBeanUp;


import java.util.List;

/**
 * Created by on 2017/12/29 0029 15 44.
 * Author  LiuXingWen
 *
 */

public class ListGoodsDetailsAdapter extends BaseAdapter {
        private Context context ;
        private List<GoodsBeanUp> list;
        private LayoutInflater inflate;

    public ListGoodsDetailsAdapter(Context context, List<GoodsBeanUp> list) {
        this.context = context;
        this.list = list;
        this.inflate=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
         if (list!=null){
             return list.size();
         }
        return 0;
    }
    @Override
    public Object getItem(int position) {
        if (list!=null){
            return list.get(position);
        }
        return null;
    }
    @Override
    public long getItemId(int position) {
         if (list!=null){
             return position;
         }
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflate.inflate(R.layout.activity_listgoodsdetails_items,null);
            holder.shop_img=(ImageView) convertView.findViewById(R.id.shopimg);
            holder.shop_name=(TextView)convertView.findViewById(R.id.shopname);
            holder.shop_count=(TextView)convertView.findViewById(R.id.shop_stock);
            holder.shop_money=(TextView)convertView.findViewById(R.id.shop_price);
            holder.goodsoptions=(TextView)convertView.findViewById(R.id.goodsoptions);
            convertView.setTag(holder);
        } else {
            //直接通过holder获取下面三个子控件，不必使用findviewbyid，加快了 UI 的响应速度
            holder = (ViewHolder) convertView.getTag();
        }
        holder.shop_name.setText(list.get(position).getTitle());
        holder.shop_count.setText("x"+list.get(position).getG_count());
        holder.shop_money.setText("￥"+list.get(position).getMarketprice());
        if ("1".equals(list.get(position).getHasoption())){
            holder.goodsoptions.setVisibility(View.VISIBLE);
            holder.goodsoptions.setText(list.get(position).getOptions().getOptionname());
        }
        Glide.with(context).load(list.get(position).getThumb()).centerCrop().placeholder(R.mipmap.ic_launcher).crossFade().into(holder.shop_img);
        return  convertView;
    }
    static class ViewHolder {
        TextView  shop_name;
        ImageView  shop_img;
        TextView   shop_count;
        TextView   shop_money;
        TextView  goodsoptions;
    }

}
