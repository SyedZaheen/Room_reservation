package com.views;

import com.controller.RoomServiceControl;
import com.models.RoomService;
import com.utils.MiscUtils;

public class RoomServiceViews implements Views {

    @Override
    public void process() {
        RoomService roomservice = null;
        RoomServiceControl control = new RoomServiceControl();

        // First, we need to see if we need to create, read, update or delete order.
        int choice;

        while (true) {
            choice = UserInputViews.getUserChoice(new String[] {
                    "Create new room service order",
                    "View all active orders",
                    "View/update order status/cancel order",
                    "Manage menu items",
                    "Return to main menu"
            });
            // For each, we call the corresponding function.
            switch (choice) {
                case 1:
                    if (control.getMenu().isEmpty()) {
                        System.out.println(
                                "There are no items on the menu! The customer can't order anything! Add something and come back.");
                        break;
                    }
                    roomservice = control.manageCreateEntry();
                    if (roomservice == null) {
                        System.out.println("Something went wrong. Contact the administrators");
                        break;
                    }
                    System.out.println(
                            "An order was sucessfully created! Here is your receipt: ");
                    System.out.println(roomservice);

                case 2:
                    if (control.getDB().isEmpty()) {
                        System.out.println("There are no active entries at the moment");
                        break;
                    }
                    for (RoomService rsv : control.getDB().findAllEntries()) {
                        System.out.println("The room service ID is: " + rsv.getRoomServiceID());
                        control.viewOrder(rsv.getOrders());
                        System.out.println();
                    }
                    break;
                case 3:
                    if (control.getDB().isEmpty()) {
                        System.out.println("There are no active entries at the moment");
                        break;
                    }

                    int roomServiceID = UserInputViews.<Integer>getEachFieldFromUser(
                            "Please enter your RoomServiceID: ",
                            "Error. please enter a valid 7-digit Room Service ID",
                            i -> MiscUtils.isValidID(i),
                            "Integer");

                    RoomService rsv = control.getRoomServiceFromDB(roomServiceID);
                    if (rsv == null) {
                        System.out.println("That Room Service ID was invalid! Try again");
                        break;
                    }

                    System.out.println("Room Service ID found! These are the orders: ");
                    control.viewOrder(rsv.getOrders());
                    System.out.println("\nPlease enter what would you like to do next: ");
                    int ch = UserInputViews.getUserChoice(new String[] {
                            "Update order statuses",
                            "Cancel order",
                            "Go back to room service menu"
                    });

                    if (ch == 3)
                        break;

                    if (ch == 1 || ch ==2) {
                        control.manageUpdateEntry(rsv);
                    }
                    break;

                case 4:
                    new MenuViews().process();

                default:
                    return;
            }
        }
    }
}
