package com.controller;

import java.util.ArrayList;
import java.util.List;

import com.models.Menu;
import com.models.Order;
import com.models.Reservation;
import com.models.RoomService;
import com.utils.MiscUtils;
import com.views.UserInputViews;
import com.db.menuDB.MenuItemDB;
import com.db.reservationDB.ReservationDB;
import com.db.roomserviceDB.RoomServiceDB;
import com.enums.OrderStatus;

/**
 * RoomServiceControl.java
 * A controller class that realises the CreatorController interface.
 * It consists of methods that will enable its users to control the outcome of a RoomService object.
 * 
 * @author DSF1 Group 1
 */
public class RoomServiceControl implements CreatorController<RoomService> {

    
    /** 
     * manageCreateEntry()
     * It is a method that would create a new RoomService object. If the object created is valid, it will be subsequently serialised into the RoomServiceDB.
     * @return the RoomService object that is created.
     */
    @Override
    public RoomService manageCreateEntry() {
        RoomServiceDB db = new RoomServiceDB();
        List<Order> orders = takeOrders();
        RoomService roomservice = new RoomService(orders);
        return db.createEntry(roomservice) ? roomservice : null;
    }

    
    /** 
     * takeOrders()
     * It takes in a user's choice of orders from the Menu and adds it into a List object.
     * @return the list of Order objects that are selected by the user.
     */
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

    /**
     * viewAllRoomServiceOrders()
     * It prints out all the RoomService IDs and its corresponding RoomService orders that are stored in the RoomServiceDB
     */
    public void viewAllRoomServiceOrders() {
        List<RoomService> lRS = new RoomServiceDB().findAllEntries();
        for (RoomService roomService : lRS) {
            System.out.println("The RoomService ID is: " + roomService.getRoomServiceID());
            viewOrder(roomService.getOrders());
            System.out.println("");
        }
    }

    
    /** 
     * viewOrder()
     * If the list of orders in a particular RoomService object is not empty, it will print out all the orders.
     * @param orderlist this is the corresponding order list for each RoomService object.
     */
    public void viewOrder(List<Order> orderlist) {
        if (orderlist.isEmpty()) {
            System.out.println("Order list is empty!");
        }
        for (Order order : orderlist) {
            System.out.println(order);
        }
    }

    
    /** 
     * getRoomServiceFromDB()
     * It will search through the RoomServiceDB for a particular RoomService object that matches the RoomServiceID.
     * @param RoomServiceID it is a particular RoomServiceID that the user wishes to search for.
     * @return the particular RoomService object that the user is searching for.
     */
    public RoomService getRoomServiceFromDB(int RoomServiceID) {
        for (RoomService eachRoomService : new RoomServiceDB().findAllEntries()) {
            if (RoomServiceID == eachRoomService.getRoomServiceID()) {
                return eachRoomService;
            }
        }
        return null;
    }

    
    /** 
     * manageUpdateEntry()
     * It will update the status of each orders within a particular RoomService object.
     * @param rsv it is the particular RoomService object that requires updating.
     */
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

    
    /** 
     * attachToReservation()
     * It is a checker method that checks if the orders within a RoomService object has been COMPLETED.
     * If it is completed the details for payment will be added to the corresponding Reservation object, it will update the ReservationDB and delete the entry from the RoomServiceDB.
     * @param rsv the corresponding RoomService object that serves to be added to the Reservation.
     * @return it will return true if the orders are all COMPLETED and execute the mentioned DB functions.
     */
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
            System.out.println(
                    "We will update the reservation status first. Please come back and attach to guest later.");
            return false;
        }

        System.out.println("\nReservation found! The following are the reservation details: ");
        System.out.println(toUpdate);
        ArrayList<RoomService> ls = toUpdate.getRoomServices();
        ls.add(rsv);
        toUpdate.setRoomServices(ls);

        if (reservationDB.updateEntry(toUpdate) && db.deleteEntry(rsv)) {
            System.out.println(
                    "Orders considered done, guest will now be charged with the payment for the room service.");
            return true;
        }

        return false;
    }

    
    /** 
     * allOrdersCompleted()
     * It is a checker function that checks if the orders within a RoomService object is completed.
     * @param list this is the list of orders that would be checked.
     * @return it will return true if the orders are all completed.
     */
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

    
    /** 
     * getDB()
     * It is a getter function that gets the RoomServiceDB.
     * @return the RoomServiceDB
     */
    public RoomServiceDB getDB() {
        return new RoomServiceDB();
    }

    
    /** 
     * getMenu()
     * It is a getter function that gets the MenuItemDB.
     * @return the MenuItemDB().
     */
    public MenuItemDB getMenu() {
        return new MenuItemDB();
    }
}
