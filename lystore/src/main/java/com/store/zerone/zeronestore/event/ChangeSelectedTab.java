package com.store.zerone.zeronestore.event;

/**
 * Created by dalong on 2016/12/27.
 */

public class ChangeSelectedTab {
    private String name;
    private int messageType;

    public ChangeSelectedTab(String name, int messageType) {
        this.name = name;
        this.messageType = messageType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }
}
