package com.controller;

import com.db.reservationDB.ReservationDB;
import com.enums.PaymentType;
import com.enums.RoomStatuses;
import com.models.Reservation;
import com.utils.FrontendUtils;

public abstract class PaymentControl {

    public static void process() {

        int choice = FrontendUtils.getUserChoice(new String[] {
                "Check out from room",
                "Print payment slip"
        });

        switch (choice) {
            case 1:
                checkOut();
                break;

<<<<<<< Updated upstream
            case 2:
                printBill();
                break;
        }
    }

    public static void checkOut() {
        int reservationID = FrontendUtils.<Integer>getEachFieldFromUser(
                "Please enter the reservation ID: ",
                "Error. Please enter a 6 digit number.",
                i -> (i >= 1e6 && i < 1e7),
=======
/**
 * PaymentControl.java
 * A controller class that consists of methods that will enable its users to control the outcome of a Payment object.
 * 
 * @author DSF1 Group 1
 */
public class PaymentControl {

    
    /** 
     * It is a method that prints the bill of a particular reservation.
     * @param checkout it is a true/false parameter to determine if a guest is keen to check-out or not.
     */
    public void printBill(boolean checkout) {
        int reservationID = UserInputViews.<Integer>getEachFieldFromUser(
                "Please enter the reservation ID to see reservation bill: ",
                "Error. Please enter a 7 digit number.",
                i -> MiscUtils.isValidID(i),
>>>>>>> Stashed changes
                "Integer");

        Reservation toCheckOut = new ReservationDB().findReservationByID(reservationID);
        if (toCheckOut == null) {
            System.out.println("Reservation ID is invalid!");
            return;
        }

        toCheckOut.getReservedRoom().setStatus(RoomStatuses.VACANT);
        // After checkout is done, user must return to Main Menu, go to Reservation
        // Menu, and delete reservation.
    }

    public static void printBill() {
        int reservationID = FrontendUtils.<Integer>getEachFieldFromUser(
                "Please enter the reservation ID: ",
                "Error. Please enter a 6 digit number.",
                i -> (i >= 1e6 && i < 1e7),
                "Integer");

        Reservation toBill = new ReservationDB().findReservationByID(reservationID);

        int discountChoice = FrontendUtils.<Integer>getEachFieldFromUser(
                "Do you wish to apply a discount? (1) Apply discount.\n(2) Do not have discount.",
                "Error. Please enter either 1 or 2.",
                i -> (i == 1 || i == 2),
                "Integer");

        double discount = 1;
        if (discountChoice == 1) {
            discount = FrontendUtils.<Double>getEachFieldFromUser(
                    "Enter discount rate: ",
                    "Error. Please enter a valid double value!",
                    i -> (i > 0 && i < 1),
                    "Double");
        }

        System.out.println("Room Charges per night: " + toBill.getReservedRoom().getRoomType().getRatePerNight());
        System.out.printf("Net Total Room Charges: %.2f\n", computeRoomCharges(toBill));
        System.out.println("");
        System.out.printf("Net Total Room Service Charges: %.2f\n", computeRoomServiceCharges(toBill));
        System.out.println("");
        System.out.printf("Additional Surcharges: %.2f\n", computeTax(toBill));

        if (discount != 1) {
            System.out.println((discount * 100) + "% Discount applied!");
            System.out.printf("Grand Total: %.2f\n", (1 - discount)
                    * (computeRoomCharges(toBill) + computeRoomServiceCharges(toBill) + computeTax(toBill)));
            System.out.println("");
        } else {
            System.out.printf("Grand Total: %.2f\n",
                    computeRoomCharges(toBill) + computeRoomServiceCharges(toBill) + computeTax(toBill));
            System.out.println("");
        }

        if (toBill.getPayingGuest().getPaymentType() == PaymentType.CREDITCARD) {
            System.out.println("Payment billed to:");
            System.out.println(toBill.getPayingGuest().getCreditCard());
        } else if (toBill.getPayingGuest().getPaymentType() == PaymentType.CASH)
            System.out.println("Payment by Cash (SGD only).");

        System.out.println("Please confirm that the above bill is correct: ");
        int choice = FrontendUtils.getUserChoice(new String[] {
                "Enter 1 if the above data are all correct",
                "Enter 2 if the data has errors"
        });

        if (choice == 1)
            System.out.println("Payment Successful! Thank you for staying with us.");

        if (choice == 2)
            printBill();
    }

<<<<<<< Updated upstream
    // TODO : To amend after receiving Zaheen's implementation of calculation of
    // time
    private static double computeRoomCharges(Reservation r) {
        return 0;
    }

    // TODO : To amend after receiving Jayden's code.
=======
    
    /** 
     * It is a method that will calculate the total room charges.
     * @param r the Reservation object that includes the Room.
     * @param daysOfStay the number of days stayed in the hotel
     * @return the total room charges.
     */
    private static int computeRoomCharges(Reservation r, int daysOfStay) {
        return daysOfStay * r.getReservedRoom().getRoomType().getRatePerNight();
    }
    
    /** 
     * It is a method that will calculate the total RoomService charges.
     * @param r the Reservation object that includes the RoomService
     * @return the total RoomService charges
     */
>>>>>>> Stashed changes
    private static double computeRoomServiceCharges(Reservation r) {
        return 0;
    }

<<<<<<< Updated upstream
    private static double computeTax(Reservation r) {
=======
    
    /** 
     * It is a method that will calculate the total tax levied on the sum of room charges and RoomService charges.
     * @param r the Reservation object that includes the Room and the RoomService
     * @param n the number of days stayed in the hotel.
     * @return the total tax levied.
     */
    private static double computeTax(Reservation r, int n) {
>>>>>>> Stashed changes
        final double TAX = 17;
        return ((TAX / 100.0) * (computeRoomCharges(r) + computeRoomServiceCharges(r)));

    }

<<<<<<< Updated upstream
=======
    
    /** 
     * It is a method that will print out the orders that were ordered in a RoomService for a particular Reservation.
     * @param r the Reservation object the contains the RoomService.
     */
    private static void printMenuItemDetails(Reservation r) {
        ArrayList<RoomService> listOfRoomServices = r.getRoomServices();
        for (RoomService roomService : listOfRoomServices) {
            System.out.println("\nThis is room service number: " + roomService.getRoomServiceID());
            System.out.println("These were the orders: ");
            new RoomServiceControl().viewOrder(roomService.getOrders());
        }
    }

>>>>>>> Stashed changes
    // Ask the user for the name of the guest paying
    // Find the guest we want
    // if paying guest is not a paying guest do again.
    // Show the payment details
    // ^call the functions below
    // Confirm that the guest has payed, Payment is always successful (assumption)
    // total = (room service + days) * 1.17 - svc charge
    // parameter for below needs to be discussed
}
