package com.zerone.store.shopingtime.Bean.order;

import java.io.Serializable;

/**
 * Created by on 2018/4/3 0003 10 59.
 * Author  LiuXingWen
 * 这个是订单详情中的商品列表展示数据
 */

public class GoodsBean implements Serializable {

    //商品ID
    private String goods_id;
    //商品标题
    private String goods_title;
    //商品图片
    private String goods_thumb;
    //商品描述
    private String goods_details;
    //总数
    private String goods_total;
    //价格
    private String goods_price;

    public GoodsBean() {

    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_title() {
        return goods_title;
    }

    public void setGoods_title(String goods_title) {
        this.goods_title = goods_title;
    }

    public String getGoods_thumb() {
        return goods_thumb;
    }

    public void setGoods_thumb(String goods_thumb) {
        this.goods_thumb = goods_thumb;
    }

    public String getGoods_details() {
        return goods_details;
    }

    public void setGoods_details(String goods_details) {
        this.goods_details = goods_details;
    }

    public String getGoods_total() {
        return goods_total;
    }

    public void setGoods_total(String goods_total) {
        this.goods_total = goods_total;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }
}
