package com.zerone.store.shopingtime.Bean.order;

import java.io.Serializable;
import java.util.List;

/**
 * Created by on 2018/4/8 0008 10 43.
 * Author  LiuXingWen
 */

public class SubmitDataBean implements Serializable {
    private List<SubmitShopBean> data;

    public SubmitDataBean(List<SubmitShopBean> data) {
        this.data = data;
    }

    public List<SubmitShopBean> getData() {
        return data;
    }

    public void setData(List<SubmitShopBean> data) {
        this.data = data;
    }

}
