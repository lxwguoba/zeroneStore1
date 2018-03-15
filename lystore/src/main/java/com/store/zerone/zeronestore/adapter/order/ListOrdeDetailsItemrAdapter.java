package com.store.zerone.zeronestore.adapter.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.store.zerone.zeronestore.R;
import com.store.zerone.zeronestore.domain.OrderInfo;

import java.util.List;

/**
 * Created by on 2017/12/29 0029 15 44.
 * Author  LiuXingWen
 *
 */

public class ListOrdeDetailsItemrAdapter extends BaseAdapter {
        private Context context ;
    private List<OrderInfo.DataBean.ItemBean.GoodsBean> list;
        private LayoutInflater inflate;

    public ListOrdeDetailsItemrAdapter(Context context, List<OrderInfo.DataBean.ItemBean.GoodsBean> list) {
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
            convertView = inflate.inflate(R.layout.activity_order_df_details_list_item,null);
            holder.goodsname=(TextView) convertView.findViewById(R.id.goodsname);
            holder.goodscount=(TextView) convertView.findViewById(R.id.goodscount);
            holder.goodsprice=(TextView) convertView.findViewById(R.id.goodsprice);
            convertView.setTag(holder);
        } else {
            //直接通过holder获取下面三个子控件，不必使用findviewbyid，加快了 UI 的响应速度
            holder = (ViewHolder) convertView.getTag();
        }

        String title = list.get(position).getTitle();

        if (title.length()>8){
            holder.goodsname.setText(title.substring(0,8)+"...");
        }else {
            holder.goodsname.setText(title);
        }
        holder.goodscount.setText("x"+list.get(position).getTotal());
        //这个是成交价格
        holder.goodsprice.setText("￥"+list.get(position).getPrice());
        return  convertView;
    }

    static class ViewHolder {
        TextView  goodsname;
        TextView  goodscount;
        TextView  goodsprice;

    }

}
