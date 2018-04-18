package com.zerone.store.shopingtime.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by on 2018/4/4 0004 10 36.
 * Author  LiuXingWen
 */

public class ShopBean implements Serializable {

    /**
     * id : 2
     * name : 茄子豆角
     * category_id : 8
     * details : 茄子豆角茄子豆角茄子豆角茄子豆角茄子豆角茄子豆角
     * price : 12.00
     * stock : 986
     * category_name : 盖浇饭
     * thumb : [{"thumb":"uploads/catering/20180317025956983.jpg"}]
     */

    private int id;
    private String name;
    private int category_id;
    private String details;
    private String price;
    private int stock;
    private String category_name;
    private String shop_Count;
    private List<ThumbBean> thumb;

    public String getShop_Count() {
        return shop_Count;
    }

    public void setShop_Count(String shop_Count) {
        this.shop_Count = shop_Count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public List<ThumbBean> getThumb() {
        return thumb;
    }

    public void setThumb(List<ThumbBean> thumb) {
        this.thumb = thumb;
    }

    @Override
    public String toString() {
        return "ShopBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category_id=" + category_id +
                ", details='" + details + '\'' +
                ", price='" + price + '\'' +
                ", stock=" + stock +
                ", category_name='" + category_name + '\'' +
                ", shop_Count='" + shop_Count + '\'' +
                ", thumb=" + thumb +
                '}';
    }

    public static class ThumbBean implements Serializable {
        /**
         * thumb : uploads/catering/20180317025956983.jpg
         */

        private String thumb;


        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        @Override
        public String toString() {
            return "ThumbBean{" +
                    "thumb='" + thumb + '\'' +
                    '}';
        }
    }
}
