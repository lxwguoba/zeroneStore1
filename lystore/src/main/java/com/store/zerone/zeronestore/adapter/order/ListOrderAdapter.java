package com.store.zerone.zeronestore.adapter.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.store.zerone.zeronestore.R;

import java.util.List;

/**
 * Created by on 2017/12/29 0029 15 44.
 * Author  LiuXingWen
 *
 */

public class ListOrderAdapter extends BaseAdapter {
        private Context context ;
        private List<String> list;
        private LayoutInflater inflate;

    public ListOrderAdapter(Context context, List<String> list) {
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
            convertView = inflate.inflate(R.layout.order_list_fragment,null);
            holder.ordercat_name=(TextView)convertView.findViewById(R.id.ordercat_name);
            convertView.setTag(holder);
        } else {
            //直接通过holder获取下面三个子控件，不必使用findviewbyid，加快了 UI 的响应速度
            holder = (ViewHolder) convertView.getTag();
        }

        holder.ordercat_name.setText(list.get(position));
        return  convertView;
    }

    static class ViewHolder {
        TextView  ordercat_name;
    }

}
