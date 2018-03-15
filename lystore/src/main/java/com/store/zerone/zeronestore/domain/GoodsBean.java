package com.store.zerone.zeronestore.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by on 2018/1/24 0024 16 50.
 * Author  LiuXingWen
 */

public class GoodsBean implements Serializable {

    private  int  gb_id;
    private  int g_count;
    private String id;
    private String title;
    private String marketprice;
    private String thumb;
    private String total;
    private String hasoption;
    private String yuanjia;
    private int total_in_cart;
    private List<GoodsListBean.DataEntity.GoodscatrgoryEntity.GoodsitemEntity.OptionsBean> options;

    public int getG_count() {
        return g_count;
    }

    public void setG_count(int g_count) {
        this.g_count = g_count;
    }

    public int getGb_id() {
        return gb_id;
    }

    public void setGb_id(int gb_id) {
        this.gb_id = gb_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMarketprice() {
        return marketprice;
    }

    public void setMarketprice(String marketprice) {
        this.marketprice = marketprice;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getHasoption() {
        return hasoption;
    }

    public void setHasoption(String hasoption) {
        this.hasoption = hasoption;
    }

    public String getYuanjia() {
        return yuanjia;
    }

    public void setYuanjia(String yuanjia) {
        this.yuanjia = yuanjia;
    }

    public int getTotal_in_cart() {
        return total_in_cart;
    }

    public void setTotal_in_cart(int total_in_cart) {
        this.total_in_cart = total_in_cart;
    }

    public List<GoodsListBean.DataEntity.GoodscatrgoryEntity.GoodsitemEntity.OptionsBean> getOptions() {
        return options;
    }

    public void setOptions(List<GoodsListBean.DataEntity.GoodscatrgoryEntity.GoodsitemEntity.OptionsBean> options) {
        this.options = options;
    }

    public static class OptionsBean {
        /**
         * optionid : 0
         * optionname : 无
         */

        private int optionid;
        private String optionname;

        public int getOptionid() {
            return optionid;
        }

        public void setOptionid(int optionid) {
            this.optionid = optionid;
        }

        public String getOptionname() {
            return optionname;
        }

        public void setOptionname(String optionname) {
            this.optionname = optionname;
        }
    }
}
