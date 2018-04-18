package com.zerone.store.shopingtime.Bean.shoplistbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dalong on 2016/12/27.
 */

public class GoodsCategroyListBean implements Serializable {

    /**
     * status : 1
     * msg : 获取分类成功
     * data : {"categorylist":[{"id":1,"name":"面条","displayorder":0},{"id":8,"name":"盖浇饭","displayorder":0},{"id":10,"name":"早点","displayorder":0},{"id":17,"name":"手机","displayorder":0},{"id":26,"name":"内存","displayorder":0},{"id":14,"name":"火锅","displayorder":1},{"id":11,"name":"面条","displayorder":2},{"id":13,"name":"盖饭","displayorder":3},{"id":9,"name":"饮料","displayorder":4},{"id":12,"name":"米饭","displayorder":4}]}
     */

    private String status;
    private String msg;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<CategorylistBean> categorylist;

        public List<CategorylistBean> getCategorylist() {
            return categorylist;
        }

        public void setCategorylist(List<CategorylistBean> categorylist) {
            this.categorylist = categorylist;
        }

        public static class CategorylistBean {
            /**
             * id : 1
             * name : 面条
             * displayorder : 0
             */

            private int id;
            private String name;
            private int displayorder;

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

            public int getDisplayorder() {
                return displayorder;
            }

            public void setDisplayorder(int displayorder) {
                this.displayorder = displayorder;
            }
        }
    }
}
