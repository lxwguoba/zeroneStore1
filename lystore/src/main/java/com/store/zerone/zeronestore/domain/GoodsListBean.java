package com.store.zerone.zeronestore.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dalong on 2016/12/27.
 */

public class GoodsListBean implements Serializable{


    private List<DataEntity> data;

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity {
        @Override
        public String toString() {
            return "DataEntity{" +
                    "goodscatrgory=" + goodscatrgory +
                    '}';
        }

        private GoodscatrgoryEntity goodscatrgory;

        public GoodscatrgoryEntity getGoodscatrgory() {
            return goodscatrgory;
        }

        public void setGoodscatrgory(GoodscatrgoryEntity goodscatrgory) {
            this.goodscatrgory = goodscatrgory;
        }

        public static class GoodscatrgoryEntity implements Serializable {
            @Override
            public String toString() {
                return "GoodscatrgoryEntity{" +
                        "c_id=" + c_id +
                        ", name='" + name + '\'' +
                        ", bugNum=" + bugNum +
                        ", goodsitem=" + goodsitem +
                        '}';
            }

            /**
             * goodsitem : [{"name":"苹果","price":10,"introduce":"苹果好吃啊，很甜！","moreStandard":false},{"name":"香蕉","price":10,"introduce":"香蕉好吃啊，又大很甜！","moreStandard":false},{"name":"橘子","price":10,"introduce":"橘子非常好吃啊，很甜！","moreStandard":false},{"name":"榴莲","price":10,"introduce":"我对象喜欢吃榴莲！","moreStandard":false},{"name":"桃子","price":10,"introduce":"大龙家的桃子就是好吃！","moreStandard":false},{"name":"橘子","price":10,"introduce":"橘子非常好吃啊，很甜！","moreStandard":false},{"name":"梨","price":10,"introduce":"梨非常好吃啊，很甜！","moreStandard":false}]
             * name : 水果
             */

            private int c_id;
            private String name;
            private int bugNum;
            private List<GoodsitemEntity> goodsitem;

            public int getC_id() {
                return c_id;
            }

            public void setC_id(int c_id) {
                this.c_id = c_id;
            }

            public String getName() {
                return name;
            }

            public int getBugNum() {
                return bugNum;
            }

            public void setBugNum(int bugNum) {
                this.bugNum = bugNum;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<GoodsitemEntity> getGoodsitem() {
                return goodsitem;
            }

            public void setGoodsitem(List<GoodsitemEntity> goodsitem) {
                this.goodsitem = goodsitem;
            }

            public static class GoodsitemEntity implements Serializable {
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

                //这个是实体类的id
                private  int  gb_id;
                //这个是商品id
                private String id;
                private String title;
                private String marketprice;
                private String thumb;
                private String total;
                private String hasoption;
                private String yuanjia;
                private int total_in_cart;
                private List<OptionsBean> options;

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

                public List<OptionsBean> getOptions() {
                    return options;
                }

                public void setOptions(List<OptionsBean> options) {
                    this.options = options;
                }

                public static class OptionsBean implements Serializable {
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
}
