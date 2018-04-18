package com.zerone.store.shopingtime.Utils.printutils;

import com.zerone.store.shopingtime.Bean.print.PrintBean;
import com.zerone.store.shopingtime.Bean.print.TableItem;

import java.util.LinkedList;

/**
 * Created by on 2018/4/9 0009 10 21.
 * Author  LiuXingWen
 */

public class PrintUtils {

    public static void print(String name, PrintBean pb) {
        //-----------------------------最大分割线-----------------------
        LinkedList<TableItem> flinelist = new LinkedList<>();
        TableItem fline = new TableItem();
        String[] flinecon = {"*******************", "", ""};
        int[] flinealt = {0, 2, 2};
        int[] flinewid = new int[]{1, 0, 0};
        fline.setText(flinecon);
        fline.setAlign(flinealt);
        fline.setWidth(flinewid);
        flinelist.add(fline);

        //-------------------标题的打印------------------------
        LinkedList<TableItem> head = new LinkedList<>();
        TableItem ti01 = new TableItem();
        String[] headti01 = {name, "", ""};
        int[] headalt = {0, 2, 2};
        int[] headwid = new int[]{1, 0, 0};
        ti01.setText(headti01);
        ti01.setAlign(headalt);
        ti01.setWidth(headwid);
        //-----------分割线-------------
        TableItem ti02 = new TableItem();
        String[] headti02 = {"*******************", "", ""};
        int[] headalt02 = {0, 2, 2};
        int[] headwid02 = new int[]{1, 0, 0};
        ti02.setText(headti02);
        ti02.setAlign(headalt02);
        ti02.setWidth(headwid02);
        //------------------------
        TableItem ti03 = new TableItem();
        String[] headti03 = {"", "现场订单", ""};
        int[] headalt03 = {1, 1, 2};
        int[] headwid03 = new int[]{0, 4, 0};
        ti03.setText(headti03);
        ti03.setAlign(headalt03);
        ti03.setWidth(headwid03);

        TableItem ti05 = new TableItem();
        String[] headti05 = {"", "已付款", ""};
        int[] headalt05 = {1, 1, 2};
        int[] headwid05 = new int[]{0, 4, 0};
        ti05.setText(headti05);
        ti05.setAlign(headalt05);
        ti05.setWidth(headwid05);
        //----------------------------------
        head.add(ti01);
        head.add(ti02);
        head.add(ti03);
        head.add(ti02);
        head.add(ti05);
        head.add(ti02);
        //-------------------标题的打印------------------------
        LinkedList<TableItem> title = new LinkedList<>();
        TableItem t0 = new TableItem();
        TableItem t1 = new TableItem();
        String[] st = {"商品名称", "价格", "数量"};
        int[] alt = {0, 2, 2};
        int[] wid = new int[]{3, 2, 2};
        String[] t11 = {"", "---------------------", ""};
        int[] alt1 = {0, 2, 2};
        int[] wid1 = new int[]{0, 1, 0};
        t1.setAlign(alt);
        t1.setText(t11);
        t1.setWidth(wid1);
        t0.setAlign(alt1);
        t0.setText(st);
        t0.setWidth(wid);
        title.add(t1);
        title.add(t0);
        title.add(t1);
        LinkedList<TableItem> datalist = new LinkedList<>();
        for (int i = 0; i < pb.getList().size(); i++) {
            TableItem ti = new TableItem();
            String[] str = {pb.getList().get(i).getGoodsname(), "￥" + pb.getList().get(i).getGprice(), "x" + pb.getList().get(i).getGcount()};
            ti.setText(str);
            int[] al = {0, 0, 2};
            ti.setAlign(al);
            int[] width = new int[]{3, 2, 1};
            ti.setWidth(width);
            datalist.add(ti);
//            Log.i("ULRL",pb.getList().get(i).getOptions());
//            if (!"null".equals(pb.getList().get(i).getOptions())){
//                TableItem  ti0 = new TableItem();
//                String[] str0={"规格：",pb.getList().get(i).getOptions(),""};
//                ti0.setText(str0);
//                int[] al0={0,0,2};
//                ti0.setAlign(al0);
//                int[] width0 = new int[]{2,5,0};
//                ti0.setWidth(width0);
//                datalist.add(ti0);
//            }
        }
        TableItem tcontent = new TableItem();
        String[] tcon = {"", "-------------------------", ""};
        int[] altcon = {0, 1, 2};
        int[] widcon = new int[]{0, 1, 0};
        tcontent.setText(tcon);
        tcontent.setAlign(altcon);
        tcontent.setWidth(widcon);
        datalist.add(tcontent);

        LinkedList<TableItem> beizhulist = new LinkedList<>();
        TableItem beizhulistBean = new TableItem();
        String[] beizhuBeanCont = {"备注", pb.getRemark(), ""};
        int[] beizhuBeanalt = {0, 0, 2};
        int[] beizhuBeanwide = new int[]{1, 3, 0};
        beizhulistBean.setText(beizhuBeanCont);
        beizhulistBean.setAlign(beizhuBeanalt);
        beizhulistBean.setWidth(beizhuBeanwide);
        beizhulist.add(beizhulistBean);
        //------------------------底部下单时间和订单编号-----------------------------
        LinkedList<TableItem> orderprint = new LinkedList<>();
        TableItem orderprintBean = new TableItem();
        String[] orderprintBeanCont = {"订单编号:", pb.getOrdersn(), ""};
        int[] orderprintBeanalt = {0, 0, 2};
        int[] orderprintBeanwide = new int[]{1, 2, 0};
        orderprintBean.setText(orderprintBeanCont);
        orderprintBean.setAlign(orderprintBeanalt);
        orderprintBean.setWidth(orderprintBeanwide);
        TableItem orderprintBean01 = new TableItem();

        String[] orderprintBeanCont01 = {"下单时间:", pb.getCreateTime(), ""};
        int[] orderprintBeanalt01 = {0, 0, 0};
        int[] orderprintBeanwide01 = new int[]{1, 2, 0};
        orderprintBean01.setText(orderprintBeanCont01);
        orderprintBean01.setAlign(orderprintBeanalt01);
        orderprintBean01.setWidth(orderprintBeanwide01);
        orderprint.add(orderprintBean);
        orderprint.add(orderprintBean01);
        //------------------------底部下单时间和订单编号-----------------------------
        //---------------------------金额--餐位费--------------------------------------
        LinkedList<TableItem> moneylist = new LinkedList<>();
        TableItem monTi = new TableItem();
        String[] monTiCon = {"总计：", "", "￥" + pb.getPmoney()};
        int[] monTialt = {0, 2, 2};
        int[] monTiwid = {1, 0, 2};
        monTi.setText(monTiCon);
        monTi.setAlign(monTialt);
        monTi.setWidth(monTiwid);
        moneylist.add(monTi);

        //---------------------------金额----------------------------------
        AidlUtil.getInstance().printTable(head, 40, true);
        AidlUtil.getInstance().printTable(title, 36, true);
        AidlUtil.getInstance().printTable(datalist, 30, false);
        AidlUtil.getInstance().printTable(orderprint, 26, false);
        AidlUtil.getInstance().printTable(beizhulist, 30, false);
        AidlUtil.getInstance().printTable(flinelist, 40, false);
        AidlUtil.getInstance().printTable(moneylist, 42, true);
        AidlUtil.getInstance().printTable(flinelist, 40, false);
        AidlUtil.getInstance().printQr("www.01nnt.com", 10, 2);
        AidlUtil.getInstance().print3Line();
    }
}
