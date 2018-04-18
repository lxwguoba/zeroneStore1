package com.zerone.store.shopingtime.Contants;

import java.io.Serializable;

/**
 * Created by on 2018/1/23 0023 10 50.
 * Author  LiuXingWen
 */

public class ContantData implements Serializable {

    //登录的返回
    public static final int LOGINRESPONSE = 1;
    //获取分店的返回
    public static final int GETBRANCHRESPONSE = 2;
    //获取员工列表
    public static final int GETWORKERRESPONSE = 3;
    //获取餐桌分类
    public static final int GETTABLECATRRESPONSE = 4;
    //跳转到桌子页面的求情码
    public static final int REQUESTCODE = 5;
    //获取房间下的桌子
    public static final int GETTABLED = 6;
    //展示popwindow的内容
    public static final int POPSHOW = 7;
    //获取商品的信息
    public static final int GETGOODSINFO = 8;
    //提交订单
    public static final int SUBMITORDER = 9;
    //获得散客id
    public static final int GETSANKE = 10;

    //登录的返回
    public static final int MAINMAKEORDERREQUESE = 11;
    //获取订单列表
    public static final int GETORDERLISTDF = 12;
    //获取订单详情
    public static final int GETORDERLISTDETAILS = 13;
    //获取订单详情
    public static final int GETTABLEORDERLIST = 14;
    //结账
    public static final int JIEZHANG = 15;
    //刷新桌子的数据
    public static final int FLUSH = 16;
    //获取规格
    public static final int GETOPTIONS = 17;
    //获取规格的价格
    public static final int GETOPTIONSPRICE = 18;
    //获取会员信息
    public static final int GETVIPINFO = 20;

    //扫码返回的结果
    public static final int SCANQRCODE = 21;
}
