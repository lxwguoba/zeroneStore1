package com.store.zerone.zeronestore.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by on 2018/1/31 0031 17 42.
 * Author  LiuXingWen
 * 打印封装实体类
 */

public class PrintBean implements Serializable {
    //订单编号
    private  String ordersn;
    //下单时间
    private  String createTime ;
    //订单总金额
    private  String  pmoney;
    //桌子名称
    private String  table_name;
    //餐位费
    private String   canweifei;
    //订单状态
    private String   orderTuype;
    //备注
    private  String  remark;
    private List<PrintItem> list;


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrdersn() {
        return ordersn;
    }

    public void setOrdersn(String ordersn) {
        this.ordersn = ordersn;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPmoney() {
        return pmoney;
    }

    public void setPmoney(String pmoney) {
        this.pmoney = pmoney;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getCanweifei() {
        return canweifei;
    }

    public void setCanweifei(String canweifei) {
        this.canweifei = canweifei;
    }

    public String getOrderTuype() {
        return orderTuype;
    }

    public void setOrderTuype(String orderTuype) {
        this.orderTuype = orderTuype;
    }

    public List<PrintItem> getList() {
        return list;
    }

    public void setList(List<PrintItem> list) {
        this.list = list;
    }
}
