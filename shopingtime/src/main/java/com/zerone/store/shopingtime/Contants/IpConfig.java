package com.zerone.store.shopingtime.Contants;

/**
 * Created by Administrator on 2017/10/10 0010.
 * <p>
 * 用来保存域名 就是链接
 */
public class IpConfig {
    //    public  static final   String URL = "https://catering.dz-ck.com/api.php?";
    //正式版
//        public  static final   String URL = "https://mi.greengod.cn/site.php?";
    //测试版
    public static final String URL = "https://fan.greengod.cn/site.php?";
    //登录接口
    public static final String URL_LOGIN = "http://o2o.01nnt.com/api/androidapi/login";
    //分类接口
    public static final String URL_CATEGORY = "http://o2o.01nnt.com/api/androidapi/goodscategory";
    // 商品查询接口
    public static final String URL_SERACH = "http://o2o.01nnt.com/api/androidapi/goodslist";
    //获取图片接口
    public static final String URL_GETPICTURE = "http://o2o.01nnt.com/";

    public static final String URL_GOODSLIST = "http://o2o.01nnt.com/api/androidapi/goodslist";
    //开启/关闭零库存开单接口
    public static final String URL_KQLKC = "http://o2o.01nnt.com/api/androidapi/allow_zero_stock";
    //下单减库存/付款减库存接口
    public static final String URL_FKJKC = "http://o2o.01nnt.com/api/androidapi/change_stock_role";
    //查询店铺设置
    public static final String URL_DPSZ = "http://o2o.01nnt.com/api/androidapi/stock_cfg";
    //订单详情接口
    public static final String URL_ORDERDETAILS = "http://o2o.01nnt.com/api/androidapi/order_detail";
    //订单列表接口
    public static final String URL_ORDERLIST = "http://o2o.01nnt.com/api/androidapi/order_list";
    //取消订单
    public static final String URL_QXORDER = "http://o2o.01nnt.com/api/androidapi/cancel_order";
    //取消订单
    public static final String URL_SUBMITORDER = "http://o2o.01nnt.com/api/androidapi/order_check";
    //修改订单支付状态接口（现金除外）
    public static final String URL_UPDATAPAY = "http://o2o.01nnt.com/api/androidapi/other_payment";
    //现金支付方式
    public static final String URL_CASHPAY = "http://o2o.01nnt.com/api/androidapi/cash_payment";
}
