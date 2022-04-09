package com.controller;

import com.db.reservationDB.ReservationDB;
import com.enums.ReservationStatuses;
import com.models.CreditCard;
import com.models.Guest;
import com.models.Reservation;
import com.models.Room;
import com.utils.FrontendUtils;
import com.utils.MiscUtils;
import com.controller.GuestControl;

import java.sql.Date;

public class ReservationControl implements Controller <Reservation> {

    public void process() {
        Reservation reservation = null;
        boolean success = false;

        int choice = FrontendUtils.getUserChoice(new String[] {
                "Create a new reservation",
                "Update reservation",
                "See all reservations",
                "Delete reservation"
        });

        switch (choice) {
            case 1:
                reservation = manageCreateEntry();
                success = new ReservationDB().createEntry(reservation);

                if (success) {
                    System.out.println(
                            "A new reservation was successfully created! These are the saved reservation data: ");

                    System.out.println(reservation);
                } else
                    System.out.println(
                            "Something went wrong trying to save the reservation data. Contact the adminstrators.");
                
                break;

            case 2:
                reservation = manageUpdateEntry();
                success = new ReservationDB().updateEntry(reservation);

                if (success) {
                    System.out.println(
                        "A new reservation was successfully updated! Thse are the current reservation data: ");
                    
                    System.out.println(reservation);
                } else
                    System.out.println(
                        "Something went wrong trying to save the reservation data. Contact the administrators.");
                
                break;

            case 3:
                System.out.println("The following are all the available reservation data so far: ");
                for (Reservation eachReservation : new ReservationDB().findAllEntries()) {
                    System.out.println("");
                    System.out.println(eachReservation);
                }

                break;

            case 4:
                // Reservation toDelete = new
                //reservation = manageDeleteEntry(toDelete);
                
        }
    }

    private Reservation manageUpdateEntry() {
        return null;
    }

    @Override
    public Reservation manageCreateEntry() {

        Integer reservationID, numberOfAdults, numberOfChildren, year, month, day;
        Guest guests[];
        Room reservedRoom;
        CreditCard creditCardUsed;
        Date checkInDate, checkOutDate;
        ReservationStatuses reservationStatus;

        reservationID = FrontendUtils.<Integer>getEachFieldFromUser(
            "Pleaes enter the reservation ID: ", 
            "Error. Please enter a valid reservation ID!", 
            i -> MiscUtils.isValidInteger(i), 
            "Integer");

        numberOfAdults = FrontendUtils.<Integer>getEachFieldFromUser(
            "Please enter the number of adults: ",
            "Error. Please enter a valid number!", 
            i -> MiscUtils.isValidInteger(i), 
            "Integer");

        numberOfChildren = FrontendUtils.<Integer>getEachFieldFromUser(
            "Please enter the number of children: ",
            "Error. Please enter a valid number!", 
            i -> MiscUtils.isValidInteger(i), 
            "Integer");

        for(int j = 0; j < numberOfAdults + numberOfChildren; j++) {
            System.out.println("Please enter the entry for guest " + (j + 1) + " : ");
            guests[j] = new GuestControl().manageCreateEntry();
        }

        reservedRoom = new RoomControl().manageCreateEntry();

        creditCardUsed = new CreditCardControl().manageCreateEntry();

        year = FrontendUtils.<Integer>getEachFieldFromUser(
            "Please enter the year (For Check-In): ", 
            "Error. Please enter a valid year!", 
            i -> MiscUtils.isValidYear(i), 
            "Integer");
        
        month = FrontendUtils.<Integer>getEachFieldFromUser(
            "Please enter the month (For Check-In): ", 
            "Error. Please enter a valid month!",
            i -> MiscUtils.isValidMonth(i), 
            "Integer");

        day = FrontendUtils.<Integer>getEachFieldFromUser(
            "Please enter the day (For Check-In): ", 
            "Error. Please enter a valid day!",
            i -> MiscUtils.isValidDay(i, month), 
            "Integer");

        checkInDate = Date.valueOf(MiscUtils.dateConvertor(year, month, day));

        year = FrontendUtils.<Integer>getEachFieldFromUser(
            "Please enter the year (For Check-Out): ", 
            "Error. Please enter a valid year!", 
            i -> MiscUtils.isValidYear(i), 
            "Integer");
        
        month = FrontendUtils.<Integer>getEachFieldFromUser(
            "Please enter the month (For Check-Out): ", 
            "Error. Please enter a valid month!",
            i -> MiscUtils.isValidMonth(i), 
            "Integer");

        day = FrontendUtils.<Integer>getEachFieldFromUser(
            "Please enter the day (For Check-Out): ", 
            "Error. Please enter a valid day!",
            i -> MiscUtils.isValidDay(i, month), 
            "Integer");

        checkOutDate = Date.valueOf(MiscUtils.dateConvertor(year, month, day));

        reservationStatus = ReservationStatuses.PENDING;

        Reservation newReservation = new Reservation(reservationID, numberOfAdults, numberOfChildren, guests, reservedRoom, creditCardUsed, checkInDate, checkOutDate, reservationStatus);
        
        return newReservation;
    }

    public boolean manageDeleteEntry(Reservation toDelete) {
        return true;
    }
}
