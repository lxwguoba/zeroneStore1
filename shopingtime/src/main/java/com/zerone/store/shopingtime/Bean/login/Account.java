package com.zerone.store.shopingtime.Bean.login;

import java.io.Serializable;

/**
 * Created by on 2018/4/2 0002 13 18.
 * Author  LiuXingWen
 */

public class Account implements Serializable {
    private String account_name;
    private String account_pwd;

    public Account() {
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getAccount_pwd() {
        return account_pwd;
    }

    public void setAccount_pwd(String account_pwd) {
        this.account_pwd = account_pwd;
    }

}
