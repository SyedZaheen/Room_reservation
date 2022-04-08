package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import com.models.Menu;
import com.models.MenuItem;
import com.models.RoomService;
import com.utils.FrontendUtils;
import com.enums.OrderStatus;

public class RoomServiceController implements Controller{

    private Menu menu = new Menu();

    @Override
    public RoomService manageCreateEntry() {
        HashMap<MenuItem, OrderStatus> orders = new HashMap<>();
        ArrayList <MenuItem> takenOrders = takeOrders();
        for(MenuItem menuitem : takenOrders) {
            orders.put(menuitem, OrderStatus.PREPARING);
        }
        RoomService roomservice = new RoomService(orders);
        return roomservice;
    }
    

    public ArrayList<MenuItem> takeOrders(){
        int choice;
        boolean success;
        ArrayList<MenuItem> orderlist = new ArrayList<>();
        String finalOrderList = "";
        do {
            choice = FrontendUtils.getUserChoice(menu.getAllItemsInString());
            if (choice == -1) break;
            orderlist.add(menu.items.get(choice - 1));
            System.out.println("Key in -1 to exit");
        } while (choice != -1);

        for (MenuItem menuItem : orderlist) {
            finalOrderList += menuItem.toString() + "\n";

        }
        success = FrontendUtils.<String>userDoubleConfirmDetails(finalOrderList);
        if (!success) orderlist = takeOrders();
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

}
