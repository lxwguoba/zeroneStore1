package com.store.zerone.zeronestore.event;

/**
 * Created by dalong on 2016/12/27.
 */

public class GoodsListEvent {
    public int[] buyNums;

    public GoodsListEvent(int[] buyNums) {
        this.buyNums =buyNums;
    }
}
