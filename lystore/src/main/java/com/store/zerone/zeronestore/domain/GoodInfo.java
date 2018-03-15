package com.store.zerone.zeronestore.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dalong on 2016/12/27.
 * 商品详情的实体类
 */

public class GoodInfo implements Serializable{


    @Override
    public String toString() {
        return "GoodInfo{" +
                "goodsnum=" + goodsnum +
                ", goodslist=" + goodslist +
                '}';
    }

    /**
     * goodsnum : 4
     * goodslist : [{"id":"11","name":"面","gooslist":[{"id":"3013","title":"过桥线","marketprice":64,"thumb":"images/1/2017/10/201710111744484056.jpg","total":"970","hasoption":"0","yuanjia":"64.00","total_in_cart":0,"options":[{"optionid":0,"optionname":"无"}]},{"id":"3010","title":"草莓奶油冰淇淋","marketprice":20,"thumb":"images/2017/06/YHHf7H5YyTaY5464Fq7H9zhp0q44Uo.jpg","total":"983","hasoption":"0","yuanjia":"20.00","total_in_cart":0,"options":[{"optionid":0,"optionname":"无"}]},{"id":"3009","title":"墨西哥鸡肉卷十小可","marketprice":8,"thumb":"images/2017/06/Xy31zXxenGZ25ZXEM1Z5j5njyeUJJj.jpg","total":"111","hasoption":"0","yuanjia":"8.00","total_in_cart":0,"options":[{"optionid":0,"optionname":"无"}]},{"id":"44","title":"兰州拉面","marketprice":0.01,"thumb":"images/2017/06/R7B7pybXByDTlbPP78bpdY2tYr8Mrr.jpg","total":"9740","hasoption":"1","yuanjia":"0.01","total_in_cart":0,"options":[{"optionid":"1927","optionname":"超级辣"},{"optionid":"1928","optionname":"中辣"},{"optionid":"1929","optionname":"微辣"}]}]},{},{},{}]
     */

    private int goodsnum;
    private List<GoodslistBean> goodslist;

    public int getGoodsnum() {
        return goodsnum;
    }

    public void setGoodsnum(int goodsnum) {
        this.goodsnum = goodsnum;
    }

    public List<GoodslistBean> getGoodslist() {
        return goodslist;
    }

    public void setGoodslist(List<GoodslistBean> goodslist) {
        this.goodslist = goodslist;
    }

    public static class GoodslistBean implements Serializable {
        @Override
        public String toString() {
            return "GoodslistBean{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", gooslist=" + gooslist +
                    '}';
        }

        /**
         * id : 11
         * name : 面
         * gooslist : [{"id":"3013","title":"过桥线","marketprice":64,"thumb":"images/1/2017/10/201710111744484056.jpg","total":"970","hasoption":"0","yuanjia":"64.00","total_in_cart":0,"options":[{"optionid":0,"optionname":"无"}]},{"id":"3010","title":"草莓奶油冰淇淋","marketprice":20,"thumb":"images/2017/06/YHHf7H5YyTaY5464Fq7H9zhp0q44Uo.jpg","total":"983","hasoption":"0","yuanjia":"20.00","total_in_cart":0,"options":[{"optionid":0,"optionname":"无"}]},{"id":"3009","title":"墨西哥鸡肉卷十小可","marketprice":8,"thumb":"images/2017/06/Xy31zXxenGZ25ZXEM1Z5j5njyeUJJj.jpg","total":"111","hasoption":"0","yuanjia":"8.00","total_in_cart":0,"options":[{"optionid":0,"optionname":"无"}]},{"id":"44","title":"兰州拉面","marketprice":0.01,"thumb":"images/2017/06/R7B7pybXByDTlbPP78bpdY2tYr8Mrr.jpg","total":"9740","hasoption":"1","yuanjia":"0.01","total_in_cart":0,"options":[{"optionid":"1927","optionname":"超级辣"},{"optionid":"1928","optionname":"中辣"},{"optionid":"1929","optionname":"微辣"}]}]
         */

        private String id;
        private String name;
        private List<GooslistBean> gooslist;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<GooslistBean> getGooslist() {
            return gooslist;
        }

        public void setGooslist(List<GooslistBean> gooslist) {
            this.gooslist = gooslist;
        }

        public static class GooslistBean implements Serializable {
            @Override
            public String toString() {
                return "GooslistBean{" +
                        "id='" + id + '\'' +
                        ", title='" + title + '\'' +
                        ", marketprice=" + marketprice +
                        ", thumb='" + thumb + '\'' +
                        ", total='" + total + '\'' +
                        ", hasoption='" + hasoption + '\'' +
                        ", yuanjia='" + yuanjia + '\'' +
                        ", total_in_cart=" + total_in_cart +
                        ", options=" + options +
                        '}';
            }

            /**
             * id : 3013
             * title : 过桥线
             * marketprice : 64
             * thumb : images/1/2017/10/201710111744484056.jpg
             * total : 970
             * hasoption : 0
             * yuanjia : 64.00
             * total_in_cart : 0
             * options : [{"optionid":0,"optionname":"无"}]
             */

            private String id;
            private String title;
            private int marketprice;
            private String thumb;
            private String total;
            private String hasoption;
            private String yuanjia;
            private int total_in_cart;
            private List<OptionsBean> options;

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

            public int getMarketprice() {
                return marketprice;
            }

            public void setMarketprice(int marketprice) {
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

            public List<OptionsBean> getOptions() {
                return options;
            }

            public void setOptions(List<OptionsBean> options) {
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
    }
}
