package com.views;

import java.util.List;

import com.controller.ReservationControl;
import com.models.Reservation;
import com.utils.MiscUtils;

public class ReservationViews implements Views {
    @Override
    public void process() {
        Reservation reservation = null;

        ReservationControl rsvc = new ReservationControl();

        while (true) {
            int choice = UserInputViews.getUserChoice(new String[] {
                    "Create a new reservation (walk-in or advanced)",
                    "Update reservation status",
                    "Print all reservations (Reservation IDs and paying guest names)",
                    "Find reservation by reservation ID or paying guest name",
                    "Delete entry",
                    "Go back to main menu"
            });
            switch (choice) {
                case 1:
                    reservation = rsvc.manageCreateEntry();
                    if (reservation == null)
                    {
                        System.out.println("Something went wrong. Contact the administrators");
                        break;
                    }
                    System.out.println(
                            "A new reservation was successfully created! These are the saved reservation data: ");
                    System.out.println(reservation);

                    break;

                case 2:
                    if (rsvc.getDB().isEmpty()) {
                        System.out.println("There are no reservations for any room currently");
                        break;
                    }
                    reservation = rsvc.manageUpdateEntry();
                    if (reservation == null)
                    {
                        System.out.println("Something went wrong. Contact the administrators");
                        break;
                    }

                    System.out.println(
                            "The reservation was successfully updated! These are the current reservation data: ");

                    System.out.println(reservation);

                    break;

                case 3:

                    List<Reservation> r = rsvc.getDB().findAllEntries();
                    if (r.isEmpty()) {
                        System.out.println("There are no reservations for any room currently");
                        break;
                    }
                    MiscUtils.printLightTransition();
                    System.out.println("The following are all the reservations in the DB currently: ");
                    for (Reservation eachReservation : r) {
                        System.out.println("");
                        System.out.println("Reservation ID: " + eachReservation.getReservationID());
                        System.out.println("Paying Guest Name: " + eachReservation.getPayingGuest().getName());
                    }

                    break;

                case 4:
                    if (rsvc.getDB().isEmpty()) {
                        System.out.println("There are no reservations for any room currently");
                        break;
                    }

                    choice = UserInputViews.getUserChoice(new String[] {
                            "Search reservation by guest name",
                            "Search reservation by reservation ID",
                            "Return to menu"
                    });

                    if (choice == 1) {
                        boolean found = false;
                        String key = UserInputViews.<String>getEachFieldFromUser(
                                "Please enter the name of the paying adult for the reservation: ",
                                "Error. please enter a string between 3 and 50 characters long.",
                                i -> MiscUtils.stringWithinLength(i, 3, 50),
                                "String");

                        Reservation foundReservation = rsvc.getDB().findSingleEntry(key);
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
                        Integer key = UserInputViews.<Integer>getEachFieldFromUser(
                                "Please enter the reservation ID: ",
                                "Error. Please enter a 7 digit number",
                                i -> MiscUtils.isValidID(i),
                                "Integer");

                        Reservation foundReservation = rsvc.getDB().findSingleEntry(key);
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
                    if (rsvc.getDB().isEmpty()) {
                        System.out.println("There are no reservations for any room currently");
                        break;
                    }
                    reservation = rsvc.manageDeleteEntry();
                    if (reservation == null)
                    {
                        System.out.println("Something went wrong. Contact the administrators");
                        break;
                    }

                    System.out.println(
                            "The reservation with the following details were successfully deleted: \n");

                    System.out.println(reservation);
                    System.out.println(
                            "\nNote: the guest details were also deleted, and the room status has been updated");

                    break;
                case 6:
                    return;
            }
        }
    }
}
