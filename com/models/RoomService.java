package com.models;

import com.enums.OrderStatus;
import com.utils.MiscUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RoomService implements Model<RoomService> {
    private List<Order> orders = new ArrayList<>();
    private int roomServiceID;

    public RoomService(List<Order> orders) {
        this.orders = orders;
        roomServiceID = MiscUtils.generateID();
    };


    public List<Order> getOrders() {
        return this.orders;
    }

    public int getRoomServiceID() {
        return this.roomServiceID;
    }

    public void removeItemFromOrder(MenuItem menuitem) {
        this.orders.remove(menuitem);
    }

    @Override
    @SuppressWarnings("Depreciated")
    public String toString() {
        String finalString = "RoomServiceID : " + new Integer(roomServiceID).toString();
        finalString += "\nThe following is the full reciept: \n\nItem : Price\n\n";
        for (Order item : orders) {
            finalString += item.getName() + " : " + new Double(item.getPrice()).toString() + "\n";
        }
        return finalString;
    }
}
