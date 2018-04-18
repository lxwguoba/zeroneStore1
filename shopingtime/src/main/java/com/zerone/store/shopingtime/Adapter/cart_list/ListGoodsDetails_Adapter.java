package com.zerone.store.shopingtime.Adapter.cart_list;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zerone.store.shopingtime.Bean.shoplistbean.ShopMessageBean;
import com.zerone.store.shopingtime.R;

import java.util.List;

/**
 * Created by on 2017/12/29 0029 15 44.
 * Author  LiuXingWen
 */

public class ListGoodsDetails_Adapter extends BaseAdapter {
    private Context mContext;
    private List<ShopMessageBean> list;
    private LayoutInflater inflate;
    private Handler handler;
    private OnClickAddGoods mOnClickAddGoods;//声明接口
    private OnClickLessenGoods mOnClickLessenGoods;//声明接口

    public ListGoodsDetails_Adapter(Context context, List<ShopMessageBean> list, Handler handler) {
        this.mContext = context;
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
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflate.inflate(R.layout.goodslistitem_, null);
//            holder.checkshop = (CheckBox) convertView.findViewById(R.id.checkshop);
            holder.shop_img = (ImageView) convertView.findViewById(R.id.picture);
            holder.shop_name = (TextView) convertView.findViewById(R.id.shopname);
            holder.shopdiscount = (TextView) convertView.findViewById(R.id.shopdiscount);
            holder.shop_price = (TextView) convertView.findViewById(R.id.shopprice);
            holder.shop_count = (TextView) convertView.findViewById(R.id.shopCount);
            holder.decrease_shop = (TextView) convertView.findViewById(R.id.decrease_shop);
            holder.add_shop = (TextView) convertView.findViewById(R.id.add_shop);
            convertView.setTag(holder);
        } else {
            //直接通过holder获取下面三个子控件，不必使用findviewbyid，加快了 UI 的响应速度
            holder = (ViewHolder) convertView.getTag();
        }
//        holder.checkshop.setChecked(list.get(position).isSp_check());
        holder.shop_name.setText(list.get(position).getSp_name());
        holder.shopdiscount.setText(list.get(position).getSp_discount());
        holder.shop_price.setText(list.get(position).getSp_price());
        holder.shop_count.setText(list.get(position).getSp_count());
        Glide.with(mContext).load(list.get(position).getSp_picture_url()).centerCrop().placeholder(R.mipmap.app_logo).crossFade().into(holder.shop_img);

        /**
         *
         * 减少按钮的点击
         *
         */
        holder.decrease_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = new Message();
                message.what = 201;
                message.obj = position;
                handler.sendMessage(message);
            }
        });

        /**
         *
         * 添加按钮
         *
         */
        holder.add_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Message message = new Message();
                message.what = 200;
                message.obj = position;
                handler.sendMessage(message);
            }
        });

//        holder.checkshop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Message message = new Message();
//                message.what = 202;
//                message.obj = position;
//                handler.sendMessage(message);
//            }
//        });

        return convertView;
    }

    /**
     * 对外开放减少商品的接口
     *
     * @param OnClickAddGoods
     */
    public void setOnClickAddGoods(OnClickAddGoods OnClickAddGoods) {
        OnClickAddGoods = OnClickAddGoods;
    }

    /**
     * 对外开放减少商品的接口
     *
     * @param mOnClickLessenGoods
     */
    public void setOnClickLessenGoods(OnClickLessenGoods mOnClickLessenGoods) {
        mOnClickLessenGoods = mOnClickLessenGoods;
    }


    /**
     * 添加的按钮
     */
    public interface OnClickAddGoods {
        void OnClickAddGoods(View view, int position);
    }

    /**
     * 减少的按钮
     */
    public interface OnClickLessenGoods {
        void OnClickAddGoods(View view, int position);
    }

    static class ViewHolder {
        //        CheckBox checkshop;
        TextView shop_name;
        ImageView shop_img;
        TextView shop_count;
        TextView shopdiscount;
        TextView shop_price;
        TextView decrease_shop;
        TextView add_shop;

    }

}
