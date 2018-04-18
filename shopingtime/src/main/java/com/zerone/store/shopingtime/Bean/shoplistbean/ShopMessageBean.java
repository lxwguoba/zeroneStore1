package com.zerone.store.shopingtime.Bean.shoplistbean;

import java.io.Serializable;

/**
 * Created by on 2018/4/2 0002 15 43.
 * Author  LiuXingWen
 * <p>
 * 商品列表信息
 */

public class ShopMessageBean implements Serializable {

    //商品id
    private String sp_id;
    private String sp_name;
    private String sp_discount;
    private String sp_price;
    private String sp_count;
    private String sp_picture_url;
    private boolean sp_check;
    private String category_id;


    public ShopMessageBean() {

    }


    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getSp_id() {
        return sp_id;
    }

    public void setSp_id(String sp_id) {
        this.sp_id = sp_id;
    }

    public String getSp_name() {
        return sp_name;
    }

    public void setSp_name(String sp_name) {
        this.sp_name = sp_name;
    }

    public String getSp_discount() {
        return sp_discount;
    }

    public void setSp_discount(String sp_discount) {
        this.sp_discount = sp_discount;
    }

    public String getSp_price() {
        return sp_price;
    }

    public void setSp_price(String sp_price) {
        this.sp_price = sp_price;
    }

    public String getSp_count() {
        return sp_count;
    }

    public void setSp_count(String sp_count) {
        this.sp_count = sp_count;
    }

    public String getSp_picture_url() {
        return sp_picture_url;
    }

    public void setSp_picture_url(String sp_picture_url) {
        this.sp_picture_url = sp_picture_url;
    }

    public boolean isSp_check() {
        return sp_check;
    }

    public void setSp_check(boolean sp_check) {
        this.sp_check = sp_check;
    }

    @Override
    public String toString() {
        return "ShopMessageBean{" +
                "sp_id='" + sp_id + '\'' +
                ", sp_name='" + sp_name + '\'' +
                ", sp_discount='" + sp_discount + '\'' +
                ", sp_price='" + sp_price + '\'' +
                ", sp_count='" + sp_count + '\'' +
                ", sp_picture_url='" + sp_picture_url + '\'' +
                ", sp_check=" + sp_check +
                ", category_id='" + category_id + '\'' +
                '}';
    }
}
