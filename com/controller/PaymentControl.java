package com.controller;

import java.util.List;
import java.util.Scanner;

import com.db.guestDB.GuestDB;
import com.db.paymentDB.PaymentDB;
import com.db.reservationDB.ReservationDB;
import com.enums.RoomTypes;
import com.models.Guest;
import com.models.Payment;
import com.models.Reservation;
import com.models.RoomService;
import com.utils.FrontendUtils;
import com.utils.MiscUtils;

public abstract class PaymentControl {

    public static void checkOut() {
        Reservation r = getReservation();

        if (r == null) {
            System.out.println("That room is unoccupied");
            return;
        }

    }

    public static Reservation getReservation() {
        int checkOutRoomNumber = FrontendUtils.<Integer>getEachFieldFromUser(
                "Enter the Room Number for check out: ",
                "Invalid room number. Check your number",
                i -> MiscUtils.roomNumberExists(i), "Integer");

        return new ReservationDB().findSingleEntryByNumber(checkOutRoomNumber);
    }

    // Ask the user for the name of the guest paying

    // Find the guest we want

    // if paying guest is not a paying guest do again.

    // Show the payment details

    // ^call the functions below

    // Confirm that the guest has payed, Payment is always successful (assumption)

    // total = (room service + days) * 1.17 - svc charge
    // parameter for below needs to be discussed
    public static void printCheckOut(Reservation r) {
        Scanner sc = new Scanner(System.in);
        // prints out all the required details
        System.out.printf("Your total Room Service Bill is: %f%n", getRoomServiceTotal(r));
        System.out.printf("Your total Room Charges are: %f%n", getRoomTotal(r));
        System.out.printf("Service Charges (17%): %f%n",getServiceChargeTotal(r));
        System.out.printf("Grand Total: %f", getTotal(r));

        Guest = 
        
        if (Guest.isPayingGuest.creditCardUsed) {// creditcard registered then Y/N, otherwise cash
            System.out.printf("You have the following credit card registered: %f, Press Y to confirm, N to review again " ), }
        else (){
            System.out.printf("Pay with cash, no credit card registered, Press Y to confirm, N to review again");
        }
        
        char choice = sc.next().charAt(0); 
        
        switch(choice){
            case 'Y':
                System.out.println("Payment and Check-Out Successful. Thank you for staying with us.");
                // set ROOM TO VACANT (RoomStatuses)
                break;
            case 'N':
                printCheckOut(r);
                break;
            default: 
                System.out.println("Invalid input. Try again.");
        }




        
    }

    private static double getRoomServiceTotal(Reservation r) { // kiv
        List<RoomService> roomServiceList = null; // fix this, from jayds

        double total = 0.0;
        for (RoomService rs : roomServiceList) 
            total += rs.; // need a method to get the value for those reservations 

        return total;
    }

    private static double getServiceChargeTotal(Reservation r) {
        final double TAX = 17;
        return ((TAX / 100.0) * (getTotal(r) + getRoomTotal(r)));

    }

    private static double getRoomTotal(Reservation r) {
        try {
            long difference = r.getCheckOutDate().getTime() - r.getCheckInDate().getTime();
            double numberOfDays = (difference / (1000 * 60 * 60 * 24));
            return numberOfDays * r.getReservedRoom().getRoomType().getRatePerNight();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }
    
    public static double getTotal(Reservation r) {
        return getRoomTotal(r) + getServiceChargeTotal(r) + getRoomServiceTotal(r);
    }
    
}
