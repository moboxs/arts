package com.ibox.state.service;

public class OrderStateManager {

    public OrderStateManager() {}

    public int handleEvent(final String orderId, EOrderStatus eOrderStatus, final int status) {

        if (isFinalStatus(status)) {
            throw new IllegalArgumentException("can't process final status order.");
        }

        OprateOrderProcessor processor = new OprateOrderProcessor();
        int result = processor.handleEvent(status, eOrderStatus);

        return result;

    }

    public boolean isFinalStatus(int status) {
        return EOrderStatus.ORDER_STATUS_FINAL.getCode() == status;
    }
}
