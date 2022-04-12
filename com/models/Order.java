package com.models;

import com.enums.OrderStatus;

public class Order extends MenuItem {

    private OrderStatus status;

    public Order(MenuItem item)
    {
        super(item.getName(), item.getDescription(), item.getPrice());
        this.status = OrderStatus.PREPARING;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return super.toString() + "  Order Status: " + status.inString;
    }
}
