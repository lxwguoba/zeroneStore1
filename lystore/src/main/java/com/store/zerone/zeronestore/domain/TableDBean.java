package com.store.zerone.zeronestore.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by on 2018/1/23 0023 15 34.
 * Author  LiuXingWen
 */

public class TableDBean implements Serializable {
    /**
     * status : 1
     * data : [{"id":"93","weid":"1","branchid":"1","roomid":"0","tablename":"柳州九号","maxseat":"12","minconsume":"0.00","reception_qr":"","using":"0","ydstatus":0,"time_st":0},{"id":"92","weid":"1","branchid":"1","roomid":"0","tablename":"柳州四号","maxseat":"12","minconsume":"0.00","reception_qr":"","using":"1","ydstatus":0,"time_st":0},{"id":"91","weid":"1","branchid":"1","roomid":"0","tablename":"柳州三号","maxseat":"12","minconsume":"0.00","reception_qr":"https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQHV8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyS3REQTBRN2JkaGoxMDAwMGcwN1YAAgRhp0laAwQAAAAA","using":"2","ydstatus":0,"time_st":0},{"id":"90","weid":"1","branchid":"1","roomid":"0","tablename":"柳州二号","maxseat":"12","minconsume":"0.00","reception_qr":"","using":"2","ydstatus":0,"time_st":0},{"id":"89","weid":"1","branchid":"1","roomid":"0","tablename":"柳州一号","maxseat":"12","minconsume":"0.00","reception_qr":"","using":"1","ydstatus":0,"time_st":0},{"id":"88","weid":"1","branchid":"1","roomid":"0","tablename":"柳州八号","maxseat":"12","minconsume":"0.00","reception_qr":"","using":"0","ydstatus":0,"time_st":0},{"id":"87","weid":"1","branchid":"1","roomid":"0","tablename":"柳州七号","maxseat":"12","minconsume":"0.00","reception_qr":"","using":"0","ydstatus":0,"time_st":0},{"id":"86","weid":"1","branchid":"1","roomid":"0","tablename":"柳州六号","maxseat":"12","minconsume":"0.00","reception_qr":"","using":"0","ydstatus":0,"time_st":0},{"id":"85","weid":"1","branchid":"1","roomid":"0","tablename":"柳州五号","maxseat":"12","minconsume":"0.00","reception_qr":"","using":"0","ydstatus":0,"time_st":0},{"id":"84","weid":"1","branchid":"1","roomid":"0","tablename":"柳州4号","maxseat":"12","minconsume":"0.00","reception_qr":"https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQFO8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyb3ZUazBTN2JkaGoxMDAwME0wN0IAAgQoscxZAwQAAAAA","using":"0","ydstatus":0,"time_st":0},{"id":"81","weid":"1","branchid":"1","roomid":"0","tablename":"柳州3号","maxseat":"12","minconsume":"0.00","reception_qr":"","using":"0","ydstatus":0,"time_st":0}]
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
    public static class DataBean  implements Serializable{
        /**
         * id : 93
         * weid : 1
         * branchid : 1
         * roomid : 0
         * tablename : 柳州九号
         * maxseat : 12
         * minconsume : 0.00
         * reception_qr :
         * using : 0
         * ydstatus : 0
         * time_st : 0
         */

        private String id;
        private String weid;
        private String branchid;
        private String roomid;
        private String tablename;
        private String maxseat;
        private String minconsume;
        private String reception_qr;
        private String using;
        private int ydstatus;
        private int time_st;

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

        public String getBranchid() {
            return branchid;
        }

        public void setBranchid(String branchid) {
            this.branchid = branchid;
        }

        public String getRoomid() {
            return roomid;
        }

        public void setRoomid(String roomid) {
            this.roomid = roomid;
        }

        public String getTablename() {
            return tablename;
        }

        public void setTablename(String tablename) {
            this.tablename = tablename;
        }

        public String getMaxseat() {
            return maxseat;
        }

        public void setMaxseat(String maxseat) {
            this.maxseat = maxseat;
        }

        public String getMinconsume() {
            return minconsume;
        }

        public void setMinconsume(String minconsume) {
            this.minconsume = minconsume;
        }

        public String getReception_qr() {
            return reception_qr;
        }

        public void setReception_qr(String reception_qr) {
            this.reception_qr = reception_qr;
        }

        public String getUsing() {
            return using;
        }

        public void setUsing(String using) {
            this.using = using;
        }

        public int getYdstatus() {
            return ydstatus;
        }

        public void setYdstatus(int ydstatus) {
            this.ydstatus = ydstatus;
        }

        public int getTime_st() {
            return time_st;
        }

        public void setTime_st(int time_st) {
            this.time_st = time_st;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", weid='" + weid + '\'' +
                    ", branchid='" + branchid + '\'' +
                    ", roomid='" + roomid + '\'' +
                    ", tablename='" + tablename + '\'' +
                    ", maxseat='" + maxseat + '\'' +
                    ", minconsume='" + minconsume + '\'' +
                    ", reception_qr='" + reception_qr + '\'' +
                    ", using='" + using + '\'' +
                    ", ydstatus=" + ydstatus +
                    ", time_st=" + time_st +
                    '}';
        }
    }
}
