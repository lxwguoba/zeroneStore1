package com.store.zerone.zeronestore.domain;

import java.io.Serializable;

/**
 * 分店的实体类
 * Created by Administrator on 2017/6/26.
 */

public class Branch implements Serializable {
    /**
     * thumb : http://catering.dz-ck.com/resource/attachment/images/1/2017/06/fDZTe9dsKrUK6dZnIBbR9bTKEPUrPd.jpg
     * ztdname : 分店店名称
     * id : 4
     */
    //分店图片
    private String thumb;
    //分店名称
    private String ztdname;
    //分店id
    private String id;

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public void setZtdname(String ztdname) {
        this.ztdname = ztdname;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThumb() {
        return thumb;
    }

    public String getZtdname() {
        return ztdname;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "thumb='" + thumb + '\'' +
                ", ztdname='" + ztdname + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
