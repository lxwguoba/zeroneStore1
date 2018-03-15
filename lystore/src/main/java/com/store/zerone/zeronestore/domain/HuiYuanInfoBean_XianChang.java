package com.store.zerone.zeronestore.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 *
 */

public class HuiYuanInfoBean_XianChang implements Serializable {

    /**
     * result : 1
     * memberlist : [{"id":"52","weid":"3","branchid":"0","shareid":"0","from_user":"osHXB0TKha2FY9pKUr_lddx93hnw","realname":"倾听雪落","mobile":"","myqq":"","commission":"0.00","zhifu":"0.00","content":null,"createtime":"1498803732","flag":"0","levelid":"0","flagtime":"0","credit2":"0","status":"1","clickcount":"0","mbbinded":"0","birthdate":"0000-00-00","remark":"","isworking":"0","credit1":"6","avatar":"http://wx.qlogo.cn/mmopen/ibH3DRwic4UmCa34suMicNicyia5EERlknibHZIn13qB8Jo7spu3hkBUichT8qDcNy0V0JiclRumXhURIB59hYzEJZ7HCIEUnNO5FWFt/0","receptionid":"39","title":""}]
     */

    private int result;
    private List<MemberlistBean> memberlist;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<MemberlistBean> getMemberlist() {
        return memberlist;
    }

    public void setMemberlist(List<MemberlistBean> memberlist) {
        this.memberlist = memberlist;
    }

    public static class MemberlistBean {
        /**
         * id : 52
         * weid : 3
         * branchid : 0
         * shareid : 0
         * from_user : osHXB0TKha2FY9pKUr_lddx93hnw
         * realname : 倾听雪落
         * mobile :
         * myqq :
         * commission : 0.00
         * zhifu : 0.00
         * content : null
         * createtime : 1498803732
         * flag : 0
         * levelid : 0
         * flagtime : 0
         * credit2 : 0
         * status : 1
         * clickcount : 0
         * mbbinded : 0
         * birthdate : 0000-00-00
         * remark :
         * isworking : 0
         * credit1 : 6
         * avatar : http://wx.qlogo.cn/mmopen/ibH3DRwic4UmCa34suMicNicyia5EERlknibHZIn13qB8Jo7spu3hkBUichT8qDcNy0V0JiclRumXhURIB59hYzEJZ7HCIEUnNO5FWFt/0
         * receptionid : 39
         * title :
         */

        private String id;
        private String weid;
        private String branchid;
        private String shareid;
        private String from_user;
        private String realname;
        private String mobile;
        private String myqq;
        private String commission;
        private String zhifu;
        private Object content;
        private String createtime;
        private String flag;
        private String levelid;
        private String flagtime;
        private String credit2;
        private String status;
        private String clickcount;
        private String mbbinded;
        private String birthdate;
        private String remark;
        private String isworking;
        private String credit1;
        private String avatar;
        private String receptionid;
        private String title;

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

        public String getShareid() {
            return shareid;
        }

        public void setShareid(String shareid) {
            this.shareid = shareid;
        }

        public String getFrom_user() {
            return from_user;
        }

        public void setFrom_user(String from_user) {
            this.from_user = from_user;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMyqq() {
            return myqq;
        }

        public void setMyqq(String myqq) {
            this.myqq = myqq;
        }

        public String getCommission() {
            return commission;
        }

        public void setCommission(String commission) {
            this.commission = commission;
        }

        public String getZhifu() {
            return zhifu;
        }

        public void setZhifu(String zhifu) {
            this.zhifu = zhifu;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getLevelid() {
            return levelid;
        }

        public void setLevelid(String levelid) {
            this.levelid = levelid;
        }

        public String getFlagtime() {
            return flagtime;
        }

        public void setFlagtime(String flagtime) {
            this.flagtime = flagtime;
        }

        public String getCredit2() {
            return credit2;
        }

        public void setCredit2(String credit2) {
            this.credit2 = credit2;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getClickcount() {
            return clickcount;
        }

        public void setClickcount(String clickcount) {
            this.clickcount = clickcount;
        }

        public String getMbbinded() {
            return mbbinded;
        }

        public void setMbbinded(String mbbinded) {
            this.mbbinded = mbbinded;
        }

        public String getBirthdate() {
            return birthdate;
        }

        public void setBirthdate(String birthdate) {
            this.birthdate = birthdate;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getIsworking() {
            return isworking;
        }

        public void setIsworking(String isworking) {
            this.isworking = isworking;
        }

        public String getCredit1() {
            return credit1;
        }

        public void setCredit1(String credit1) {
            this.credit1 = credit1;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getReceptionid() {
            return receptionid;
        }

        public void setReceptionid(String receptionid) {
            this.receptionid = receptionid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
