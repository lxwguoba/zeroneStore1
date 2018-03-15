package com.store.zerone.zeronestore.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by on 2018/1/23 0023 14 22.
 * Author  LiuXingWen
 */

public class TableCatringBean implements Serializable {

    /**
     * status : 1
     * data : [{"id":0,"weid":"1","branchid":3,"roomname":"大厅"},{"id":"31","weid":"1","branchid":"3","roomname":"包厢01"},{"id":"32","weid":"1","branchid":"3","roomname":"包厢02"},{"id":"33","weid":"1","branchid":"3","roomname":"包厢03"},{"id":"34","weid":"1","branchid":"3","roomname":"包厢04"},{"id":"35","weid":"1","branchid":"3","roomname":"包厢05"}]
     */

    private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }
    public static class DataBean {
        /**
         * id : 0
         * weid : 1
         * branchid : 3
         * roomname : 大厅
         */

        private int id;
        private String weid;
        private int branchid;
        private String roomname;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getWeid() {
            return weid;
        }

        public void setWeid(String weid) {
            this.weid = weid;
        }

        public int getBranchid() {
            return branchid;
        }

        public void setBranchid(int branchid) {
            this.branchid = branchid;
        }

        public String getRoomname() {
            return roomname;
        }

        public void setRoomname(String roomname) {
            this.roomname = roomname;
        }
    }
}
