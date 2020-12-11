package com.ibox.state.service;

public enum EOrderStatus {

    ORDER_STATUS_FINAL(1, "终止状态");

    private int code;
    private String value;

    EOrderStatus(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
