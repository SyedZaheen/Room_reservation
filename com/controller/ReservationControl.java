package com.controller;

import com.db.reservationDB.reservationDB;
import com.enums.ReservationStatuses;
import com.models.CreditCard;
import com.models.Guest;
import com.models.Reservation;
import com.utils.FrontendUtils;
import com.utils.MiscUtils;

public abstract class ReservationControl {

    public static void process() {
        Reservation reservation = null;
        boolean success = false;

        int choice = FrontendUtils.getUserChoice(new String[] {
            "Create a new reservation",
            "Update reservation",
            "See all reservations",
            "Delete reservation"
        });

        switch(choice) {
            case 1:
                reservation = manageCreateReservation();
                success = new ReservationDB().createEntry(reservation);

                if(success) {
                    System.out.println(
                        "A new reservation was successfully created! THese are the saved reservation data: ");
                    
                    System.out.println(reservation);
                } else
                    System.out.println(
                        "Something went wrong trying to save the reservation data. Contact the adminstrators.")
        }
    }

}
