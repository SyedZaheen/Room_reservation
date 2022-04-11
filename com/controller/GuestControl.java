package com.controller;

import java.util.List;

import com.Views;
import com.db.guestDB.GuestDB;
import com.enums.IDType;
import com.enums.PaymentType;
import com.models.CreditCard;
import com.models.Guest;
import com.utils.MiscUtils;

public class GuestControl implements UpdatorController<Guest> {

        public void process() {
                Guest newguest = null;
                boolean success = false;
                GuestDB db = new GuestDB();

                int choice;

                MiscUtils.printTransition();
                while (true) {
                        choice = Views.getUserChoice(new String[] {
                                        "See all guest details",
                                        "Update guest details",
                                        "Find guest by name",
                                        "Return to main menu"
                        });

                        // For each, we call the corresponding function.
                        switch (choice) {
                                case 1:
                                        if (db.isEmpty()) {
                                                System.out.println("There are no guests in the hotel currently!");
                                                break;
                                        }
                                        List<Guest> guests = db.findAllEntries();
                                        System.out.println("The following are all the available guest data so far: ");
                                        for (Guest eachGuest : guests) {
                                                System.out.println("");
                                                System.out.println(eachGuest);
                                        }
                                        break;
                                case 2:
                                        if (db.isEmpty()) {
                                                System.out.println("There are no guests in the hotel currently!");
                                                break;
                                        }
                                        newguest = manageUpdateEntry();
                                        if (newguest == null)
                                                break;

                                        success = db.updateEntry(newguest);

                                        if (success) {
                                                System.out.println(
                                                                "The guest details were successfully updated! These are the updated guest data: ");
                                                System.out.println(newguest);
                                        } else
                                                System.out.println(
                                                                "Something went wrong trying to save the guest data. Contact the administrators");
                                        break;
                                case 3:
                                        if (db.isEmpty()) {
                                                System.out.println("There are no guests in the hotel currently!");
                                                break;
                                        }
                                        String name = Views.<String>getEachFieldFromUser(
                                                        "Please enter the full name of the guest who you wish to search.",
                                                        "Error. please enter a string between 3 and 50 characters long",
                                                        i -> MiscUtils.stringWithinLength(i, 3, 50),
                                                        "String");
                                        newguest = db.findSingleEntry(name);
                                        if (newguest == null)
                                                System.out.println(
                                                                "Could not find that guest in the system! Check your spelling maybe?");
                                        else {
                                                System.out.println(
                                                                "\nThe following are the guest details requested: \n");
                                                System.out.println(newguest);
                                        }
                                        break;
                                default:
                                        return;

                        }
                }
        }

        public Guest manageCreateEntry(boolean isPaying) {
                // Initialise the guest data that we want
                String name, address, country, gender, nationality, identity;
                int contact;
                IDType idType;
                PaymentType paymentType = PaymentType.NA;
                CreditCard creditCard = null;
                GuestDB db = new GuestDB();

                // First we ask the user to give us the simple data that we want
                name = Views.<String>getEachFieldFromUser(
                                "Please enter the full name: ",
                                "Error. please enter a string between 3 and 50 characters long. That name may already be taken!",
                                i -> MiscUtils.stringWithinLength(i, 3, 50) && db.checkDuplicate(i),
                                "String");

                address = Views.<String>getEachFieldFromUser(
                                "Please enter the address: ",
                                "Error. Please enter a string between 5 and 50 characters long",
                                i -> MiscUtils.stringWithinLength(i, 5, 50),
                                "String");

                country = Views.<String>getEachFieldFromUser(
                                "Please enter the country: ",
                                "Error. please enter a string less than 20 characters long",
                                i -> MiscUtils.stringWithinLength(i, 2, 20),
                                "String");

                gender = Views.<String>getEachFieldFromUser(
                                "Please e8nter the gender (may not be male or female): ",
                                "Error. Please enter a string less than 10 characters long",
                                i -> MiscUtils.stringWithinLength(i, 1, 10),
                                "String");

                nationality = Views.<String>getEachFieldFromUser(
                                "Please enter the nationality: ",
                                "Error. please enter a string less than 20 characters long",
                                i -> MiscUtils.stringWithinLength(i, 1, 20),
                                "String");

                contact = Views.<Integer>getEachFieldFromUser(
                                "Please enter a valid Singapore number (without country code, +65): ",
                                "Error. Please enter a valid Singapore number (8 digits starting with 6, 8 or 9)",
                                i -> MiscUtils.isValidSingaporeNumber(i),
                                "Integer");

                // Now, we have to handle credit cards and IDs

                // Lets handle ID first
                // Note that guests have a "composition" relationship with IDs.
                int idChoice = Views.getUserChoice(new String[] {
                                "Enter 1 if the guest's ID for registration is a passport",
                                "Enter 2 if the guest's ID for registration is a driving license"
                });
                idType = idChoice == 1 ? IDType.PASSPORT : IDType.DRIVING_LICENSE;

                // todo: Find discuss the validation of identity number
                identity = Views.<String>getEachFieldFromUser(
                                "Please enter the last 4 characters of the ID number: ",
                                "Error. Please enter 4 characters only",
                                i -> MiscUtils.stringWithinLength(i, 4, 4),
                                "String");

                // Now, we settle the credit card info.

                // Next, if the guest is paying, then we get their credit card info
                if (isPaying) {
                        int paymentTypeChoice = Views.getUserChoice(new String[] {
                                        "Enter 1 if the guest is paying by creditcard for the reservation",
                                        "Enter 2 if the guest is paying by cash for the reservation"
                        });

                        if (paymentTypeChoice == 1) {
                                paymentType = PaymentType.CREDITCARD;
                                creditCard = new CreditCardControl().manageCreateEntry();
                        } else if (paymentTypeChoice == 2) {
                                paymentType = PaymentType.CASH;
                                creditCard = null;
                        }
                } else
                        paymentType = PaymentType.NA;

                Guest newGuest = new Guest(name, address, country, gender, nationality, contact, idType, identity,
                                isPaying, paymentType, creditCard);

                if (!Views.<Guest>userDoubleConfirmDetails(newGuest))
                        newGuest = manageCreateEntry(isPaying);

                return newGuest;
        }

        public Guest manageUpdateEntry() {
                GuestDB db = new GuestDB();
                Guest toUpdate = null, newGuest = null;
                System.out.println("How would you want to find the guest which you'd like to update?");
                int choice = Views.getUserChoice(new String[] {
                                "By name",
                                "By ID"
                });
                if (choice == 1) {
                        String name = Views.<String>getEachFieldFromUser(
                                        "Please enter the full name of the guest who you wish to search.",
                                        "Error. please enter a string between 3 and 50 characters long",
                                        i -> MiscUtils.stringWithinLength(i, 3, 50),
                                        "String");
                        toUpdate = db.findSingleEntry(name);
                } else {
                        int id = Views.<Integer>getEachFieldFromUser(
                                        "Please enter the Guest ID of the guest who you wish to search.",
                                        "Error. Please enter a number 7 digits long",
                                        i -> MiscUtils.isValidID(i),
                                        "Integer");
                        toUpdate = db.findSingleEntry(id);
                }

                if (toUpdate == null) {
                        System.out.println("Could not find a guest with that name or ID!");
                        return null;
                }

                System.out.println("\nThe following is the current guest object: \n");
                System.out.println(toUpdate);
                System.out.println("Please enter all of the relevant updated details about this guest: ");
                newGuest = manageCreateEntry(toUpdate.getIsPayingGuest());
                newGuest.setGuestID(toUpdate.getGuestID());
                if (db.updateEntry(toUpdate))
                        return newGuest;
                return null;
        }
}
