package com.store.zerone.zeronestore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.store.zerone.zeronestore.R;
import com.store.zerone.zeronestore.domain.Branch;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/6/21.
 */

public class BranchSelectedAdapter extends RecyclerView.Adapter<BranchSelectedAdapter.ViewHolder> {
    private Context context;
    private List<Branch> list;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    public BranchSelectedAdapter(List<Branch> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context==null){
            context=parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.activity_branch_selected_items,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Branch fd = list.get(position);
        holder.fDName.setText(fd.getZtdname());
        Glide.with(context).load(fd.getThumb()).into(holder.fDImg);
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

        private final TextView fDName;
        private final CircleImageView fDImg;
        public ViewHolder(View itemView) {
            super(itemView);
            fDImg = (CircleImageView) itemView.findViewById(R.id.fd_img);
            fDName  = (TextView) itemView.findViewById(R.id.fd_name);
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
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }
}
