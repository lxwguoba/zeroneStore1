package com.store.zerone.zeronestore.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/24.
 * 登录成功后的用户获得返回用户信息
 */

public class UserInfo implements Serializable{

    /**
     * session : 登陆时的session
     * loginstatus : 登陆状态
     * remark : no password
     */
    private String session;
    private int loginstatus;
    private String remark;

    public void setSession(String session) {
        this.session = session;
    }

    public void setLoginstatus(int loginstatus) {
        this.loginstatus = loginstatus;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSession() {
        return session;
    }

    public int getLoginstatus() {
        return loginstatus;
    }

    public String getRemark() {
        return remark;
    }

}
