package com.store.zerone.zeronestore.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/8/5.
 */

public class DaiFuKuanOrderBean implements Serializable {


    /**
     * status : 1
     * data : {"list":[{"id":"247","ordersn":"17072805580532","stockminus":null,"weid":"3","shareid":"41","from_user":"5sanke","price":"680","goodsprice":"680.00","dispatchprice":"0.00","fetchnextday":"0","transid":"0","status":"0","sendtype":"3","paytype":"4","goodstype":"0","remark":"","addressid":"235","expresssn":"","express":"","expresscom":"","dispatchwkid":"0","createtime":"1501232915","updatetime":"0","isrest":"0","rsreson":"","sendtime":"0","confirmedby":"0","hongbao":"0.00","ztdid":"5","tableid":"0","ztdname":"垫江县玉锅宴食府","ztpassword":"","ztdwkrid":"0","dispatchchoice":"3","datednum":"0","datedtime":"0","reported":"0","discount":"0.00","commission":"68.00","realname":"垫江县玉锅宴食府的散客","mobile":""}],"pager":{"total":"1","pindex":1,"psize":20},"alltotal":680,"totalyejin":680}
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

    public static class DataBean implements Serializable {
        /**
         * list : [{"id":"247","ordersn":"17072805580532","stockminus":null,"weid":"3","shareid":"41","from_user":"5sanke","price":"680","goodsprice":"680.00","dispatchprice":"0.00","fetchnextday":"0","transid":"0","status":"0","sendtype":"3","paytype":"4","goodstype":"0","remark":"","addressid":"235","expresssn":"","express":"","expresscom":"","dispatchwkid":"0","createtime":"1501232915","updatetime":"0","isrest":"0","rsreson":"","sendtime":"0","confirmedby":"0","hongbao":"0.00","ztdid":"5","tableid":"0","ztdname":"垫江县玉锅宴食府","ztpassword":"","ztdwkrid":"0","dispatchchoice":"3","datednum":"0","datedtime":"0","reported":"0","discount":"0.00","commission":"68.00","realname":"垫江县玉锅宴食府的散客","mobile":""}]
         * pager : {"total":"1","pindex":1,"psize":20}
         * alltotal : 680
         * totalyejin : 680
         */

        private PagerBean pager;
        private int alltotal;
        private int totalyejin;
        private List<ListBean> list;

        public PagerBean getPager() {
            return pager;
        }

        public void setPager(PagerBean pager) {
            this.pager = pager;
        }

        public int getAlltotal() {
            return alltotal;
        }

        public void setAlltotal(int alltotal) {
            this.alltotal = alltotal;
        }

        public int getTotalyejin() {
            return totalyejin;
        }

        public void setTotalyejin(int totalyejin) {
            this.totalyejin = totalyejin;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class PagerBean implements Serializable {
            /**
             * total : 1
             * pindex : 1
             * psize : 20
             */

            private String total;
            private int pindex;
            private int psize;

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public int getPindex() {
                return pindex;
            }

            public void setPindex(int pindex) {
                this.pindex = pindex;
            }

            public int getPsize() {
                return psize;
            }

            public void setPsize(int psize) {
                this.psize = psize;
            }
        }

        public static class ListBean implements Serializable {
            @Override
            public String toString() {
                return "ListBean{" +
                        "seat_fee='" + seat_fee + '\'' +
                        ", id='" + id + '\'' +
                        ", ordersn='" + ordersn + '\'' +
                        ", stockminus=" + stockminus +
                        ", weid='" + weid + '\'' +
                        ", shareid='" + shareid + '\'' +
                        ", from_user='" + from_user + '\'' +
                        ", price='" + price + '\'' +
                        ", goodsprice='" + goodsprice + '\'' +
                        ", dispatchprice='" + dispatchprice + '\'' +
                        ", fetchnextday='" + fetchnextday + '\'' +
                        ", transid='" + transid + '\'' +
                        ", status='" + status + '\'' +
                        ", sendtype='" + sendtype + '\'' +
                        ", paytype='" + paytype + '\'' +
                        ", goodstype='" + goodstype + '\'' +
                        ", remark='" + remark + '\'' +
                        ", addressid='" + addressid + '\'' +
                        ", expresssn='" + expresssn + '\'' +
                        ", express='" + express + '\'' +
                        ", expresscom='" + expresscom + '\'' +
                        ", dispatchwkid='" + dispatchwkid + '\'' +
                        ", createtime='" + createtime + '\'' +
                        ", updatetime='" + updatetime + '\'' +
                        ", isrest='" + isrest + '\'' +
                        ", rsreson='" + rsreson + '\'' +
                        ", sendtime='" + sendtime + '\'' +
                        ", confirmedby='" + confirmedby + '\'' +
                        ", hongbao='" + hongbao + '\'' +
                        ", ztdid='" + ztdid + '\'' +
                        ", tableid='" + tableid + '\'' +
                        ", ztdname='" + ztdname + '\'' +
                        ", ztpassword='" + ztpassword + '\'' +
                        ", ztdwkrid='" + ztdwkrid + '\'' +
                        ", dispatchchoice='" + dispatchchoice + '\'' +
                        ", datednum='" + datednum + '\'' +
                        ", datedtime='" + datedtime + '\'' +
                        ", reported='" + reported + '\'' +
                        ", discount='" + discount + '\'' +
                        ", commission='" + commission + '\'' +
                        ", realname='" + realname + '\'' +
                        ", mobile='" + mobile + '\'' +
                        ", tableName='" + tableName + '\'' +
                        ", rp_times='" + rp_times + '\'' +
                        ", kp_times='" + kp_times + '\'' +
                        ", print_order='" + print_order + '\'' +
                        '}';
            }

            /**
             * id : 247
             * ordersn : 17072805580532
             * stockminus : null
             * weid : 3
             * shareid : 41
             * from_user : 5sanke
             * price : 680
             * goodsprice : 680.00
             * dispatchprice : 0.00
             * fetchnextday : 0
             * transid : 0
             * status : 0
             * sendtype : 3
             * paytype : 4
             * goodstype : 0
             * remark :
             * addressid : 235
             * expresssn :
             * express :
             * expresscom :
             * dispatchwkid : 0
             * createtime : 1501232915
             * updatetime : 0
             * isrest : 0
             * rsreson :
             * sendtime : 0
             * confirmedby : 0
             * hongbao : 0.00
             * ztdid : 5
             * tableid : 0
             * ztdname : 垫江县玉锅宴食府
             * ztpassword :
             * ztdwkrid : 0
             * dispatchchoice : 3
             * datednum : 0
             * datedtime : 0
             * reported : 0
             * discount : 0.00
             * commission : 68.00
             * realname : 垫江县玉锅宴食府的散客
             * mobile :
             */

            private  String seat_fee;
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
            private String datednum;
            private String datedtime;
            private String reported;
            private String discount;
            private String commission;
            private String realname;
            private String mobile;

            private String tableName;

            private String rp_times;

            private String kp_times;

            private String print_order;

            //优惠折扣
            private  String sale;

            public String getSale() {
                return sale;
            }

            public void setSale(String sale) {
                this.sale = sale;
            }

            public String getSeat_fee() {
                return seat_fee;
            }

            public void setSeat_fee(String seat_fee) {
                this.seat_fee = seat_fee;
            }

            public String getTableName() {
                return tableName;
            }

            public void setTableName(String tableName) {
                this.tableName = tableName;
            }

            public String getRp_times() {
                return rp_times;
            }

            public void setRp_times(String rp_times) {
                this.rp_times = rp_times;
            }

            public String getKp_times() {
                return kp_times;
            }

            public void setKp_times(String kp_times) {
                this.kp_times = kp_times;
            }

            public String getPrint_order() {
                return print_order;
            }

            public void setPrint_order(String print_order) {
                this.print_order = print_order;
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

            public String getDatednum() {
                return datednum;
            }

            public void setDatednum(String datednum) {
                this.datednum = datednum;
            }

            public String getDatedtime() {
                return datedtime;
            }

            public void setDatedtime(String datedtime) {
                this.datedtime = datedtime;
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

            public String getCommission() {
                return commission;
            }

            public void setCommission(String commission) {
                this.commission = commission;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }
        }
    }
}
