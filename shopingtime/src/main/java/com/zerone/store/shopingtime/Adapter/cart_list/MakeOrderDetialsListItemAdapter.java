package com.zerone.store.shopingtime.Adapter.cart_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zerone.store.shopingtime.Bean.shoplistbean.ShopMessageBean;
import com.zerone.store.shopingtime.R;

import java.util.List;

/**
 * Created by on 2017/12/29 0029 15 44.
 * Author  LiuXingWen
 */

public class MakeOrderDetialsListItemAdapter extends BaseAdapter {
    private Context context;
    private List<ShopMessageBean> list;
    private LayoutInflater inflate;

    public MakeOrderDetialsListItemAdapter(Context context, List<ShopMessageBean> list) {
        this.context = context;
        this.list = list;
        this.inflate = LayoutInflater.from(context);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflate.inflate(R.layout.orderdetailslistitem, null);
            holder.goodsCount = (TextView) convertView.findViewById(R.id.goodscount);
            holder.goodsName = (TextView) convertView.findViewById(R.id.goodsname);
            holder.goodsMoney = (TextView) convertView.findViewById(R.id.goodsmoney);
            convertView.setTag(holder);
        } else {
            //直接通过holder获取下面三个子控件，不必使用findviewbyid，加快了 UI 的响应速度
            holder = (ViewHolder) convertView.getTag();
        }
        holder.goodsCount.setText("x" + list.get(position).getSp_count());
        holder.goodsName.setText(list.get(position).getSp_name());
        holder.goodsMoney.setText("￥" + list.get(position).getSp_price());

        return convertView;
    }

    static class ViewHolder {

        TextView goodsCount;
        TextView goodsName;
        TextView goodsMoney;


    }
}
