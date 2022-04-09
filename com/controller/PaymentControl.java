package com.controller;

import com.models.Guest;
import com.models.Payment;
import com.models.Reservation;
import com.models.RoomService;
import com.utils.FrontendUtils;

import java.util.List;

import com.db.guestDB.GuestDB;



public class PaymentControl implements Controller {

    public static void process() {
        Payment newpayment = null; 
        boolean success = false; 
        
        

        newpayment = manageCreateEntry();
        success = new PaymentDB().createBill(newpayment);


        private static Payment checkOut() {

        
    }

    @Override
    public Payment manageCreateEntry()
    {
  
        String nameOfPayingGuest;
        Guest payingGuest = null;
        
        // Ask the user for the name of the guest paying

        nameOfPayingGuest = FrontendUtils.<String>getEachFieldFromUser("Enter the name of the Paying Guest:", 
        "Error. Either the Guest does not exist or is not listed as a Paying Guest", 
        i -> {
            payingGuest = new GuestDB().findSingleEntryByName(i);
            if (payingGuest.getName() == i){
                return false;
            }
            if (payingGuest.getIsPayingGuest() == false){
                return false;
            } else {
                return true;
            }
        }, 
        "String");


        {
            return null;
        }

        // Find the guest we want
        payingGuest =  new GuestDB().findSingleEntryByName(nameOfPayingGuest);
        // if paying guest is not a paying guest do again. 
       

        nameOfPayingGuest = FrontendUtils.<String>getEachFieldFromUser("Enter the name of the Paying Guest:", i -> MiscUtils.stringWithinLength(i, 5, 50) ,"Error. Please enter a string between 5 and 50 characters long"  ,"String")

        
                // Show the payment details

                // ^call the functions below

                

                // Confirm that the guest has payed

        if (!FrontendUtils.<Guest>userDoubleConfirmDetails(newPayment))
                newPayment = CreatePayment();
        
                // Make the room avail again ?

        return null;
    }

    // total = (room service + days) * 1.17 - svc charge

    public double getRoomServiceTotal() {
        RoomService roomServiceList = new RoomService(); // fix this

        double total = 0.0;
        for (RoomService rs : roomServiceList) // kiv - reservation
            total += rs.getTotal();

        return total;
    }


    public double getServiceChargeTotal() {
        final double TAX = 17;
        return ((getRoomServiceTotal() + getRoomTotal()) * (TAX / 100.0));

    }

    private double getRoomTotal(Reservation r) {
        try {
            long difference = r.getCheckOutDate().getTime() - r.getCheckInDate().getTime();
            double numberOfDays = (difference / (1000*60*60*24));
            return numberOfDays * dayRate; // decide dayRate later, weekend, weekday rate??
      } catch (Exception e) {
            e.printStackTrace();
      }
      
      
    }


    public double getTotal() {
        return getRoomTotal() + getRoomServiceTotal() + getServiceChargeTotal();
    }

    
}

   
    }
}



