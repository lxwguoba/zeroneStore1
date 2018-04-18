package com.zerone.store.shopingtime.Adapter.shopplistadapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zerone.store.shopingtime.Bean.shoplistbean.GoodsCategroyListBean;
import com.zerone.store.shopingtime.R;

import java.util.List;


/**
 * Created by dalong on 2017/1/13.
 */

public class RecycleGoodsCategoryListAdapter extends RecyclerView.Adapter<RecycleGoodsCategoryListAdapter.ViewHolder> {

    public Context mContext;
    OnItemClickListener mOnItemClickListener;
    //当前选中的位置
    private int selectPosition;
    private List<GoodsCategroyListBean.DataBean.CategorylistBean> dataList;

    public RecycleGoodsCategoryListAdapter(List<GoodsCategroyListBean.DataBean.CategorylistBean> dataList, Context context) {
        this.dataList = dataList;
        this.mContext = context;
    }

    /**
     * 数据发生改变的方法
     *
     * @param dataList 发生改变的集合类
     */
    public void changeData(List<GoodsCategroyListBean.DataBean.CategorylistBean> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods_category_list,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.goodsCategoryName.setText(dataList.get(position).getName());
        if (selectPosition != -1) {
            if (selectPosition == position) {
                //被选中
                holder.checkcategory.setBackgroundColor(Color.parseColor("#fedb43"));
                holder.goodsCategoryName.setTextColor(Color.parseColor("#000000"));
            } else {
                // 没有被选中808080
                holder.goodsCategoryName.setTextColor(Color.parseColor("#808080"));
                holder.checkcategory.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        } else {
            holder.goodsCategoryName.setTextColor(Color.parseColor("#808080"));
            holder.checkcategory.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        /**
         * 对外开放点击接口
         */
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view, position);
                }
            }
        });

    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    /**
     * 设置选中index
     *
     * @param position 选中分类的下标
     */
    public void setCheckPosition(int position) {
        this.selectPosition = position;
        notifyDataSetChanged();
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView goodsCategoryName;
        //        public final TextView shopCartNum;
        public final View root;
        public final ImageView checkcategory;

        public ViewHolder(View root) {
            super(root);
            goodsCategoryName = (TextView) root.findViewById(R.id.catName);
//            shopCartNum = (TextView) root.findViewById(R.id.shopCartNumTV);
            checkcategory = (ImageView) root.findViewById(R.id.checkcategory);
            this.root = root;
        }
    }
}
