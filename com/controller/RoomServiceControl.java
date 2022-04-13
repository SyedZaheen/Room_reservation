package com.controller;

import java.util.ArrayList;
import java.util.List;

import com.models.Menu;
import com.models.Order;
import com.models.Reservation;
import com.models.RoomService;
import com.utils.MiscUtils;
import com.views.UserInputViews;
import com.db.reservationDB.ReservationDB;
import com.db.roomserviceDB.RoomServiceDB;
import com.enums.OrderStatus;

public class RoomServiceControl implements CreatorController<RoomService> {

    @Override
    public RoomService manageCreateEntry() {
        List<Order> orders = takeOrders();
        RoomService roomservice = new RoomService(orders);
        return roomservice;
    }

    private List<Order> takeOrders() {
        int choice;
        boolean success;
        List<Order> orderlist = new ArrayList<>();

        String finalOrderList = "";
        MiscUtils.printLightTransition();
        do {
            choice = UserInputViews.getUserChoice(new Menu().getAllItemsInString(), true); // Use the menu constructor
                                                                                           // in
            // order to re-query the database
            if (choice == -1)
                break;
            orderlist.add(new Order(new Menu().getItems().get(choice - 1)));
            System.out.println("Key in -1 to exit");
        } while (choice != -1);

        for (Order order : orderlist) {
            finalOrderList += order.toString() + "\n";
        }

        success = UserInputViews.<String>userDoubleConfirmDetails(finalOrderList);
        if (!success)
            orderlist = takeOrders();
        return orderlist;
    }

    public void viewAllRoomServiceOrders() {
        List<RoomService> lRS = new RoomServiceDB().findAllEntries();
        for (RoomService roomService : lRS) {
            System.out.println("The reservation ID is: " + roomService.getRoomServiceID());
            viewOrder(roomService.getOrders());
            System.out.println("");
        }
    }

    public void viewOrder(List<Order> orderlist) {
        if (orderlist.isEmpty()) {
            System.out.println("Order list is empty!");
        }
        for (Order order : orderlist) {
            System.out.println(order);
        }
    }

    public RoomService getRoomServiceFromDB(int RoomServiceID) {
        for (RoomService eachRoomService : new RoomServiceDB().findAllEntries()) {
            if (RoomServiceID == eachRoomService.getRoomServiceID()) {
                return eachRoomService;
            }
        }
        return null;
    }

    public void manageUpdateEntry(RoomService rsv) {
        RoomServiceDB db = new RoomServiceDB();

        List<Order> orders = rsv.getOrders();
        int ch;
        System.out.println(
                "\nA guest is only entitled to pay for the room service only after ALL of the order statuses are completed!\n");
        if (attachToReservation(rsv))
            return;
        for (Order order : orders) {
            System.out.println("The current status of the " + order.getName() + " is: " + order.getStatus().inString);
            System.out.printf("What would you like to change the order of the " + order.getName() + " to?");
            ch = UserInputViews.getUserChoice(new String[] {
                    "Preparing",
                    "Completed",
                    "Cancelled",
                    "Keep the same status"
            });

            switch (ch) {
                case 1:
                    order.setStatus(OrderStatus.PREPARING);
                    break;
                case 2:
                    order.setStatus(OrderStatus.COMPLETED);
                    break;
                case 3:
                    order.setStatus(OrderStatus.CANCELLED);
                    break;
                default:
                    break;
            }
        }
        System.out.println("\n The following is the summary of orders: \n");
        viewOrder(rsv.getOrders());

        if (attachToReservation(rsv))
            return;

        if (db.updateEntry(rsv))
            System.out.println("All have been updated!");
        else
            System.out.println("Something went wrong. Contact the administrators");

    }

    private boolean attachToReservation(RoomService rsv) {
        int ch;
        RoomServiceDB db = new RoomServiceDB();
        ReservationDB reservationDB = new ReservationDB();
        if (!allOrdersCompleted(rsv.getOrders()))
            return false;

        System.out.println(
                "All of the necessary orders have been completed! Would you like to attach the payment for the service to the guest's reservation?");
        ch = UserInputViews.getUserChoice(new String[] {
                "Yes",
                "No. I would want to do that later. "
        });

        if (ch == 2)
            return false;

        System.out.println("Reservation of the guest who had order the room service, to be updated: ");
        Integer key = UserInputViews.getEachFieldFromUser(
                "Please enter the reservation ID: ",
                "Error. Please enter a 7 digit number.",
                i -> MiscUtils.isValidID(i),
                "Integer");

        Reservation toUpdate = new ReservationDB().findSingleEntry(key);
        if (toUpdate == null) {
            System.out.println(
                    "Reservation ID does not exist! Check the full reservation list to see if the ID is correct");
            return false;
        }

        System.out.println("\nReservation found! The following are the reservation details: ");
        System.out.println(toUpdate);
        ArrayList<RoomService> ls = toUpdate.getRoomServices();
        ls.add(rsv);
        toUpdate.setRoomServices(ls);

        if (reservationDB.updateEntry(toUpdate) && db.deleteEntry(rsv)) {
            System.out.println("Orders considered done, guest will now be charged with the payment for the room service.");
            return true;
        }

        return false;
    }

    private boolean allOrdersCompleted(List<Order> list) {
        if (list.isEmpty())
            return false;

        boolean allAreCompleted = true;
        for (Order order : list) {
            if (order.getStatus() != OrderStatus.COMPLETED) {
                allAreCompleted = false;
                break;
            }
        }

        return allAreCompleted;
    }

}
