package com.store.zerone.zeronestore.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 * 订单信息
 */

public class OrderInfo implements Serializable {

    /**
     * status : 1
     * data : {"item":{"id":"372","ordersn":"17081012451161","stockminus":null,"weid":"1","shareid":"3","from_user":"oM4K9wKW8PP8NQDyj9Qm0kE7wmnw","price":"29","goodsprice":"29.00","dispatchprice":"0.00","fetchnextday":"0","transid":"0","status":"0","sendtype":"3","paytype":"4","goodstype":"1","remark":"","addressid":"364","expresssn":"","express":"","expresscom":"","dispatchwkid":"0","createtime":"1502337993","updatetime":"0","isrest":"0","rsreson":"","sendtime":"0","confirmedby":"0","hongbao":"0.00","ztdid":"1","tableid":"0","ztdname":"柳州螺蛳粉","ztpassword":"","ztdwkrid":"0","dispatchchoice":"3","datenum":"0","datetime":"0","reported":"0","discount":"0.00","user":{"id":"364","mobile":"","province":"","city":"","area":"[现场交易]","address":"北京市贞观国际"},"goods":[{"id":"49","total":"1","price":"11.00","title":"凉瓜炒蛋饭","thumb":"http://ctest.greengod.cn/resource/attachment/images/2017/06/AL3Q5zmEOt3h6QNdXTq69z0z3pXhN9.jpg","unit":"","goodssn":"342","productsn":"72342","marketprice":"11.00","oid":"1105","type":"0","optionname":null,"optionid":"0","commission":"0.55"},{"id":"2526","total":"1","price":"10.00","title":"丝瓜","thumb":"http://ctest.greengod.cn/resource/attachment/images/2017/06/H6n36t7TZbNatnwWhr37RBsbIiKwp3.jpg","unit":"","goodssn":"545","productsn":"","marketprice":"10.00","oid":"1104","type":"0","optionname":null,"optionid":"0","commission":"0.50"},{"id":"48","total":"1","price":"8.00","title":"墨西哥鸡肉卷十小可","thumb":"http://ctest.greengod.cn/resource/attachment/images/2017/06/Xy31zXxenGZ25ZXEM1Z5j5njyeUJJj.jpg","unit":"","goodssn":"3132","productsn":"321312","marketprice":"8.00","oid":"1103","type":"0","optionname":null,"optionid":"0","commission":"0.40"}]},"orderownner":{"id":"13","weid":"1","realname":"O2O云服务平台-赵向阳"},"sharemem":{"id":"3","from_user":"oM4K9wCoiBwY3S13sK6TYole5JDQ","realname":"唐中林13662666768"},"shopownner":{"from_user":"oM4K9wN1k6SmrAd5XnGszRfZ92wA"},"tableinfo":{"id":"173","orderid":"372","dispatchchoice":"3","tableid":"123","datenum":"3","datetime_st":"1502337993","datetime_nd":"0","status":"1"}}
     */

    private int status;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * item : {"id":"372","ordersn":"17081012451161","stockminus":null,"weid":"1","shareid":"3","from_user":"oM4K9wKW8PP8NQDyj9Qm0kE7wmnw","price":"29","goodsprice":"29.00","dispatchprice":"0.00","fetchnextday":"0","transid":"0","status":"0","sendtype":"3","paytype":"4","goodstype":"1","remark":"","addressid":"364","expresssn":"","express":"","expresscom":"","dispatchwkid":"0","createtime":"1502337993","updatetime":"0","isrest":"0","rsreson":"","sendtime":"0","confirmedby":"0","hongbao":"0.00","ztdid":"1","tableid":"0","ztdname":"柳州螺蛳粉","ztpassword":"","ztdwkrid":"0","dispatchchoice":"3","datenum":"0","datetime":"0","reported":"0","discount":"0.00","user":{"id":"364","mobile":"","province":"","city":"","area":"[现场交易]","address":"北京市贞观国际"},"goods":[{"id":"49","total":"1","price":"11.00","title":"凉瓜炒蛋饭","thumb":"http://ctest.greengod.cn/resource/attachment/images/2017/06/AL3Q5zmEOt3h6QNdXTq69z0z3pXhN9.jpg","unit":"","goodssn":"342","productsn":"72342","marketprice":"11.00","oid":"1105","type":"0","optionname":null,"optionid":"0","commission":"0.55"},{"id":"2526","total":"1","price":"10.00","title":"丝瓜","thumb":"http://ctest.greengod.cn/resource/attachment/images/2017/06/H6n36t7TZbNatnwWhr37RBsbIiKwp3.jpg","unit":"","goodssn":"545","productsn":"","marketprice":"10.00","oid":"1104","type":"0","optionname":null,"optionid":"0","commission":"0.50"},{"id":"48","total":"1","price":"8.00","title":"墨西哥鸡肉卷十小可","thumb":"http://ctest.greengod.cn/resource/attachment/images/2017/06/Xy31zXxenGZ25ZXEM1Z5j5njyeUJJj.jpg","unit":"","goodssn":"3132","productsn":"321312","marketprice":"8.00","oid":"1103","type":"0","optionname":null,"optionid":"0","commission":"0.40"}]}
         * orderownner : {"id":"13","weid":"1","realname":"O2O云服务平台-赵向阳"}
         * sharemem : {"id":"3","from_user":"oM4K9wCoiBwY3S13sK6TYole5JDQ","realname":"唐中林13662666768"}
         * shopownner : {"from_user":"oM4K9wN1k6SmrAd5XnGszRfZ92wA"}
         * tableinfo : {"id":"173","orderid":"372","dispatchchoice":"3","tableid":"123","datenum":"3","datetime_st":"1502337993","datetime_nd":"0","status":"1"}
         */

        private ItemBean item;
        private OrderownnerBean orderownner;
        private SharememBean sharemem;
        private ShopownnerBean shopownner;
        private TableinfoBean tableinfo;

        public ItemBean getItem() {
            return item;
        }

        public void setItem(ItemBean item) {
            this.item = item;
        }

        public OrderownnerBean getOrderownner() {
            return orderownner;
        }

        public void setOrderownner(OrderownnerBean orderownner) {
            this.orderownner = orderownner;
        }

        public SharememBean getSharemem() {
            return sharemem;
        }

        public void setSharemem(SharememBean sharemem) {
            this.sharemem = sharemem;
        }

        public ShopownnerBean getShopownner() {
            return shopownner;
        }

        public void setShopownner(ShopownnerBean shopownner) {
            this.shopownner = shopownner;
        }

        public TableinfoBean getTableinfo() {
            return tableinfo;
        }

        public void setTableinfo(TableinfoBean tableinfo) {
            this.tableinfo = tableinfo;
        }

        public static class ItemBean {
            /**
             * id : 372
             * ordersn : 17081012451161
             * stockminus : null
             * weid : 1
             * shareid : 3
             * from_user : oM4K9wKW8PP8NQDyj9Qm0kE7wmnw
             * price : 29
             * goodsprice : 29.00
             * dispatchprice : 0.00
             * fetchnextday : 0
             * transid : 0
             * status : 0
             * sendtype : 3
             * paytype : 4
             * goodstype : 1
             * remark :
             * addressid : 364
             * expresssn :
             * express :
             * expresscom :
             * dispatchwkid : 0
             * createtime : 1502337993
             * updatetime : 0
             * isrest : 0
             * rsreson :
             * sendtime : 0
             * confirmedby : 0
             * hongbao : 0.00
             * ztdid : 1
             * tableid : 0
             * ztdname : 柳州螺蛳粉
             * ztpassword :
             * ztdwkrid : 0
             * dispatchchoice : 3
             * datenum : 0
             * datetime : 0
             * reported : 0
             * discount : 0.00
             * user : {"id":"364","mobile":"","province":"","city":"","area":"[现场交易]","address":"北京市贞观国际"}
             * goods : [{"id":"49","total":"1","price":"11.00","title":"凉瓜炒蛋饭","thumb":"http://ctest.greengod.cn/resource/attachment/images/2017/06/AL3Q5zmEOt3h6QNdXTq69z0z3pXhN9.jpg","unit":"","goodssn":"342","productsn":"72342","marketprice":"11.00","oid":"1105","type":"0","optionname":null,"optionid":"0","commission":"0.55"},{"id":"2526","total":"1","price":"10.00","title":"丝瓜","thumb":"http://ctest.greengod.cn/resource/attachment/images/2017/06/H6n36t7TZbNatnwWhr37RBsbIiKwp3.jpg","unit":"","goodssn":"545","productsn":"","marketprice":"10.00","oid":"1104","type":"0","optionname":null,"optionid":"0","commission":"0.50"},{"id":"48","total":"1","price":"8.00","title":"墨西哥鸡肉卷十小可","thumb":"http://ctest.greengod.cn/resource/attachment/images/2017/06/Xy31zXxenGZ25ZXEM1Z5j5njyeUJJj.jpg","unit":"","goodssn":"3132","productsn":"321312","marketprice":"8.00","oid":"1103","type":"0","optionname":null,"optionid":"0","commission":"0.40"}]
             */

            private String id;
            private String ordersn;
            private Object stockminus;
            private String weid;
            private String shareid;
            private String from_user;
            private String price;
            private String goodsprice;
            private String dispatchprice;
            private String fetchnextday;
            private String transid;
            private String status;
            private String sendtype;
            private String paytype;
            private String goodstype;
            private String remark;
            private String addressid;
            private String expresssn;
            private String express;
            private String expresscom;
            private String dispatchwkid;
            private String createtime;
            private String updatetime;
            private String isrest;
            private String rsreson;
            private String sendtime;
            private String confirmedby;
            private String hongbao;
            private String ztdid;
            private String tableid;
            private String ztdname;
            private String ztpassword;
            private String ztdwkrid;
            private String dispatchchoice;
            private String datenum;
            private String datetime;
            private String reported;
            private String discount;
            private String seat_fee;
            private UserBean user;
            private List<GoodsBean> goods;

            public String getSeat_fee() {
                return seat_fee;
            }

            public void setSeat_fee(String seat_fee) {
                this.seat_fee = seat_fee;
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

            public Object getStockminus() {
                return stockminus;
            }

            public void setStockminus(Object stockminus) {
                this.stockminus = stockminus;
            }

            public String getWeid() {
                return weid;
            }

            public void setWeid(String weid) {
                this.weid = weid;
            }

            public String getShareid() {
                return shareid;
            }

            public void setShareid(String shareid) {
                this.shareid = shareid;
            }

            public String getFrom_user() {
                return from_user;
            }

            public void setFrom_user(String from_user) {
                this.from_user = from_user;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getGoodsprice() {
                return goodsprice;
            }

            public void setGoodsprice(String goodsprice) {
                this.goodsprice = goodsprice;
            }

            public String getDispatchprice() {
                return dispatchprice;
            }

            public void setDispatchprice(String dispatchprice) {
                this.dispatchprice = dispatchprice;
            }

            public String getFetchnextday() {
                return fetchnextday;
            }

            public void setFetchnextday(String fetchnextday) {
                this.fetchnextday = fetchnextday;
            }

            public String getTransid() {
                return transid;
            }

            public void setTransid(String transid) {
                this.transid = transid;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getSendtype() {
                return sendtype;
            }

            public void setSendtype(String sendtype) {
                this.sendtype = sendtype;
            }

            public String getPaytype() {
                return paytype;
            }

            public void setPaytype(String paytype) {
                this.paytype = paytype;
            }

            public String getGoodstype() {
                return goodstype;
            }

            public void setGoodstype(String goodstype) {
                this.goodstype = goodstype;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getAddressid() {
                return addressid;
            }

            public void setAddressid(String addressid) {
                this.addressid = addressid;
            }

            public String getExpresssn() {
                return expresssn;
            }

            public void setExpresssn(String expresssn) {
                this.expresssn = expresssn;
            }

            public String getExpress() {
                return express;
            }

            public void setExpress(String express) {
                this.express = express;
            }

            public String getExpresscom() {
                return expresscom;
            }

            public void setExpresscom(String expresscom) {
                this.expresscom = expresscom;
            }

            public String getDispatchwkid() {
                return dispatchwkid;
            }

            public void setDispatchwkid(String dispatchwkid) {
                this.dispatchwkid = dispatchwkid;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }

            public String getIsrest() {
                return isrest;
            }

            public void setIsrest(String isrest) {
                this.isrest = isrest;
            }

            public String getRsreson() {
                return rsreson;
            }

            public void setRsreson(String rsreson) {
                this.rsreson = rsreson;
            }

            public String getSendtime() {
                return sendtime;
            }

            public void setSendtime(String sendtime) {
                this.sendtime = sendtime;
            }

            public String getConfirmedby() {
                return confirmedby;
            }

            public void setConfirmedby(String confirmedby) {
                this.confirmedby = confirmedby;
            }

            public String getHongbao() {
                return hongbao;
            }

            public void setHongbao(String hongbao) {
                this.hongbao = hongbao;
            }

            public String getZtdid() {
                return ztdid;
            }

            public void setZtdid(String ztdid) {
                this.ztdid = ztdid;
            }

            public String getTableid() {
                return tableid;
            }

            public void setTableid(String tableid) {
                this.tableid = tableid;
            }

            public String getZtdname() {
                return ztdname;
            }

            public void setZtdname(String ztdname) {
                this.ztdname = ztdname;
            }

            public String getZtpassword() {
                return ztpassword;
            }

            public void setZtpassword(String ztpassword) {
                this.ztpassword = ztpassword;
            }

            public String getZtdwkrid() {
                return ztdwkrid;
            }

            public void setZtdwkrid(String ztdwkrid) {
                this.ztdwkrid = ztdwkrid;
            }

            public String getDispatchchoice() {
                return dispatchchoice;
            }

            public void setDispatchchoice(String dispatchchoice) {
                this.dispatchchoice = dispatchchoice;
            }

            public String getDatenum() {
                return datenum;
            }

            public void setDatenum(String datenum) {
                this.datenum = datenum;
            }

            public String getDatetime() {
                return datetime;
            }

            public void setDatetime(String datetime) {
                this.datetime = datetime;
            }

            public String getReported() {
                return reported;
            }

            public void setReported(String reported) {
                this.reported = reported;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public List<GoodsBean> getGoods() {
                return goods;
            }

            public void setGoods(List<GoodsBean> goods) {
                this.goods = goods;
            }

            public static class UserBean {
                /**
                 * id : 364
                 * mobile :
                 * province :
                 * city :
                 * area : [现场交易]
                 * address : 北京市贞观国际
                 */

                private String id;
                private String mobile;
                private String province;
                private String city;
                private String area;
                private String address;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getArea() {
                    return area;
                }

                public void setArea(String area) {
                    this.area = area;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }
            }

            public static class GoodsBean {
                /**
                 * id : 49
                 * total : 1
                 * price : 11.00
                 * title : 凉瓜炒蛋饭
                 * thumb : http://ctest.greengod.cn/resource/attachment/images/2017/06/AL3Q5zmEOt3h6QNdXTq69z0z3pXhN9.jpg
                 * unit :
                 * goodssn : 342
                 * productsn : 72342
                 * marketprice : 11.00
                 * oid : 1105
                 * type : 0
                 * optionname : null
                 * optionid : 0
                 * commission : 0.55
                 */

                private String id;
                private String total;
                private String price;
                private String title;
                private String thumb;
                private String unit;
                private String goodssn;
                private String productsn;
                private String marketprice;
                private String oid;
                private String type;
                private Object optionname;
                private String optionid;
                private String commission;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getTotal() {
                    return total;
                }

                public void setTotal(String total) {
                    this.total = total;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getUnit() {
                    return unit;
                }

                public void setUnit(String unit) {
                    this.unit = unit;
                }

                public String getGoodssn() {
                    return goodssn;
                }

                public void setGoodssn(String goodssn) {
                    this.goodssn = goodssn;
                }

                public String getProductsn() {
                    return productsn;
                }

                public void setProductsn(String productsn) {
                    this.productsn = productsn;
                }

                public String getMarketprice() {
                    return marketprice;
                }

                public void setMarketprice(String marketprice) {
                    this.marketprice = marketprice;
                }

                public String getOid() {
                    return oid;
                }

                public void setOid(String oid) {
                    this.oid = oid;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public Object getOptionname() {
                    return optionname;
                }

                public void setOptionname(Object optionname) {
                    this.optionname = optionname;
                }

                public String getOptionid() {
                    return optionid;
                }

                public void setOptionid(String optionid) {
                    this.optionid = optionid;
                }

                public String getCommission() {
                    return commission;
                }

                public void setCommission(String commission) {
                    this.commission = commission;
                }
            }
        }

        public static class OrderownnerBean {
            /**
             * id : 13
             * weid : 1
             * realname : O2O云服务平台-赵向阳
             */

            private String id;
            private String weid;
            private String realname;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getWeid() {
                return weid;
            }

            public void setWeid(String weid) {
                this.weid = weid;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }
        }

        public static class SharememBean {
            /**
             * id : 3
             * from_user : oM4K9wCoiBwY3S13sK6TYole5JDQ
             * realname : 唐中林13662666768
             */

            private String id;
            private String from_user;
            private String realname;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getFrom_user() {
                return from_user;
            }

            public void setFrom_user(String from_user) {
                this.from_user = from_user;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }
        }

        public static class ShopownnerBean {
            /**
             * from_user : oM4K9wN1k6SmrAd5XnGszRfZ92wA
             */

            private String from_user;

            public String getFrom_user() {
                return from_user;
            }

            public void setFrom_user(String from_user) {
                this.from_user = from_user;
            }
        }

        public static class TableinfoBean {
            /**
             * id : 173
             * orderid : 372
             * dispatchchoice : 3
             * tableid : 123
             * datenum : 3
             * datetime_st : 1502337993
             * datetime_nd : 0
             * status : 1
             */

            private String id;
            private String orderid;
            private String dispatchchoice;
            private String tableid;
            private String datenum;
            private String datetime_st;
            private String datetime_nd;
            private String status;
            private String tablename;

            public String getTablename() {
                return tablename;
            }

            public void setTablename(String tablename) {
                this.tablename = tablename;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOrderid() {
                return orderid;
            }

            public void setOrderid(String orderid) {
                this.orderid = orderid;
            }

            public String getDispatchchoice() {
                return dispatchchoice;
            }

            public void setDispatchchoice(String dispatchchoice) {
                this.dispatchchoice = dispatchchoice;
            }

            public String getTableid() {
                return tableid;
            }

            public void setTableid(String tableid) {
                this.tableid = tableid;
            }

            public String getDatenum() {
                return datenum;
            }

            public void setDatenum(String datenum) {
                this.datenum = datenum;
            }

            public String getDatetime_st() {
                return datetime_st;
            }

            public void setDatetime_st(String datetime_st) {
                this.datetime_st = datetime_st;
            }

            public String getDatetime_nd() {
                return datetime_nd;
            }

            public void setDatetime_nd(String datetime_nd) {
                this.datetime_nd = datetime_nd;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
