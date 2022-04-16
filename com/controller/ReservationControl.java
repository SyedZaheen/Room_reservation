package com.controller;

import com.db.reservationDB.ReservationDB;
import com.db.roomDB.RoomDB;
import com.enums.ReservationStatuses;
import com.enums.PaymentType;
import com.models.CreditCard;
import com.models.Guest;
import com.models.Reservation;
import com.models.Room;
import com.utils.FrontendUtils;
import com.utils.MiscUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

<<<<<<< Updated upstream
public class ReservationControl implements Controller<Reservation> {

    public void process() {
        Reservation reservation = null;
        boolean success = false;

        int choice = FrontendUtils.getUserChoice(new String[] {
                "Create a new reservation",
                "Update reservation",
                "Print all reservation IDs and paying guest name",
                "Find reservation by guest name",
                "Delete reservation"
        });

        switch (choice) {
            case 1:
                reservation = manageCreateEntry();
                if (reservation == null)
                    return;

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
                if (reservation == null)
                    return;

                success = new ReservationDB().updateEntry(reservation);

                if (success) {
                    System.out.println(
                            "A new reservation was successfully updated! These are the current reservation data: ");

                    System.out.println(reservation);
                } else
                    System.out.println(
                            "Something went wrong trying to save the reservation data. Contact the administrators.");

                break;

            case 3:
                try {
                    List<Reservation> r = new ReservationDB().findAllEntries();
                    if (r.size() == 0) {
                        System.out.println("There are no reservations currently");
                        break;
                    }
                    for (Reservation eachReservation : r) {
                        System.out.println("Reservation ID: " + eachReservation.getReservationID());
                        System.out.println("Paying Guest Name: " + eachReservation.getPayingGuest().getName());
                    }

                } catch (NullPointerException npe) {
                    System.out.println("There are no reservations currently");
                }

                break;

            case 4:
                String key = FrontendUtils.getEachFieldFromUser(
                        "Please enter the name of the paying adult for the reservation: ",
                        "Error. please enter a string between 3 and 50 characters long.",
                        i -> MiscUtils.stringWithinLength(i, 3, 50),
                        "String");

                for (Reservation eachReservation : new ReservationDB().findAllEntries()) {
                    int sizeOfList = eachReservation.getGuests().size();
                    if (key == eachReservation.getGuests().get(sizeOfList - 1).getName()) {
                        System.out.println(eachReservation);
                        break;
                    }
                }

                break;

            case 5:
                // Reservation toDelete = new
                // reservation = manageDeleteEntry(toDelete);
        }
    }
=======
/**
 * ReservationControl.java
 * A controller class that realises the CreatorController and UpdateController interface.
 * It consists of methods that will enable its users to control the outcome of a Reservation object.
 * 
 * @author DSF1 Group 1
 */
public class ReservationControl
        implements CreatorController<Reservation>, UpdatorController<Reservation> {
>>>>>>> Stashed changes

    /**
     * manageCreateEntry()
     * It is a method that would create a new Reservation object. If the object created is valid, it will be subsequently serialised into the ReservationDB.
     * 
     * @return the Reservation object that is created
     */
    @Override
    public Reservation manageCreateEntry() {

        Integer numberOfAdults, numberOfChildren, year, monthIn, monthOut, day;
        ArrayList<Guest> guests = new ArrayList<>();
        PaymentType paymentType;
        CreditCard creditCardUsed;
        Room reservedRoom;
        Date checkInDate, checkOutDate;
        ReservationStatuses reservationStatus;
        Guest payingGuest = null;

        numberOfAdults = FrontendUtils.getEachFieldFromUser(
                "Please enter the number of non-paying adults: ",
                "Please enter a non-negative number up to 5.",
                i -> (i >= 0 && i <= 5),
                "Integer");

        numberOfChildren = FrontendUtils.<Integer>getEachFieldFromUser(
                "Please enter the number of children: ",
                "Please enter a non-negative number up to 5.",
                i -> (i >= 0 && i <= 5),
                "Integer");

        System.out.println("Please enter the details for the non-paying guests: ");
        for (int j = 0; j < numberOfAdults + numberOfChildren; j++)
            guests.add(new GuestControl().manageCreateEntry(false));

        System.out.println("Please enter the details for the paying adult: ");
        payingGuest = new GuestControl().manageCreateEntry(true);
        guests.add(payingGuest);

        paymentType = payingGuest.getPaymentType();

        creditCardUsed = payingGuest.getCreditCard();

        year = FrontendUtils.<Integer>getEachFieldFromUser(
                "Please enter the year (For Check-In): ",
                "Error. Please enter a valid year!",
                i -> MiscUtils.isValidYear(i),
                "Integer");

        monthIn = FrontendUtils.<Integer>getEachFieldFromUser(
                "Please enter the month (For Check-In): ",
                "Error. Please enter a valid month!",
                i -> MiscUtils.isValidMonth(i),
                "Integer");

        day = FrontendUtils.<Integer>getEachFieldFromUser(
                "Please enter the day (For Check-In): ",
                "Error. Please enter a valid day!",
                i -> MiscUtils.isValidDay(i, monthIn),
                "Integer");

        checkInDate = Date.valueOf(MiscUtils.dateConvertor(year, monthIn, day));

        year = FrontendUtils.<Integer>getEachFieldFromUser(
                "Please enter the year (For Check-Out): ",
                "Error. Please enter a valid year!",
                i -> MiscUtils.isValidYear(i),
                "Integer");

        monthOut = FrontendUtils.<Integer>getEachFieldFromUser(
                "Please enter the month (For Check-Out): ",
                "Error. Please enter a valid month!",
                i -> MiscUtils.isValidMonth(i),
                "Integer");

        day = FrontendUtils.<Integer>getEachFieldFromUser(
                "Please enter the day (For Check-Out): ",
                "Error. Please enter a valid day!",
                i -> MiscUtils.isValidDay(i, monthOut),
                "Integer");

        checkOutDate = Date.valueOf(MiscUtils.dateConvertor(year, monthOut, day));

        boolean hotelIsFull = new RoomDB().checkIfHotelIsFull(checkInDate, checkOutDate);
        if (hotelIsFull) {
            System.out.println(
                    "It seems that all of the hotel rooms are full during this timing! Select from the options below: ");
            int choice = FrontendUtils.getUserChoice(new String[] {
                    "Choose new date",
                    "Go back to Main Menu"
            });

            if (choice == 1)
                return manageCreateEntry();
            else
                return null;
        }

        reservedRoom = new RoomControl().manageCreateEntry(checkInDate, checkOutDate);

        reservationStatus = ReservationStatuses.CONFIRMED;

        double lowerBound = 1e5, upperBound = 1e6;
        Integer reservationID = (int) Math.floor(Math.random() * (upperBound - lowerBound + 1) + lowerBound);

        Reservation newReservation = new Reservation(reservationID, numberOfAdults, numberOfChildren, guests,
                reservedRoom, paymentType, creditCardUsed, checkInDate, checkOutDate, reservationStatus);

        if (!FrontendUtils.<Reservation>userDoubleConfirmDetails(newReservation))
            newReservation = manageCreateEntry();

<<<<<<< Updated upstream
        return newReservation;
    }

    private Reservation manageUpdateEntry() {

=======
        for (Guest guest : newReservation.getGuests()) {
            new GuestDB().createEntry(guest);
        }
        return db.createEntry(newReservation) ? newReservation : null;
    }

    /**
     * manageUpdateEntry()
     * It is a method that would update the ReservationStatus of an existing Reservation object. 
     * If the object updated is valid, it will be subsequently serialised into the ReservationDB.
     * 
     * @return the Reservation object that is udpated
     */
    @Override
    public Reservation manageUpdateEntry() {
        ReservationDB db = new ReservationDB();
>>>>>>> Stashed changes
        System.out.println("Reservation to be updated (Search by ID): ");
        Integer key = FrontendUtils.getEachFieldFromUser(
                "Please enter the reservation ID: ",
                "Error. Please enter a 6 digit number.",
                i -> (i >= 1e6 && i < 1e7),
                "Integer");

        Reservation toUpdate = new ReservationDB().findReservationByID(key);
        if (toUpdate == null) {
            System.out.println("Reservation ID does not exist!");
            return null;
        }

        System.out.println("Please update reservation details: ");
        int choice = FrontendUtils.getUserChoice(new String[] {
                "Reservation in Waitlist.",
                "Reservation Confirmed.",
                "Reservation Checked-in.",
                "Reservation Expired"
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
        }

<<<<<<< Updated upstream
        return toUpdate;
    }

=======
        return db.updateEntry(toUpdate) ? toUpdate : null;
    }

    /**
     * manageDeleteEntry()
     * It is a method that deletes a Reservation that is in the ReservationDB.
     * 
     * @return the reservation that is deleted.
     */
    public Reservation manageDeleteEntry() {
        System.out.println("Reservation to be delete (Search by ID): ");
        Integer key = UserInputViews.getEachFieldFromUser(
                "Please enter the reservation ID: ",
                "Error. Please enter a 7 digit number.",
                i -> MiscUtils.isValidID(i),
                "Integer");
        ReservationDB db = new ReservationDB();
        Reservation toDelete = db.findSingleEntry(key);
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
                return db.deleteEntry(toDelete) ? toDelete : null;

            default:
                return null;
        }

    }

    /**
     * getDB()
     * This is a getter function that gets the ReservationDB.
     * 
     * @return the ReservationDB.
     */
    public ReservationDB getDB() {
        return new ReservationDB();
    }
>>>>>>> Stashed changes
}
