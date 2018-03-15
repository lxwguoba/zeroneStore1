package com.store.zerone.zeronestore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.store.zerone.zeronestore.R;
import com.store.zerone.zeronestore.domain.HuiYuanInfoBean;

import java.util.List;

/**
 * Created by on 2017/12/29 0029 15 44.
 * Author  LiuXingWen
 *
 */

public class ListMemberlistItem extends BaseAdapter {
        private Context context ;
        private List<HuiYuanInfoBean.MemberlistBean> list;
        private LayoutInflater inflate;

    public ListMemberlistItem(Context context, List<HuiYuanInfoBean.MemberlistBean> list) {
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
            convertView = inflate.inflate(R.layout.activity_member_list_item,null);
            holder.user_name=(TextView) convertView.findViewById(R.id.username);
            holder.user_discount=(TextView)convertView.findViewById(R.id.discount);
            holder.user_number=(TextView)convertView.findViewById(R.id.bianhao);
            holder.usermoney=(TextView)convertView.findViewById(R.id.money);
            holder.user_phone=(TextView)convertView.findViewById(R.id.userphone);
            holder.user_jifen=(TextView)convertView.findViewById(R.id.jifen);
            convertView.setTag(holder);
        } else {
            //直接通过holder获取下面三个子控件，不必使用findviewbyid，加快了 UI 的响应速度
            holder = (ViewHolder) convertView.getTag();
        }

        holder.user_name.setText(list.get(position).getRealname());
        holder.user_phone.setText(list.get(position).getMobile());
        holder.user_number.setText(list.get(position).getId());
        holder.user_jifen.setText(list.get(position).getCredit1());
        holder.usermoney.setText(list.get(position).getCredit2());
        String title = list.get(position).getTitle();
        if (title.length()>0){
            holder.user_discount.setText(title);
        }else {
            holder.user_discount.setText("普通");
        }

        return  convertView;
    }
    static class ViewHolder {
        TextView    user_number;
        TextView   user_name;
        TextView   user_discount;
        TextView  usermoney;
        TextView   user_phone;
        TextView   user_jifen;
    }

}
