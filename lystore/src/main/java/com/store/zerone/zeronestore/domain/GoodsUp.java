package com.store.zerone.zeronestore.domain;

import java.io.Serializable;

/**
 * Created by on 2018/1/26 0026 09 48.
 * Author  LiuXingWen
 */

public class GoodsUp implements Serializable {
    // 商品id
    private  String goodsid;
    // 规格id
    private String optionid;
    //数量
    private int number;

    public GoodsUp() {
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    public String getOptionid() {
        return optionid;
    }

    public void setOptionid(String optionid) {
        this.optionid = optionid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
