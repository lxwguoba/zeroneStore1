package com.store.zerone.zeronestore.adapter.tableadapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.store.zerone.zeronestore.R;
import com.store.zerone.zeronestore.domain.TableCatringBean;

import java.util.List;

/**
 * Created by on 2017/12/29 0029 15 44.
 * Author  LiuXingWen
 *
 */

public class TableCatListViewAdapter extends BaseAdapter {
        private Context context ;
        private List<TableCatringBean.DataBean> list;
        private LayoutInflater inflate;
        private int pos;

    public TableCatListViewAdapter(Context context, List<TableCatringBean.DataBean> list) {
        this.context = context;
        this.list = list;
        this.inflate=LayoutInflater.from(context);
    }

    /**
     * 设置选中的位置
     * @param pos
     */
    public void setPosition(int pos){
         this.pos=pos;
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
            convertView = inflate.inflate(R.layout.activity_listcat_item,null);
            holder.catname= (TextView) convertView.findViewById(R.id.tablecatname);
            convertView.setTag(holder);
        } else {
            //直接通过holder获取下面三个子控件，不必使用findviewbyid，加快了 UI 的响应速度
            holder = (ViewHolder) convertView.getTag();
        }
         if (pos==position){
             holder.catname.setBackgroundResource(R.mipmap.icon_goodscate_bg);
             holder.catname.setTextColor(Color.parseColor("#ffffff"));
         }else {
             holder.catname.setBackgroundColor(Color.WHITE);
             holder.catname.setTextColor(Color.parseColor("#838383"));
         }

        holder.catname.setText(list.get(position).getRoomname());
        return  convertView;
    }

    static class ViewHolder {
        TextView  catname;
    }
}
