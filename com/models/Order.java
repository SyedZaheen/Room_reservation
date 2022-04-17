package com.models;

import com.enums.OrderStatus;

/**
 * Represents a room service order, which extends from menu item.
 * @author DSF 1 Group 1
 *
 */
public class Order extends MenuItem {

    private OrderStatus status;

    /**
     * Creates an Order object with a given menu item object.
     * @param item
     */
    public Order(MenuItem item)
    {
        super(item.getName(), item.getDescription(), item.getPrice());
        this.status = OrderStatus.PREPARING;
    }
    
    /**
     * Gets this Order's order status.
     * @return This Order's order status.
     */

    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Sets this Orders's order status.
     * @return void
     */
    
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return super.toString() + "  Order Status: " + status.inString;
    }
}
