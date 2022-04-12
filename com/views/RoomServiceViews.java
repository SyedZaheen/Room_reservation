package com.views;

import com.controller.RoomServiceControl;
import com.db.roomserviceDB.RoomServiceDB;
import com.models.MenuItem;
import com.models.RoomService;
import com.utils.MiscUtils;

public class RoomServiceViews implements Views {

    @Override
    public void process() {
        RoomService roomservice = null;
        boolean success = false;
        RoomServiceControl control = new RoomServiceControl();

        // First, we need to see if we need to create, read, update or delete order.
        int choice;

        while (true) {
            choice = UserInputViews.getUserChoice(new String[] {
                    "Take an order",
                    "View order status",
                    "Cancel order",
                    "Return to main menu"
            });
            // For each, we call the corresponding function.
            switch (choice) {
                case 1:
                    roomservice = new RoomServiceControl().manageCreateEntry();
                    success = new RoomServiceDB().createEntry(roomservice);
                    // Let's send the guest to the database

                    if (success) {
                        System.out.println(
                                "An order was sucessfully created! Here is your receipt: ");
                        System.out.println(roomservice.getOrders().keySet());
                    } else {
                        System.out.println(
                                "Something went wrong trying to save the room service data. Contact the administrators");
                        return;
                    }
                case 2:
                    RoomServiceControl.printOrder();
                    break;
                case 3:
                    // this is some shitty ass code jesus christ

                    int count = 1;
                    int orderID = UserInputViews.<Integer>getEachFieldFromUser(
                            "Please enter your OrderID: ",
                            "Error. please enter a number 1 to 1000 characters long",
                            i -> MiscUtils.isValidInteger((int) i),
                            "Integer");

                    RoomService rs = RoomServiceControl.getRoomServiceFromDB(orderID);

                    int itemNumber = UserInputViews.<Integer>getEachFieldFromUser(
                            "Please enter the Item number you wish to remove: ",
                            "Error. Please check if you have entered a valid order number!",
                            i -> MiscUtils.isValidIntegerFromStartToEnd(1, rs.getOrders().size(), (int) i),
                            "Integer");

                    if (rs != null) {
                        for (MenuItem menuitem : rs.getOrders().keySet()) {
                            if (count == itemNumber) {
                                rs.removeItemFromOrder(menuitem);
                            }
                            count++;
                        }
                    }

                    break;

                // update roomservice item in database

                default:
                    return;
            }
        }
    }
}
