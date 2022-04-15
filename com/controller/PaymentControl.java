package com.controller;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import com.db.reservationDB.ReservationDB;
import com.enums.PaymentType;
import com.models.Order;
import com.models.Reservation;
import com.models.RoomService;
import com.utils.MiscUtils;
import com.views.UserInputViews;

public class PaymentControl {

    public void printBill(boolean checkout) {
        int reservationID = UserInputViews.<Integer>getEachFieldFromUser(
                "Please enter the reservation ID to see reservation bill: ",
                "Error. Please enter a 7 digit number.",
                i -> MiscUtils.isValidID(i),
                "Integer");

        Reservation toBill = new ReservationDB().findSingleEntry(reservationID);
        if (toBill == null) {
            System.out.println("Could not find a reservation with that ID! Try again");
            return;
        }

        System.out.println("\nThe following are the reservation details in full: \n");
        System.out.println(toBill);
        System.out.println("");

        int discountChoice = UserInputViews.<Integer>getEachFieldFromUser(
                "Does the guest have a valid discount? (1) Apply discount.\n(2) Do not have discount.",
                "Error. Please enter either 1 or 2.",
                i -> (i == 1 || i == 2),
                "Integer");

        double discount =1;
        if (discountChoice == 1) {
            discount = UserInputViews.<Double>getEachFieldFromUser(
                    "Enter discount rate: ",
                    "Error. Please enter a valid non-integer value between 0 and 1 (exclusive)!",
                    i -> (i > 0 && i < 1),
                    "Double");
        }
        MiscUtils.printLightTransition();

        int numberOfNightsOfStay = (int) ChronoUnit.DAYS.between(toBill.getCheckInDate(), toBill.getCheckOutDate());

        System.out.println("Room Charges per night: " + toBill.getReservedRoom().getRoomType().getRatePerNight());
        System.out.println("Number of nights of stay: " + numberOfNightsOfStay);
        System.out.printf("Net Total Room Charges: %.2f\n", (double) computeRoomCharges(toBill, numberOfNightsOfStay));
        System.out.println("");
        printMenuItemDetails(toBill);
        System.out.printf("Net Total Room Service Charges: %.2f\n", (double) computeRoomServiceCharges(toBill));
        System.out.println("");
        System.out.printf("Additional Surcharges: %.2f\n", (double) computeTax(toBill, numberOfNightsOfStay));

        if (discount != 1) {
            System.out.println((discount * 100) + "% Discount applied!");
            System.out.printf("Grand Total: %.2f\n", (1 - discount)
                    * (computeRoomCharges(toBill, numberOfNightsOfStay) + computeRoomServiceCharges(toBill)
                            + computeTax(toBill, numberOfNightsOfStay)));
            System.out.println("");
        } else {
            System.out.printf("Grand Total: %.2f\n",
                    computeRoomCharges(toBill, numberOfNightsOfStay) + computeRoomServiceCharges(toBill)
                            + computeTax(toBill, numberOfNightsOfStay));
            System.out.println("");
        }

        if (!checkout)
            return;

        if (toBill.getPayingGuest().getPaymentType() == PaymentType.CREDITCARD) {
            System.out.println("Payment billed to:");
            System.out.println(toBill.getPayingGuest().getCreditCard());
        } else if (toBill.getPayingGuest().getPaymentType() == PaymentType.CASH)
            System.out.println("Payment by cash (SGD only)");

        System.out.println("\nPlease confirm that the above bill is correct: ");
        int choice = UserInputViews.getUserChoice(new String[] {
                "Enter 1 if the above data are all correct",
                "Enter 2 if the data has errors"
        });

        if (choice == 1) {
            System.out.println("Please confirm that the guest has confirmed payment and is checking out: ");
            int ch2 = UserInputViews.getUserChoice(new String[] {
                    "The guest has payed and has successfully checked out",
                    "The guest has not payed and is not checking out currently",
            });
            if (ch2 == 2)
                return;
            boolean succ = new ReservationDB().deleteEntry(toBill);
            if (succ)
                System.out.println(
                        "Update successful! Remember to thank the guest for staying with us!\n Note that all guest data is now deleted, and room is now vacant");
            else
                System.out.println("Something went wrong! Contact the administrators.");
            return;
        }

        if (choice == 2)
            printBill(checkout);
    }

    private static int computeRoomCharges(Reservation r, int daysOfStay) {
        return daysOfStay * r.getReservedRoom().getRoomType().getRatePerNight();
    }
    private static double computeRoomServiceCharges(Reservation r) {
        ArrayList<RoomService> listOfRoomServices = r.getRoomServices();
        double totalCharge = 0;
        for (RoomService roomService : listOfRoomServices) {
            for (Order menuitem : roomService.getOrders()) {
                totalCharge += menuitem.getPrice();
            }
        }
        return totalCharge;
    }

    private static double computeTax(Reservation r, int n) {
        final double TAX = 17;
        return ((TAX / 100.0) * (computeRoomCharges(r, n) + computeRoomServiceCharges(r)));

    }

    private static void printMenuItemDetails(Reservation r) {
        ArrayList<RoomService> listOfRoomServices = r.getRoomServices();
        for (RoomService roomService : listOfRoomServices) {
            System.out.println("\nThis is room service number: " + roomService.getRoomServiceID());
            System.out.println("These were the orders: ");
            new RoomServiceControl().viewOrder(roomService.getOrders());
        }
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
