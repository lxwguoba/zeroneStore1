package com.store.zerone.zeronestore.domain.options;

import java.io.Serializable;
import java.util.List;

/**
 * Created by on 2017/11/2 0002 16 27.
 * Author  LiuXingWen
 */

public class OptionsBean implements Serializable {
    private String  id;
    private String  weid;
    private String  title;
    private String  description;
    private String  displaytype;
    private String  content;
    private String  goodsid;
    private String  displayorder;
    private List<OptionsBeanItem> items;



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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplaytype() {
        return displaytype;
    }

    public void setDisplaytype(String displaytype) {
        this.displaytype = displaytype;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    public String getDisplayorder() {
        return displayorder;
    }

    public void setDisplayorder(String displayorder) {
        this.displayorder = displayorder;
    }

    public List<OptionsBeanItem> getItems() {
        return items;
    }

    public void setItems(List<OptionsBeanItem> items) {
        this.items = items;
    }
}
