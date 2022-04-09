package com.controller;

import com.db.guestDB.GuestDB;
import com.enums.IDType;
import com.models.CreditCard;
import com.models.Guest;
import com.utils.FrontendUtils;
import com.utils.MiscUtils;

public class GuestControl implements Controller<Guest> {

        public void process() {
                Guest newguest = null;
                boolean success = false;

                // First, we need to see if we need to create, read, update or delete guest.
                int choice = FrontendUtils.getUserChoice(new String[] {
                                "Create a new guest",
                                "Update guest details",
                                "See all guest details",
                                "Delete guest"
                });
                // For each, we call the corresponding function.
                switch (choice) {
                        case 1:
                                newguest = manageCreateEntry();
                                // Let's send the guest to the database.
                                // todo: This design is pretty shit but idk how to fix it.
                                success = new GuestDB().createEntry(newguest);

                                if (success) {
                                        System.out.println(
                                                        "A new guest was sucessfully created! These are the saved guest data: ");
                                        System.out.println(newguest);
                                } else
                                        System.out.println(
                                                        "Something went wrong trying to save the guest data. Contact the administrators");
                                break;
                        case 2:
                                newguest = manageUpdateEntry();
                                success = new GuestDB().updateEntry(newguest);

                                if (success) {
                                        System.out.println(
                                                        "The guest details were successfully updated! These are the current guest data: ");
                                        System.out.println(newguest);
                                } else
                                        System.out.println(
                                                        "Something went wrong trying to save the guest data. Contact the administrators");
                                break;
                        case 3:
                                System.out.println("The following are all the available guest data so far: ");
                                for (Guest eachGuest : new GuestDB().findAllEntries()) {
                                        System.out.println("");
                                        System.out.println(eachGuest);
                                }
                                break;
                        default:
                                break;
                }
        }

        @Override
        public Guest manageCreateEntry() {
                // Initialise the guest data that we want
                String name, address, country, gender, nationality, identity;
                int contact;
                IDType idType;
                boolean isPayingGuest;
                CreditCard creditCard = null;

                // First we ask the user to give us the simple data that we want
                name = FrontendUtils.<String>getEachFieldFromUser(
                                "Please enter the full name: ",
                                "Error. please enter a string between 3 and 50 characters long",
                                i -> MiscUtils.stringWithinLength(i, 3, 50),
                                "String");

                address = FrontendUtils.<String>getEachFieldFromUser(
                                "Please enter the address: ",
                                "Error. Please enter a string between 5 and 50 characters long",
                                i -> MiscUtils.stringWithinLength(i, 5, 50),
                                "String");

                country = FrontendUtils.<String>getEachFieldFromUser(
                                "Please enter the country: ",
                                "Error. please enter a string less than 20 characters long",
                                i -> MiscUtils.stringWithinLength(i, 2, 20),
                                "String");

                gender = FrontendUtils.<String>getEachFieldFromUser(
                                "Please e8nter the gender (may not be male or female): ",
                                "Error. Please enter a string less than 10 characters long",
                                i -> MiscUtils.stringWithinLength(i, 1, 10),
                                "String");

                nationality = FrontendUtils.<String>getEachFieldFromUser(
                                "Please enter the nationality: ",
                                "Error. please enter a string less than 20 characters long",
                                i -> MiscUtils.stringWithinLength(i, 1, 20),
                                "String");

                contact = FrontendUtils.<Integer>getEachFieldFromUser(
                                "Please enter a valid Singapore number (without country code, +65): ",
                                "Error. Please enter a valid Singapore number (8 digits starting with 6, 8 or 9)",
                                i -> MiscUtils.isValidSingaporeNumber(i),
                                "Integer");

                // Now, we have to handle credit cards and IDs

                // Lets handle ID first
                // Note that guests have a "composition" relationship with IDs.
                int idChoice = FrontendUtils.getUserChoice(new String[] {
                                "Enter 1 if the guest's ID for registration is a passport",
                                "Enter 2 if the guest's ID for registration is a driving license"
                });
                idType = idChoice == 1 ? IDType.PASSPORT : IDType.DRIVING_LICENSE;

                // todo: Find discuss the validation of identity number
                identity = FrontendUtils.<String>getEachFieldFromUser(
                                "Please enter the last 4 characters of the ID number: ",
                                "Error. Please enter 4 characters only",
                                i -> MiscUtils.stringWithinLength(i, 4, 4),
                                "String");

                // Now, we settle the credit card info.
                // First, we see if the guest is paying or not
                int payingChoice = FrontendUtils.getUserChoice(new String[] {
                                "Enter 1 if the guest is paying for the reservation",
                                "Enter 2 if the guest is not paying for the reservation"
                });

                isPayingGuest = payingChoice == 1;

                // Next, if the guest is paying, then we get their credit card info
                if (isPayingGuest) {
                        creditCard = new CreditCardControl().manageCreateEntry();
                } else
                        creditCard = null;

                Guest newGuest = new Guest(name, address, country, gender, nationality, contact, idType, identity,
                                isPayingGuest, creditCard);

                if (!FrontendUtils.<Guest>userDoubleConfirmDetails(newGuest))
                        newGuest = manageCreateEntry();
                return newGuest;
        }

        public Guest manageCreateEntry(boolean isPaying) {
                // Initialise the guest data that we want
                String name, address, country, gender, nationality, identity;
                int contact;
                IDType idType;
                CreditCard creditCard = null;

                // First we ask the user to give us the simple data that we want
                name = FrontendUtils.<String>getEachFieldFromUser(
                                "Please enter the full name: ",
                                "Error. please enter a string between 3 and 50 characters long",
                                i -> MiscUtils.stringWithinLength(i, 3, 50),
                                "String");

                address = FrontendUtils.<String>getEachFieldFromUser(
                                "Please enter the address: ",
                                "Error. Please enter a string between 5 and 50 characters long",
                                i -> MiscUtils.stringWithinLength(i, 5, 50),
                                "String");

                country = FrontendUtils.<String>getEachFieldFromUser(
                                "Please enter the country: ",
                                "Error. please enter a string less than 20 characters long",
                                i -> MiscUtils.stringWithinLength(i, 2, 20),
                                "String");

                gender = FrontendUtils.<String>getEachFieldFromUser(
                                "Please e8nter the gender (may not be male or female): ",
                                "Error. Please enter a string less than 10 characters long",
                                i -> MiscUtils.stringWithinLength(i, 1, 10),
                                "String");

                nationality = FrontendUtils.<String>getEachFieldFromUser(
                                "Please enter the nationality: ",
                                "Error. please enter a string less than 20 characters long",
                                i -> MiscUtils.stringWithinLength(i, 1, 20),
                                "String");

                contact = FrontendUtils.<Integer>getEachFieldFromUser(
                                "Please enter a valid Singapore number (without country code, +65): ",
                                "Error. Please enter a valid Singapore number (8 digits starting with 6, 8 or 9)",
                                i -> MiscUtils.isValidSingaporeNumber(i),
                                "Integer");

                // Now, we have to handle credit cards and IDs

                // Lets handle ID first
                // Note that guests have a "composition" relationship with IDs.
                int idChoice = FrontendUtils.getUserChoice(new String[] {
                                "Enter 1 if the guest's ID for registration is a passport",
                                "Enter 2 if the guest's ID for registration is a driving license"
                });
                idType = idChoice == 1 ? IDType.PASSPORT : IDType.DRIVING_LICENSE;

                // todo: Find discuss the validation of identity number
                identity = FrontendUtils.<String>getEachFieldFromUser(
                                "Please enter the last 4 characters of the ID number: ",
                                "Error. Please enter 4 characters only",
                                i -> MiscUtils.stringWithinLength(i, 4, 4),
                                "String");

                // Now, we settle the credit card info.

                // Next, if the guest is paying, then we get their credit card info
                if (isPaying) {
                        creditCard = new CreditCardControl().manageCreateEntry();
                } else
                        creditCard = null;

                Guest newGuest = new Guest(name, address, country, gender, nationality, contact, idType, identity,
                                isPaying, creditCard);

                if (!FrontendUtils.<Guest>userDoubleConfirmDetails(newGuest))
                        newGuest = manageCreateEntry();
                return newGuest;
        }

        public Guest manageUpdateEntry() {
                return null;
        }
}
