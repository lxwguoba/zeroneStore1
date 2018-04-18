package com.zerone.store.shopingtime.Bean.order;

import java.io.Serializable;

/**
 * Created by on 2018/4/2 0002 18 36.
 * Author  LiuXingWen
 * 订单实体类
 */

public class OrderBean implements Serializable {
    //订单ID
    private String id;
    //订单编号
    private String ordersn;
    //订单价格
    private String order_price;
    //订单状态
    private String status;
    //created_at
    private String created_at;

    public OrderBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrdersn() {
        return ordersn;
    }

    public void setOrdersn(String ordersn) {
        this.ordersn = ordersn;
    }

    public String getOrder_price() {
        return order_price;
    }

    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
