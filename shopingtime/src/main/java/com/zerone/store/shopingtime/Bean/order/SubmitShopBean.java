package com.zerone.store.shopingtime.Bean.order;

import java.io.Serializable;

/**
 * Created by on 2018/4/8 0008 10 21.
 * Author  LiuXingWen
 */

public class SubmitShopBean implements Serializable {

    private String id;
    private String num;
    private String spec;
    private String price;

    public SubmitShopBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SubmitShopBean{" +
                "id='" + id + '\'' +
                ", num='" + num + '\'' +
                ", spec='" + spec + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
