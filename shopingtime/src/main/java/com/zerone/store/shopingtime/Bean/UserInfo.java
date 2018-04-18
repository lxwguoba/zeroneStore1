package com.zerone.store.shopingtime.Bean;

import java.io.Serializable;

/**
 * Created by on 2018/3/31 0031 16 38.
 * Author  LiuXingWen
 * <p>
 * 用户登录成功后保留用户的信息
 */

public class UserInfo implements Serializable {

    String account_id;
    String account;
    String organization_id;
    String uuid;

    public UserInfo() {
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(String organization_id) {
        this.organization_id = organization_id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "account_id='" + account_id + '\'' +
                ", account='" + account + '\'' +
                ", organization_id='" + organization_id + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
