package com.store.zerone.zeronestore.event;
import com.store.zerone.zeronestore.domain.GoodsBeanUp;
import com.store.zerone.zeronestore.domain.GoodsListBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dalong on 2016/12/27.
 */

public class MessageEvent implements Serializable {
    public int  num;
    public Double  price;
    public List<GoodsListBean.DataEntity.GoodscatrgoryEntity.GoodsitemEntity> goods;
    public List<GoodsBeanUp> list;

    public MessageEvent(int totalNum, Double price,List<GoodsBeanUp> list,List<GoodsListBean.DataEntity.GoodscatrgoryEntity.GoodsitemEntity> goods) {
        this.num = totalNum;
        this.price = price;
        this.list=list;
        this.goods=goods;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "num=" + num +
                ", price=" + price +
                ", list=" + list +
                '}';
    }
}
