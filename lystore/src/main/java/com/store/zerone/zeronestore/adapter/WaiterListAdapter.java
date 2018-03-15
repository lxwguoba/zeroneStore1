package com.store.zerone.zeronestore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.store.zerone.zeronestore.R;
import com.store.zerone.zeronestore.domain.Worker;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by on 2017/12/29 0029 15 44.
 * Author  LiuXingWen
 *
 */

public class WaiterListAdapter extends BaseAdapter {
    private Context context ;
    private List<Worker> list;
    private LayoutInflater inflate;
    public WaiterListAdapter(Context context, List<Worker> list) {
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
            convertView = inflate.inflate(R.layout.activity_checkwaiter_items,null);
            holder.w_name=(TextView)convertView.findViewById(R.id.w_name);
            holder.w_id=(TextView)convertView.findViewById(R.id.w_id);
            holder.w_Img=(CircleImageView)convertView.findViewById(R.id.waiterImg);
            convertView.setTag(holder);
        } else {
            //直接通过holder获取下面三个子控件，不必使用findviewbyid，加快了 UI 的响应速度
            holder = (ViewHolder) convertView.getTag();
        }
        holder.w_name.setText("接待员："+list.get(position).getName());
        holder.w_id.setText("ID："+list.get(position).getWorkerid());
        Glide.with(context).load(list.get(position).getIcon_thumb()).into(holder.w_Img);
        return  convertView;
    }
    static class ViewHolder {
        TextView  w_name;
        TextView  w_id;
        CircleImageView w_Img;
    }
}
