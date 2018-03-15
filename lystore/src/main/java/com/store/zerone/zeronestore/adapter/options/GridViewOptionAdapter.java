package com.store.zerone.zeronestore.adapter.options;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.store.zerone.zeronestore.R;
import com.store.zerone.zeronestore.domain.options.OptionsBeanItem;

import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 *
 */

public class GridViewOptionAdapter extends BaseAdapter {
    private List<OptionsBeanItem> list;
    private Context context;
    private String  option;

    private int clickItemPosition=-1;
    //标识选中的item
    public void setSelectPosition(int position){
        clickItemPosition=position;
    }

    public GridViewOptionAdapter(List<OptionsBeanItem> list, Context context, String  option) {
        this.list = list;
        this.context = context;
        this.option=option;
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
            return    list.get(position);
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView=   LayoutInflater.from(context).inflate(R.layout.activity_optionsitem_gridview,null);
            // 初始化组件
            viewHolder.optionName = (TextView) convertView.findViewById(R.id.optionName);
            // 给converHolder附加一个对象
            convertView.setTag(viewHolder);
        }else {
            // 取得converHolder附加的对象
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.optionName.setText(list.get(position).getTitle());
        if (clickItemPosition== position) {
            viewHolder.optionName.setTextColor(Color.rgb(248,176,42));
            viewHolder.optionName.setBackgroundColor(Color.rgb(255,255,255));
        } else {
            viewHolder.optionName.setTextColor(Color.rgb(91,91,91));
            viewHolder.optionName.setBackgroundColor(Color.rgb(255,255,255));
        }

        return convertView;
    }

    class ViewHolder{

        private TextView optionName;

    }
}
