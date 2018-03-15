package com.store.zerone.zeronestore.domain;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/10.
 */

public class Worker implements Serializable {
    /**
     * workerid : 39   会员id
     * reception_qr :   现场签入
     * name : 唐中林13662666768   店员名称
     */

    private String  icon_thumb;
    private String workerid;
    private String reception_qr;
    private String name;

    public String getIcon_thumb() {
        return icon_thumb;
    }

    public void setIcon_thumb(String icon_thumb) {
        this.icon_thumb = icon_thumb;
    }

    public String getWorkerid() {
        return workerid;
    }

    public void setWorkerid(String workerid) {
        this.workerid = workerid;
    }

    public String getReception_qr() {
        return reception_qr;
    }

    public void setReception_qr(String reception_qr) {
        this.reception_qr = reception_qr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "workerid='" + workerid + '\'' +
                ", reception_qr='" + reception_qr + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
