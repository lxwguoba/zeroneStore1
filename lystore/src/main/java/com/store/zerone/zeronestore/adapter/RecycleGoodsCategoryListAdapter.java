package com.store.zerone.zeronestore.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.store.zerone.zeronestore.R;
import com.store.zerone.zeronestore.domain.GoodsListBean;

import java.util.List;


/**
 * Created by dalong on 2017/1/13.
 */

public class RecycleGoodsCategoryListAdapter extends RecyclerView.Adapter<RecycleGoodsCategoryListAdapter.ViewHolder> {

    //当前选中的位置
    private int selectPosition;
    private List<GoodsListBean.DataEntity.GoodscatrgoryEntity> dataList;
    public Context mContext;
    public RecycleGoodsCategoryListAdapter(List<GoodsListBean.DataEntity.GoodscatrgoryEntity> dataList, Context context) {
        this.dataList=dataList;
        this.mContext=context;
    }

    /**
     * 数据发生改变的方法
     * @param dataList 发生改变的集合类
     */
    public  void  changeData(List<GoodsListBean.DataEntity.GoodscatrgoryEntity> dataList){
        this.dataList=dataList;
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
        holder.shopCartNum.setText(String.valueOf(dataList.get(position).getBugNum()));
        if(dataList.get(position).getBugNum()>0){
            holder.shopCartNum.setVisibility(View.VISIBLE);
        }else{
            holder.shopCartNum.setVisibility(View.GONE);
        }
        if (selectPosition != -1) {
            if (selectPosition == position) {
                holder.goodsCategoryName.setBackgroundResource(R.mipmap.icon_goodscate_bg);
                holder.goodsCategoryName.setTextColor(Color.rgb(255,255,255));
            } else {
                holder.goodsCategoryName.setBackgroundColor(Color.rgb(255,255,255));
                holder.goodsCategoryName.setTextColor(Color.rgb(55,55,55));
            }
        } else {
            holder.goodsCategoryName.setBackgroundColor(Color.rgb(255,255,255));
            holder.goodsCategoryName.setTextColor(Color.rgb(55,55,55));
        }
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(view,position);
                }
            }
        });

    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener=mOnItemClickListener;
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }


    /**
     * 设置选中index
     * @param position 选中分类的下标
     */
    public void setCheckPosition(int position) {
        this.selectPosition = position;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView goodsCategoryName;
        public final TextView shopCartNum;
        public final View root;
        public ViewHolder(View root) {
            super(root);
            goodsCategoryName = (TextView) root.findViewById(R.id.goodsCategoryNameTV);
            shopCartNum = (TextView) root.findViewById(R.id.shopCartNumTV);
            this.root = root;
        }

    }

}
