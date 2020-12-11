package com.ibox.state.service;

public abstract class AbstractOrderProcessor {
    int status;

    public abstract int handleEvent(int orderStatus, EOrderStatus eOrderStatus);

}
