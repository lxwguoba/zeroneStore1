package com.store.zerone.zeronestore.domain;

import java.io.Serializable;

/**
 * Created by on 2018/1/20 0020 09 54.
 * Author  LiuXingWen
 */

public class SessionBean implements Serializable {
    private String s_id;
    private  String s_value;

    public SessionBean() {
    }

    public SessionBean(String s_id, String s_value) {
        this.s_id = s_id;
        this.s_value = s_value;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getS_value() {
        return s_value;
    }

    public void setS_value(String s_value) {
        this.s_value = s_value;
    }
}
