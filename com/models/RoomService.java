package com.models;

import com.utils.MiscUtils;

import java.util.ArrayList;

import java.util.List;

/**
 * Represents a Room Service model.
 * @author DSF 1 Group 1
 *
 */

public class RoomService implements Model<RoomService> {
    private List<Order> orders = new ArrayList<>();
    private int roomServiceID;
    
    /**
     * Creates a room service object with a given list of Order objects, and sets a unique room service ID.
     * @param orders List of Order objects.
     */

    public RoomService(List<Order> orders) {
        this.orders = orders;
        this.roomServiceID = MiscUtils.generateID();
    };
    
	/**
	 * Gets this room service's order list.
	 * @return A list of this room service's Orders.
	 */

    public List<Order> getOrders() {
        return this.orders;
    }
    
    /**
	 * Gets this room service's roomserviceID.
	 * @return This a list of this room service's roomserviceID.
	 */

    public int getRoomServiceID() {
        return this.roomServiceID;
    }
    
    /**
	 * Removes a menu item from this room service's list of orders.
	 * @return void.
	 */

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
