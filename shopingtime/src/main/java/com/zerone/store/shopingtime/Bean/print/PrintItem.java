package com.zerone.store.shopingtime.Bean.print;

import java.io.Serializable;

/**
 * Created by on 2018/1/31 0031 17 55.
 * Author  LiuXingWen
 */

public class PrintItem implements Serializable {

    private String goodsname;
    private String gcount;
    private String gprice;
//      private  String  options;

//    public String getOptions() {
//        return options;
//    }
//
//    public void setOptions(String options) {
//        this.options = options;
//    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getGcount() {
        return gcount;
    }

    public void setGcount(String gcount) {
        this.gcount = gcount;
    }

    public String getGprice() {
        return gprice;
    }

    public void setGprice(String gprice) {
        this.gprice = gprice;
    }
}
