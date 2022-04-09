package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import com.models.Menu;
import com.models.MenuItem;
import com.models.RoomService;
import com.utils.FrontendUtils;
import com.db.RoomServiceDB;
import com.enums.OrderStatus;



public class RoomServiceController implements Controller{

    private Menu menu = new Menu();

    public static void process() {
        RoomService roomservice = null;
        boolean success = false;

        // First, we need to see if we need to create, read, update or delete guest.
        int choice = FrontendUtils.getUserChoice(new String[] {
                        "Take an order",
                        "View order status",
                        "Cancel order",
        });
        // For each, we call the corresponding function.
        switch (choice) {
                case 1:
                        roomservice = new RoomServiceController().manageCreateEntry();
                        success = new RoomServiceDB().createEntry(roomservice);
                        // Let's send the guest to the database.
                        // todo: This design is pretty shit but idk how to fix it.
                       

                        if (success) {
                                System.out.println(
                                                "An order was sucessfully created! Here is your receipt: ");
                                System.out.println(roomservice.getOrders().keySet());
                        } else
                                System.out.println(
                                                "Something went wrong trying to save the guest data. Contact the administrators");
                        break;
                case 2:
                    RoomServiceController.printOrder();
                    break;
                case 3:
                    Scanner sc = new Scanner(System.in);
                    RoomServiceController.printOrder();
                    System.out.println("Enter which order you wish to delete: ");


                default:
                        break;
        }
}

    @Override
    public RoomService manageCreateEntry() {
        int orderID;
        HashMap<MenuItem, OrderStatus> orders = new HashMap<>();
        ArrayList <MenuItem> takenOrders = takeOrders();
        for(MenuItem menuitem : takenOrders) {
            orders.put(menuitem, OrderStatus.PREPARING);
        }

        
        boolean success = false;
        do {
            orderID = new Random().nextInt(1,1000);
            for (RoomService rs : new RoomServiceDB().findAllEntries()) {
                if(orderID == rs.getOrderID()) {
                    break;
                }
            }
            success = true;
        } while(success == false);
        RoomService roomservice = new RoomService(orders, orderID);
        
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

    public static void printOrder() {
                    int count = 1;
                    int orderID;
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Enter OrderID: ");
                    orderID = sc.nextInt();
                    System.out.println("Orders: ");
                    // can abstract this out maybe
                    for (RoomService eachRoomService : new RoomServiceDB().findAllEntries() ) {
                            if (orderID == eachRoomService.getOrderID()) { 
                                for (MenuItem menuitem : eachRoomService.getOrders().keySet()) {
                                    System.out.println(count + "." + menuitem); //print order with number
                                    count++;
                                }
                            }
                            break;
                            
                    }
    }

}
