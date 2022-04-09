package com.models;

import java.util.List;
import com.enums.OrderStatus;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class RoomService {
    private HashMap<MenuItem, OrderStatus> orders;
    private int orderID;
    public Menu menu;
    public RoomService(HashMap<MenuItem, OrderStatus> orders){
        this.orders = orders;
    };

    public RoomService(HashMap<MenuItem, OrderStatus> orders, int orderID) {}

    public HashMap<MenuItem, OrderStatus> getOrders() {
        return this.orders;
    }
    public int getOrderID() {
        return this.orderID;
    }
    public void removeItemFromOrder(MenuItem menuitem) {
        this.orders.remove(menuitem);
    }
}
