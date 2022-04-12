package com.controller;

import com.db.guestDB.GuestDB;
import com.db.reservationDB.ReservationDB;
import com.enums.ReservationStatuses;
import com.enums.PaymentType;
import com.models.CreditCard;
import com.models.Guest;
import com.models.Reservation;
import com.models.Room;
import com.utils.MiscUtils;
import com.views.UserInputViews;

import java.time.*;
import java.util.ArrayList;

public class ReservationControl
        implements CreatorController<Reservation>, UpdatorController<Reservation> {

    @Override
    public Reservation manageCreateEntry() {
        ReservationDB db = new ReservationDB();
        GuestDB gdb = new GuestDB();
        Guest nonpayingguest = null;

        Integer numberOfAdults, numberOfChildren, year, day;
        ArrayList<Guest> guests = new ArrayList<>();
        PaymentType paymentType;
        CreditCard creditCardUsed;
        Room reservedRoom;
        LocalDate checkInDate, checkOutDate;
        ReservationStatuses reservationStatus;
        Guest payingGuest = null;
        boolean isValidDates = false;
        System.out.println("Is the guest a walk-in or advanced reservation?");
        boolean isWalkIn = UserInputViews.getUserChoice(new String[] {
                "Walk-in Reservation",
                "Advanced Reservation"
        }) == 1;

        numberOfAdults = UserInputViews.getEachFieldFromUser(
                "Please enter the number of non-paying adults: ",
                "Please enter a non-negative number up to 5.",
                i -> (i >= 0 && i <= 5),
                "Integer");

        numberOfChildren = UserInputViews.<Integer>getEachFieldFromUser(
                "Please enter the number of children: ",
                "Please enter a non-negative number up to 5.",
                i -> (i >= 0 && i <= 5),
                "Integer");

        if (numberOfAdults + numberOfChildren != 0) {
            System.out.println("Please enter the details for the non-paying guests: ");
            for (int j = 0; j < numberOfAdults + numberOfChildren; j++) {
                System.out.printf("Please enter the details of the number %d non-paying guest: \n", j+1);
                nonpayingguest = new GuestControl().manageCreateEntry(false);
                if (!gdb.createEntry(nonpayingguest))
                    System.out.println("Something went wrong with trying to save the guest details. Contact the administrators. ");
                guests.add(nonpayingguest);
            }
        }

        System.out.println("Please enter the details for the paying adult: ");
        payingGuest = new GuestControl().manageCreateEntry(true);
        if (!gdb.createEntry(payingGuest))
            return null;
        guests.add(payingGuest);

        paymentType = payingGuest.getPaymentType();
        creditCardUsed = payingGuest.getCreditCard();

        checkInDate = LocalDate.now();
        do {
            if (!isWalkIn) {
                year = UserInputViews.<Integer>getEachFieldFromUser(
                        "Please enter the year (For Check-In): ",
                        "Error. Please enter a valid year!",
                        i -> MiscUtils.isValidYear(i),
                        "Integer");

                final Integer monthIn = UserInputViews.<Integer>getEachFieldFromUser(
                        "Please enter the month (For Check-In): ",
                        "Error. Please enter a valid month!",
                        i -> MiscUtils.isValidMonth(i),
                        "Integer");

                day = UserInputViews.<Integer>getEachFieldFromUser(
                        "Please enter the day (For Check-In): ",
                        "Error. Please enter a valid day!",
                        i -> MiscUtils.isValidDay(i, monthIn),
                        "Integer");

                checkInDate = LocalDate.of(year, monthIn, day);
            }

            year = UserInputViews.<Integer>getEachFieldFromUser(
                    "Please enter the year (For Check-Out): ",
                    "Error. Please enter a valid year!",
                    i -> MiscUtils.isValidYear(i),
                    "Integer");

            final Integer monthOut = UserInputViews.<Integer>getEachFieldFromUser(
                    "Please enter the month (For Check-Out): ",
                    "Error. Please enter a valid month!",
                    i -> MiscUtils.isValidMonth(i),
                    "Integer");

            day = UserInputViews.<Integer>getEachFieldFromUser(
                    "Please enter the day (For Check-Out): ",
                    "Error. Please enter a valid day!",
                    i -> MiscUtils.isValidDay(i, monthOut),
                    "Integer");

            checkOutDate = LocalDate.of(year, monthOut, day);

            isValidDates = checkOutDate.compareTo(checkInDate) > 0 && !MiscUtils.dateBeforeNow(checkInDate);
            if (!isValidDates)
                System.out.println(
                        "The checkin date is ahead of the checkout date OR the checkin date is before today! Try again!\n");
        } while (!isValidDates);

        boolean hotelIsFull = db.checkIfHotelIsFull(checkInDate, checkOutDate);
        if (hotelIsFull) {
            System.out.println(
                    "It seems that all of the hotel rooms are full during this timing! Select from the options below: ");
            int choice = UserInputViews.getUserChoice(new String[] {
                    "Create a new reservation",
                    "Go back to menu"
            });

            if (choice == 1)
                return manageCreateEntry();
            else
                return null;
        }

        reservedRoom = new RoomControl().manageCreateEntry(checkInDate, checkOutDate);
        if (reservedRoom == null)
            return null;

        reservationStatus = isWalkIn ? ReservationStatuses.CHECKED_IN : ReservationStatuses.CONFIRMED;

        Integer reservationID = MiscUtils.generateID();

        Reservation newReservation = new Reservation(reservationID, numberOfAdults, numberOfChildren, guests,
                reservedRoom, paymentType, creditCardUsed, checkInDate, checkOutDate, reservationStatus);

        if (!UserInputViews.<Reservation>userDoubleConfirmDetails(newReservation))
            newReservation = manageCreateEntry();

        return newReservation;
    }

    @Override
    public Reservation manageUpdateEntry() {

        System.out.println("Reservation to be updated (Search by ID): ");
        Integer key = UserInputViews.getEachFieldFromUser(
                "Please enter the reservation ID: ",
                "Error. Please enter a 7 digit number.",
                i -> MiscUtils.isValidID(i),
                "Integer");

        Reservation toUpdate = new ReservationDB().findSingleEntry(key);
        if (toUpdate == null) {
            System.out.println(
                    "Reservation ID does not exist! Check the full reservation list to see if the ID is correct");
            return null;
        }

        System.out.println("\nReservation found! The following are the reservation details: ");
        System.out.println(toUpdate);

        System.out.println("\nPlease choose reservation status to update to: ");
        int choice = UserInputViews.getUserChoice(new String[] {
                "Reservation in Waitlist.",
                "Reservation Confirmed.",
                "Reservation Checked-in.",
                "Reservation Expired",
                "Return to menu"
        });

        switch (choice) {
            case 1:
                toUpdate.setReservationStatus(ReservationStatuses.IN_WAITLIST);
                break;

            case 2:
                toUpdate.setReservationStatus(ReservationStatuses.CONFIRMED);
                break;

            case 3:
                toUpdate.setReservationStatus(ReservationStatuses.CHECKED_IN);
                break;

            case 4:
                toUpdate.setReservationStatus(ReservationStatuses.EXPIRED);
                break;
            default:
                return null;
        }

        return toUpdate;
    }

    public Reservation manageDeleteEntry() {
        System.out.println("Reservation to be delete (Search by ID): ");
        Integer key = UserInputViews.getEachFieldFromUser(
                "Please enter the reservation ID: ",
                "Error. Please enter a 7 digit number.",
                i -> MiscUtils.isValidID(i),
                "Integer");

        Reservation toDelete = new ReservationDB().findSingleEntry(key);
        if (toDelete == null) {
            System.out.println(
                    "Reservation ID does not exist! Check the full reservation list to see if the ID is correct");
            return null;
        }

        System.out.println("\nReservation found! The following are the reservation details: ");
        System.out.println(toDelete);
        System.out.println("\nPlease confirm that you would like to delete this reservation: ");
        int choice = UserInputViews.getUserChoice(new String[] {
                "Confirm delete reservation",
                "Return to menu"
        });

        switch (choice) {
            case 1:
                return toDelete;

            default:
                return null;
        }

    }
}
