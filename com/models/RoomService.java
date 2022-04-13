package com.models;

import com.utils.MiscUtils;

import java.util.ArrayList;

import java.util.List;

public class RoomService implements Model<RoomService> {
    private List<Order> orders = new ArrayList<>();
    private Integer roomServiceID;

    public RoomService(List<Order> orders) {
        this.orders = orders;
        roomServiceID = MiscUtils.generateID();
    };


    public List<Order> getOrders() {
        return this.orders;
    }

    public int getRoomServiceID() {
        return (int) this.roomServiceID;
    }

    public void removeItemFromOrder(MenuItem menuitem) {
        this.orders.remove(menuitem);
    }

    @Override
    @SuppressWarnings("Depreciated")
    public String toString() {
        String finalString = "RoomServiceID : " + roomServiceID.toString();
        finalString += "\nThe following is the full reciept: \n\nItem : Price\n\n";
        for (Order item : orders) {
            finalString += item.getName() + " : " + item.getPrice().toString() + "\n";
        }
        return finalString;
    }
}
