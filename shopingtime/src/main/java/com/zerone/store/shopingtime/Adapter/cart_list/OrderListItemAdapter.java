package com.zerone.store.shopingtime.Adapter.cart_list;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zerone.store.shopingtime.Activity.OrderDetailsYFKActivity;
import com.zerone.store.shopingtime.Activity.OrderDetailsYQXActivity;
import com.zerone.store.shopingtime.Bean.order.OrderBean;
import com.zerone.store.shopingtime.R;

import java.util.List;

/**
 * Created by on 2017/12/29 0029 15 44.
 * Author  LiuXingWen
 */

public class OrderListItemAdapter extends BaseAdapter {
    private Context context;
    private List<OrderBean> list;
    private LayoutInflater inflate;
    private Handler handler;

    public OrderListItemAdapter(Context context, List<OrderBean> list, Handler handler) {
        this.context = context;
        this.list = list;
        this.inflate = LayoutInflater.from(context);
        this.handler = handler;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        if (list != null) {
            return position;
        }
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflate.inflate(R.layout.orderlist_item, null);
            holder.shop_img = (ImageView) convertView.findViewById(R.id.logo_order);
            holder.orderNumber = (TextView) convertView.findViewById(R.id.ordernumber);
            holder.orderTime = (TextView) convertView.findViewById(R.id.ordertime);
            holder.orderMoney = (TextView) convertView.findViewById(R.id.ordermoney);
            holder.orderStates = (TextView) convertView.findViewById(R.id.orderstates);
            holder.lookoverorder = (TextView) convertView.findViewById(R.id.lookover);
            convertView.setTag(holder);
        } else {
            //直接通过holder获取下面三个子控件，不必使用findviewbyid，加快了 UI 的响应速度
            holder = (ViewHolder) convertView.getTag();
        }
        holder.orderNumber.setText(list.get(position).getOrdersn());
        holder.orderTime.setText(list.get(position).getCreated_at());
        holder.orderMoney.setText(list.get(position).getOrder_price());
        //这个地方需要修改 根据不同的id去判断是什么订单
        //holder.orderStates.setText();
        if ("0".equals(list.get(position).getStatus())) {
            //待付款
            holder.orderStates.setText("待付款");
            holder.orderStates.setTextColor(Color.parseColor("#f3454c"));
        } else if ("1".equals(list.get(position).getStatus())) {
            //已付款
            holder.orderStates.setText("已付款");
            holder.orderStates.setTextColor(Color.parseColor("#000000"));
        } else if ("-1".equals(list.get(position).getStatus())) {
            //已取消
            holder.orderStates.setText("已取消");
            holder.orderStates.setTextColor(Color.parseColor("#000000"));
        }
        holder.lookoverorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("0".equals(list.get(position).getStatus())) {
                    //待付款
                    Message message = new Message();
                    message.what = 20000;
                    message.obj = position;
                    handler.sendMessage(message);
                } else if ("1".equals(list.get(position).getStatus())) {
                    //已付款
                    Intent intent02 = new Intent(context, OrderDetailsYFKActivity.class);
                    intent02.putExtra("orderid", list.get(position).getId());
                    context.startActivity(intent02);
                } else if ("-1".equals(list.get(position).getStatus())) {
                    //已取消
                    Intent intent03 = new Intent(context, OrderDetailsYQXActivity.class);
                    intent03.putExtra("orderid", list.get(position).getId());
                    context.startActivity(intent03);
                }


            }
        });


//        Glide.with(context).load(list.get(position).getThumb()).centerCrop().placeholder(R.mipmap.ic_launcher).crossFade().into(holder.shop_img);
        return convertView;
    }

    static class ViewHolder {

        TextView orderNumber;
        ImageView shop_img;
        TextView orderTime;
        TextView orderMoney;
        TextView orderStates;
        TextView lookoverorder;

    }
}
