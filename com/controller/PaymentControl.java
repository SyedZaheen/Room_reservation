package com.controller;

import com.Views;
import com.db.reservationDB.ReservationDB;
import com.enums.PaymentType;
import com.enums.RoomStatuses;
import com.models.Reservation;

public abstract class PaymentControl {

    public static void process() {

        int choice = Views.getUserChoice(new String[] {
                "Make payment and check out from room",
                "Print payment slip"
        });

        switch (choice) {
            case 1:
                checkOut();
                break;

            case 2:
                printBill();
                break;
        }
    }

    public static void checkOut() {
        int reservationID = Views.<Integer>getEachFieldFromUser(
                "Please enter the reservation ID: ",
                "Error. Please enter a 6 digit number.",
                i -> (i >= 1e6 && i < 1e7),
                "Integer");

        Reservation toCheckOut = new ReservationDB().findSingleEntry(reservationID);
        if (toCheckOut == null) {
            System.out.println("Reservation ID is invalid!");
            return;
        }

        toCheckOut.getReservedRoom().setStatus(RoomStatuses.VACANT);
        // After checkout is done, user must return to Main Menu, go to Reservation
        // Menu, and delete reservation.
    }

    // TODO: Complete this - not done because number of days need to be calculated
    public static void printBill() {
        int reservationID = Views.<Integer>getEachFieldFromUser(
                "Please enter the reservation ID: ",
                "Error. Please enter a 6 digit number.",
                i -> (i >= 1e6 && i < 1e7),
                "Integer");

        Reservation toBill = new ReservationDB().findSingleEntry(reservationID);

        int discountChoice = Views.<Integer>getEachFieldFromUser(
                "Do you wish to apply a discount? (1) Apply discount.\n(2) Do not have discount.",
                "Error. Please enter either 1 or 2.",
                i -> (i == 1 || i == 2),
                "Integer");

        double discount = 1;
        if (discountChoice == 1) {
            discount = Views.<Double>getEachFieldFromUser(
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
        int choice = Views.getUserChoice(new String[] {
                "Enter 1 if the above data are all correct",
                "Enter 2 if the data has errors"
        });

        if (choice == 1)
            System.out.println("Payment Successful! Thank you for staying with us.");

        if (choice == 2)
            printBill();
    }

    // TODO : To amend after receiving Zaheen's implementation of calculation of
    // time
    private static double computeRoomCharges(Reservation r) {
        return 0;
    }

    // TODO : To amend after receiving Jayden's code.
    private static double computeRoomServiceCharges(Reservation r) {
        return 0;
    }

    private static double computeTax(Reservation r) {
        final double TAX = 17;
        return ((TAX / 100.0) * (computeRoomCharges(r) + computeRoomServiceCharges(r)));

    }

    // Ask the user for the name of the guest paying
    // Find the guest we want
    // if paying guest is not a paying guest do again.
    // Show the payment details
    // ^call the functions below
    // Confirm that the guest has payed, Payment is always successful (assumption)
    // total = (room service + days) * 1.17 - svc charge
    // parameter for below needs to be discussed
}
