package com.controller;

import com.Views;
import com.db.guestDB.GuestDB;
import com.db.reservationDB.ReservationDB;
import com.enums.ReservationStatuses;
import com.enums.PaymentType;
import com.models.CreditCard;
import com.models.Guest;
import com.models.Reservation;
import com.models.Room;
import com.utils.MiscUtils;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationControl
        implements MasterController, CreatorController<Reservation>, UpdatorController<Reservation> {
    @Override
    public void process() {
        Reservation reservation = null;
        boolean success = false;
        ReservationDB rdb = new ReservationDB();
        while (true) {
            int choice = Views.getUserChoice(new String[] {
                    "Create a new reservation (walk-in or advanced)",
                    "Update reservation status",
                    "Print all reservations (Reservation IDs and paying guest names)",
                    "Find reservation by reservation ID or paying guest name",
                    "Delete entry",
                    "Go back to main menu"
            });
            switch (choice) {
                case 1:
                    reservation = manageCreateEntry();
                    if (reservation == null)
                        break;

                    success = rdb.createEntry(reservation);

                    if (success) {
                        System.out.println(
                                "A new reservation was successfully created! These are the saved reservation data: ");

                        System.out.println(reservation);
                    } else
                        System.out.println(
                                "Something went wrong trying to save the reservation data. Contact the adminstrators.");

                    break;

                case 2:
                    if (rdb.isEmpty()) {
                        System.out.println("There are no reservations for any room currently");
                        break;
                    }
                    reservation = manageUpdateEntry();
                    if (reservation == null)
                        return;

                    success = rdb.updateEntry(reservation);

                    if (success) {
                        System.out.println(
                                "The reservation was successfully updated! These are the current reservation data: ");

                        System.out.println(reservation);
                    } else
                        System.out.println(
                                "Something went wrong trying to save the reservation data. Contact the administrators.");

                    break;

                case 3:

                    List<Reservation> r = rdb.findAllEntries();
                    if (r.size() == 0) {
                        System.out.println("There are no reservations for any room currently");
                        break;
                    }
                    MiscUtils.printLightTransition();
                    System.out.println("The following are all the reservations in the DB currently: ");
                    for (Reservation eachReservation : r) {
                        System.out.println("");
                        System.out.println("Reservation ID: " + eachReservation.getReservationID() );
                        System.out.println("Paying Guest Name: " + eachReservation.getPayingGuest().getName());
                    }

                    break;

                case 4:
                    if (rdb.isEmpty()) {
                        System.out.println("There are no reservations for any room currently");
                        break;
                    }

                    choice = Views.getUserChoice(new String[] {
                            "Search reservation by guest name",
                            "Search reservation by reservation ID",
                            "Return to menu"
                    });

                    if (choice == 1) {
                        boolean found = false;
                        String key = Views.<String>getEachFieldFromUser(
                                "Please enter the name of the paying adult for the reservation: ",
                                "Error. please enter a string between 3 and 50 characters long.",
                                i -> MiscUtils.stringWithinLength(i, 3, 50),
                                "String");

                        Reservation foundReservation = rdb.findSingleEntry(key);
                        if (foundReservation != null) {
                            System.out.println("\nThe following are the relevant resevation data: ");
                            System.out.println(foundReservation);
                            System.out.println("");
                            found = true;
                        }

                        if (!found)
                            System.out.println("Could not find a reservation with that guest name");
                    }

                    if (choice == 2) {
                        boolean found = false;
                        Integer key = Views.<Integer>getEachFieldFromUser(
                                "Please enter the reservation ID: ",
                                "Error. Please enter a 7 digit number",
                                i -> MiscUtils.isValidID(i),
                                "Integer");

                        Reservation foundReservation = rdb.findSingleEntry(key);
                        if (foundReservation != null) {
                            System.out.println("The following are the relevant resevation data: ");
                            System.out.println(foundReservation);
                            System.out.println("");
                            found = true;
                        }

                        if (!found)
                            System.out.println("Could not find a reservation with that ID");
                    }
                    break;
                case 5:
                    if (rdb.isEmpty()) {
                        System.out.println("There are no reservations for any room currently");
                        break;
                    }
                    reservation = manageDeleteEntry();
                    if (reservation == null)
                        return;

                    success = rdb.deleteEntry(reservation);

                    if (success) {
                        System.out.println(
                                "The reservation with the following details were successfully deleted: \n");

                        System.out.println(reservation);
                        System.out.println(
                                "\nNote: the guest details were also deleted, and the room status has been updated");
                    } else
                        System.out.println(
                                "Something went wrong trying to save the reservation data. Contact the administrators.");

                    break;
                case 6:
                    return;
            }
        }
    }

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
        boolean isWalkIn = Views.getUserChoice(new String[] {
                "Walk-in Reservation",
                "Advanced Reservation"
        }) == 1;

        numberOfAdults = Views.getEachFieldFromUser(
                "Please enter the number of non-paying adults: ",
                "Please enter a non-negative number up to 5.",
                i -> (i >= 0 && i <= 5),
                "Integer");

        numberOfChildren = Views.<Integer>getEachFieldFromUser(
                "Please enter the number of children: ",
                "Please enter a non-negative number up to 5.",
                i -> (i >= 0 && i <= 5),
                "Integer");

        if (numberOfAdults + numberOfChildren != 0) {
            System.out.println("Please enter the details for the non-paying guests: ");
            for (int j = 0; j < numberOfAdults + numberOfChildren; j++)
                nonpayingguest = new GuestControl().manageCreateEntry(false);
            if (!gdb.createEntry(nonpayingguest))
                System.out.println(" TODO: DEBUG ");
            guests.add(nonpayingguest);
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
                year = Views.<Integer>getEachFieldFromUser(
                        "Please enter the year (For Check-In): ",
                        "Error. Please enter a valid year!",
                        i -> MiscUtils.isValidYear(i),
                        "Integer");

                final Integer monthIn = Views.<Integer>getEachFieldFromUser(
                        "Please enter the month (For Check-In): ",
                        "Error. Please enter a valid month!",
                        i -> MiscUtils.isValidMonth(i),
                        "Integer");

                day = Views.<Integer>getEachFieldFromUser(
                        "Please enter the day (For Check-In): ",
                        "Error. Please enter a valid day!",
                        i -> MiscUtils.isValidDay(i, monthIn),
                        "Integer");

                checkInDate = LocalDate.of(year, monthIn, day);
            }

            year = Views.<Integer>getEachFieldFromUser(
                    "Please enter the year (For Check-Out): ",
                    "Error. Please enter a valid year!",
                    i -> MiscUtils.isValidYear(i),
                    "Integer");

            final Integer monthOut = Views.<Integer>getEachFieldFromUser(
                    "Please enter the month (For Check-Out): ",
                    "Error. Please enter a valid month!",
                    i -> MiscUtils.isValidMonth(i),
                    "Integer");

            day = Views.<Integer>getEachFieldFromUser(
                    "Please enter the day (For Check-Out): ",
                    "Error. Please enter a valid day!",
                    i -> MiscUtils.isValidDay(i, monthOut),
                    "Integer");

            checkOutDate = LocalDate.of(year, monthOut, day);

            isValidDates = checkOutDate.compareTo(checkInDate) > 0 && !MiscUtils.dateBeforeNow(checkInDate);
            if (!isValidDates)
                System.out.println("The checkin date is ahead of the checkout date OR the checkin date is before today! Try again!\n");
        } while (!isValidDates);

        boolean hotelIsFull = db.checkIfHotelIsFull(checkInDate, checkOutDate);
        if (hotelIsFull) {
            System.out.println(
                    "It seems that all of the hotel rooms are full during this timing! Select from the options below: ");
            int choice = Views.getUserChoice(new String[] {
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

        if (!Views.<Reservation>userDoubleConfirmDetails(newReservation))
            newReservation = manageCreateEntry();

        return newReservation;
    }

    @Override
    public Reservation manageUpdateEntry() {

        System.out.println("Reservation to be updated (Search by ID): ");
        Integer key = Views.getEachFieldFromUser(
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
        int choice = Views.getUserChoice(new String[] {
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
        Integer key = Views.getEachFieldFromUser(
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
        int choice = Views.getUserChoice(new String[] {
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
