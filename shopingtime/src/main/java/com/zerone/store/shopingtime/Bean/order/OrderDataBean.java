package com.zerone.store.shopingtime.Bean.order;

import java.io.Serializable;

/**
 * Created by on 2018/4/3 0003 19 54.
 * Author  LiuXingWen
 */

public class OrderDataBean implements Serializable {
    //订单ID
    private String id;
    //订单编号
    private String ordersn;
    //订单价格
    private String order_price;
    //备注
    private String remarks;
    //u用户ID
    private String user_id;
    //用户账号
    private String user_account;
    //支付公司
    private String payment_company;
    //订单类型
    /**
     * 0-未知类型
     * 1-现场订单
     * 2-外卖订单
     */
    private String order_type;
    //订单状态
    /**
     * -1-取消状态
     * 0-待付款
     * 1-为已付款
     */
    private String status;
    //支付类型
    /**
     * -1.现金支付，其他支付
     * 0.银行卡支付
     * 1.支付宝扫码
     * 2.支付宝二维码
     * 3.微信扫码
     * 4.微信二维码
     */
    private String paytype;
    //操作员ID
    private String operator_id;
    //操作员账号
    private String operator_account;
    //店铺ID
    private String retail_id;

    public OrderDataBean() {
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getPayment_company() {
        return payment_company;
    }

    public void setPayment_company(String payment_company) {
        this.payment_company = payment_company;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }

    public String getOperator_account() {
        return operator_account;
    }

    public void setOperator_account(String operator_account) {
        this.operator_account = operator_account;
    }

    public String getRetail_id() {
        return retail_id;
    }

    public void setRetail_id(String retail_id) {
        this.retail_id = retail_id;
    }
}
