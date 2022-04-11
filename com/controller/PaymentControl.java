package com.controller;

import com.Views;
import com.db.reservationDB.ReservationDB;
import com.enums.PaymentType;
import com.enums.RoomStatuses;
import com.models.Reservation;
import com.utils.MiscUtils;

public class PaymentControl implements MasterController {
    @Override
    public void process() {

        int choice;

        while (true) {

            choice = Views.getUserChoice(new String[] {
                    "Make payment and check out from room",
                    "See payment slip for reservation",
                    "Return to main menu"
            });

            switch (choice) {
                case 1:
                    printBill(true);
                    break;

                case 2:
                    printBill(false);
                    break;
                default:
                    return;
            }
        }
    }

    private void printBill(boolean checkout) {
        int reservationID = Views.<Integer>getEachFieldFromUser(
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

        int discountChoice = Views.<Integer>getEachFieldFromUser(
                "Does the guest have a valid discount? (1) Apply discount.\n(2) Do not have discount.",
                "Error. Please enter either 1 or 2.",
                i -> (i == 1 || i == 2),
                "Integer");

        double discount = 1D;
        if (discountChoice == 1) {
            discount = Views.<Double>getEachFieldFromUser(
                    "Enter discount rate: ",
                    "Error. Please enter a valid non-integer value between 0 and 1 (exclusive)!",
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

        if (!checkout)
            return;

        System.out.println("Please confirm that the above bill is correct: ");
        int choice = Views.getUserChoice(new String[] {
                "Enter 1 if the above data are all correct",
                "Enter 2 if the data has errors"
        });

        if (choice == 1) {
            boolean succ = new ReservationDB().deleteEntry(toBill);
            if (succ)
                System.out.println("Payment Successful! Thank you for staying with us.");
            else
                System.out.println("Something went wrong! Contact the administrators.");
            return;
        }

        if (choice == 2)
            printBill(checkout);
    }

    private static double computeRoomCharges(Reservation r) {
        int daysOfStay = r.getCheckOutDate().compareTo(r.getCheckInDate());
        return ((double) daysOfStay) * (double) r.getReservedRoom().getRoomType().getRatePerNight();
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
