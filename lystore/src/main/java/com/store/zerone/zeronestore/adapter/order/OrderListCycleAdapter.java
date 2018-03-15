package com.store.zerone.zeronestore.adapter.order;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.store.zerone.zeronestore.R;
import com.store.zerone.zeronestore.adapter.BranchSelectedAdapter;
import com.store.zerone.zeronestore.domain.DaiFuKuanOrderBean;
import com.store.zerone.zeronestore.utils.UtilsTime;

import java.util.List;

/**
 * Created by Administrator on 2017/6/21.
 */

public class OrderListCycleAdapter extends RecyclerView.Adapter<OrderListCycleAdapter.ViewHolder> {
    private Context context;
    private List<DaiFuKuanOrderBean.DataBean.ListBean> getOrderList;
    private BranchSelectedAdapter.OnItemClickListener mOnItemClickListener;
    private BranchSelectedAdapter.OnItemLongClickListener mOnItemLongClickListener;
    public OrderListCycleAdapter(List<DaiFuKuanOrderBean.DataBean.ListBean> getOrderList) {
        this.getOrderList = getOrderList;
    }
    @Override
    public OrderListCycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context==null){
            context=parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.activity_order_list_items,parent,false);
        return new OrderListCycleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final OrderListCycleAdapter.ViewHolder holder, int position) {
        //这个需要修改
            holder.tableame.setText(getOrderList.get(position).getTableName());
            holder.ordertime.setText(UtilsTime.getTime(Long.parseLong(getOrderList.get(position).getCreatetime())));
            holder.order_number.setText(getOrderList.get(position).getOrdersn());
            double sale = Double.parseDouble(getOrderList.get(position).getSale());
            double  money=Double.parseDouble(getOrderList.get(position).getPrice());
            double newmoney=sale*money;
            holder.orderMoney.setText("￥"+newmoney);
            holder.order_canweifei.setText("￥"+getOrderList.get(position).getSeat_fee());
        //订单类型
        if ("0".equals(getOrderList.get(position).getStatus())){
          holder.paystatus.setText("待付款");
            holder.paystatus.setBackgroundResource(R.mipmap.icon_order_df_status_bg);
            holder.tableame.setBackgroundResource(R.mipmap.icon_order_df_table_bg);
        }else   if ("1".equals(getOrderList.get(position).getStatus())){
            holder.paystatus.setText("已付款");
            holder.paystatus.setBackgroundResource(R.mipmap.icon_order_yw_status_bg);
            holder.tableame.setBackgroundResource(R.mipmap.icon_order_yw_table_bg);
        }else   if ("2".equals(getOrderList.get(position).getStatus())){
            holder.paystatus.setText("待收货");
        }else   if ("3".equals(getOrderList.get(position).getStatus())){
            holder.paystatus.setText("已完成");
            holder.paystatus.setBackgroundResource(R.mipmap.icon_order_yw_status_bg);
            holder.tableame.setBackgroundResource(R.mipmap.icon_order_yw_table_bg);
        }else   if ("-1".equals(getOrderList.get(position).getStatus())){
            holder.paystatus.setText("已关闭");
        }else   if ("-2".equals(getOrderList.get(position).getStatus())){
            holder.paystatus.setText("退款中");
        }else   if ("-3".equals(getOrderList.get(position).getStatus())){
            holder.paystatus.setText("换货中");
        }else   if ("-4".equals(getOrderList.get(position).getStatus())){
            holder.paystatus.setText("退货中");
        }else   if ("-5".equals(getOrderList.get(position).getStatus())){
            holder.paystatus.setText("已退货");
        }else   if ("-6".equals(getOrderList.get(position).getStatus())){
            holder.paystatus.setText("已退款");
        }

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
        if (getOrderList!=null){
            return getOrderList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;
        private final TextView tableame;
        private final TextView  paystatus;
        private final TextView   orderMoney;
        private  final  TextView ordertime;
        private  final ImageView q_print_status;
        private  final ImageView h_print_status;
        private  final  TextView order_number;
        private  final  TextView order_canweifei;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            tableame=itemView.findViewById(R.id.order_tablename);
            paystatus=itemView.findViewById(R.id.pay_status);
            ordertime  =itemView.findViewById(R.id.ordertime);
            q_print_status = itemView.findViewById(R.id.q_print_status);
            h_print_status = itemView.findViewById(R.id.h_print_status);
            order_number =itemView.findViewById(R.id.order_number);
            orderMoney= itemView.findViewById(R.id.order_money);
            order_canweifei =itemView.findViewById(R.id.order_canweifei);

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
