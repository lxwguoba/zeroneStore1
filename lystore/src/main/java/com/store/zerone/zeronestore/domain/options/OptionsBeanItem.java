package com.store.zerone.zeronestore.domain.options;

import java.io.Serializable;

/**
 * Created by on 2017/11/2 0002 16 30.
 * Author  LiuXingWen
 */

public class OptionsBeanItem implements Serializable{


    /**
     * id : 18
     * weid : 1
     * specid : 8
     * title : 夜色1
     * thumb :
     * show : 1
     * displayorder : 0
     */

    private String id;
    private String weid;
    private String specid;
    private String title;
    private String thumb;
    private String show;
    private String displayorder;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeid() {
        return weid;
    }

    public void setWeid(String weid) {
        this.weid = weid;
    }

    public String getSpecid() {
        return specid;
    }

    public void setSpecid(String specid) {
        this.specid = specid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getDisplayorder() {
        return displayorder;
    }

    public void setDisplayorder(String displayorder) {
        this.displayorder = displayorder;
    }

    @Override
    public String toString() {
        return "OptionsBeanItem{" +
                "id='" + id + '\'' +
                ", weid='" + weid + '\'' +
                ", specid='" + specid + '\'' +
                ", title='" + title + '\'' +
                ", thumb='" + thumb + '\'' +
                ", show='" + show + '\'' +
                ", displayorder='" + displayorder + '\'' +
                '}';
    }
}
