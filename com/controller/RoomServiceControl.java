package com.controller;

import java.util.ArrayList;
import java.util.HashMap;

import com.models.Menu;
import com.models.MenuItem;
import com.models.RoomService;
import com.utils.MiscUtils;
import com.views.UserInputViews;
import com.db.roomserviceDB.RoomServiceDB;
import com.enums.OrderStatus;

public class RoomServiceControl implements CreatorController<RoomService> {

    private Menu menu = new Menu();

    @Override
    public RoomService manageCreateEntry() {
        int orderID;
        HashMap<MenuItem, OrderStatus> orders = new HashMap<>();
        ArrayList<MenuItem> takenOrders = takeOrders();
        for (MenuItem menuitem : takenOrders) {
            orders.put(menuitem, OrderStatus.PREPARING);
        }

        boolean success = false;
        do {
            orderID = MiscUtils.generateID();
            for (RoomService rs : new RoomServiceDB().findAllEntries()) {
                if (rs != null) {
                    if (orderID == rs.getOrderID()) {
                        break;
                    }
                }
            }
            success = true;
        } while (success == false);
        RoomService roomservice = new RoomService(orders, orderID);

        return roomservice;
    }

    public ArrayList<MenuItem> takeOrders() {
        int choice;
        boolean success;
        ArrayList<MenuItem> orderlist = new ArrayList<>();
        String finalOrderList = "";
        do {
            choice = UserInputViews.getUserChoice(menu.getAllItemsInString());
            if (choice == -1)
                break;
            orderlist.add(menu.items.get(choice - 1));
            System.out.println("Key in -1 to exit");
        } while (choice != -1);

        for (MenuItem menuItem : orderlist) {
            finalOrderList += menuItem.toString() + "\n";
        }
        
        success = UserInputViews.<String>userDoubleConfirmDetails(finalOrderList);
        if (!success)
            orderlist = takeOrders();
        return orderlist;
    }

    public void viewOrder(ArrayList<MenuItem> orderlist) {
        if (orderlist.isEmpty()) {
            System.out.println("Order list is empty!");
        }
        for (MenuItem menuItem : orderlist) {
            System.out.println(menuItem);
        }
    }

    public static void printOrder() {
        int count = 1;
        int orderID = UserInputViews.<Integer>getEachFieldFromUser(
                "Enter Order ID: ", "Please enter a valid ID", i -> true, "Integer");

        System.out.println("Orders: ");
        for (RoomService eachRoomService : new RoomServiceDB().findAllEntries()) {
            if (orderID == eachRoomService.getOrderID()) {
                for (MenuItem menuitem : eachRoomService.getOrders().keySet()) {
                    System.out.println(count + "." + menuitem); // print order with number
                    count++;
                }
            }
            break;

        }
    }

    public static RoomService getRoomServiceFromDB(int OrderID) {
        for (RoomService eachRoomService : new RoomServiceDB().findAllEntries()) {
            if (OrderID == eachRoomService.getOrderID()) {
                return eachRoomService;
            }
        }
        return null;
    }

    public void manageUpdateOrderStatus() {
    }
}
