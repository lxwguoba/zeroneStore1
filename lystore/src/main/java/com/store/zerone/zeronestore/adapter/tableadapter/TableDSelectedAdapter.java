package com.store.zerone.zeronestore.adapter.tableadapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.store.zerone.zeronestore.R;
import com.store.zerone.zeronestore.adapter.BranchSelectedAdapter;
import com.store.zerone.zeronestore.domain.TableDBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/21.
 */

public class TableDSelectedAdapter extends RecyclerView.Adapter<TableDSelectedAdapter.ViewHolder> {
    private Context context;
    private List<TableDBean.DataBean> list;
    private BranchSelectedAdapter.OnItemClickListener mOnItemClickListener;
    private BranchSelectedAdapter.OnItemLongClickListener mOnItemLongClickListener;
    public TableDSelectedAdapter(List<TableDBean.DataBean> list) {
        this.list = list;
    }
    @Override
    public TableDSelectedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context==null){
            context=parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.activity_table_d_items,parent,false);

        return new TableDSelectedAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TableDSelectedAdapter.ViewHolder holder, int position) {
        //这个需要修改
        if ("0".equals(list.get(position).getUsing())){
            holder.tablestatus.setText("空闲中");
            holder.tablestatus.setTextColor(Color.parseColor("#69d397"));
            holder.tableame.setTextColor(Color.parseColor("#000000"));
            holder.percount.setTextColor(Color.parseColor("#9a9a9a"));
            holder.cardView.setBackgroundResource(R.mipmap.icon_table_normal_bg);
         } else if ("1".equals(list.get(position).getUsing())){
                    holder.tablestatus.setText("使用中");
            holder.tablestatus.setTextColor(Color.parseColor("#ffffff"));
            holder.tableame.setTextColor(Color.parseColor("#ffffff"));
            holder.percount.setTextColor(Color.parseColor("#ffffff"));
            holder.cardView.setBackgroundResource(R.mipmap.icon_table_selected_bg);
            } else  if ("2".equals(list.get(position).getUsing())){
                        holder.tablestatus.setText("使用中");
                        holder.tablestatus.setTextColor(Color.parseColor("#ffffff"));
                        holder.tableame.setTextColor(Color.parseColor("#ffffff"));
                        holder.percount.setTextColor(Color.parseColor("#ffffff"));
                        holder.cardView.setBackgroundResource(R.mipmap.icon_table_selected_bg);
                    }
        holder.tableame.setText(list.get(position).getTablename());
        holder.percount.setText(list.get(position).getMaxseat()+"座");
        if(mOnItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView,position); // 2
                }
            });
        }
        if(mOnItemLongClickListener != null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(holder.itemView,position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;
        private final TextView tableame;
        private final TextView  tablestatus;
        private final TextView   percount;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            tableame=itemView.findViewById(R.id.table_name);
            tablestatus=itemView.findViewById(R.id.table_status);
            percount=itemView.findViewById(R.id.table_per_count);
        }
    }

    //
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    public interface OnItemLongClickListener{
        void onItemLongClick(View view, int position);
    }
    //
    public void setOnItemClickListener(BranchSelectedAdapter.OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(BranchSelectedAdapter.OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }
}
