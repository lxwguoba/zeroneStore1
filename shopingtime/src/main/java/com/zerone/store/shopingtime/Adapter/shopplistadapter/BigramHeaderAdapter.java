package com.zerone.store.shopingtime.Adapter.shopplistadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eowise.recyclerview.stickyheaders.StickyHeadersAdapter;
import com.zerone.store.shopingtime.Bean.ShopBean;
import com.zerone.store.shopingtime.Bean.shoplistbean.GoodsCategroyListBean;
import com.zerone.store.shopingtime.R;

import java.util.List;

public class BigramHeaderAdapter implements StickyHeadersAdapter<BigramHeaderAdapter.ViewHolder> {


    private Context mContext;
    private List<ShopBean> dataList;
    private List<GoodsCategroyListBean.DataBean.CategorylistBean> goodscatrgoryEntities;


    public BigramHeaderAdapter(Context context, List<ShopBean> items, List<GoodsCategroyListBean.DataBean.CategorylistBean> goodscatrgoryEntities) {
        this.mContext = context;
        this.dataList = items;
        this.goodscatrgoryEntities = goodscatrgoryEntities;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_goods_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder headerViewHolder, int position) {
//        这个地方有问题
        headerViewHolder.tvGoodsItemTitle.setText(goodscatrgoryEntities.get(position).getName());
    }

    @Override
    public long getHeaderId(int position) {

        return dataList.get(position).getId();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvGoodsItemTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            tvGoodsItemTitle = (TextView) itemView.findViewById(R.id.tvGoodsItemTitle);
        }
    }
}
