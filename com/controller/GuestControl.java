package com.controller;

import com.models.Guest;
import com.utils.FrontendUtils;
import com.utils.MiscUtils;

public abstract class GuestControl {

    public static void process() {
        // First, we need to see if we need to create, read, update or delete guest.
        int choice = FrontendUtils.getUserChoice(new String[] {
                "Create a new guest",
                "Update guest details",
                "Delete guest"
        });
        // For each, we call the corresponding function.
        switch (choice) {
            case 1:
                manageCreateGuest();
                break;

            case 2:
                manageUpdateGuest();
            default:
                break;
        }

    }

    private static void manageCreateGuest() {
        // Initialise the guest data that we want
        String name, address, country, gender, nationality, identity;
        Integer contact, creditCardDetails;

        // First we ask the user to give us the data that we want
        name = FrontendUtils.<String>getEachFieldFromUser(
                "Please enter your name: ",
                "Error. please enter a string between 5 and 50 characters long",
                i -> MiscUtils.stringWithinLength(i, 5, 50),
                "String");

        address = FrontendUtils.<String>getEachFieldFromUser(
                "Please enter your address: ",
                "Error. please enter a string between 5 and 50 characters long",
                i -> MiscUtils.stringWithinLength(i, 5, 50),
                "String");

        country = FrontendUtils.<String>getEachFieldFromUser(
                "Please enter your country: ",
                "Error. please enter a string less than 20 characters long",
                i -> MiscUtils.stringWithinLength(i, 1, 20),
                "String");

        gender = FrontendUtils.<String>getEachFieldFromUser(
                "Please enter your gender (may not be male or female): ",
                "Error. Please enter a string less than 10 characters long",
                i -> MiscUtils.stringWithinLength(i, 1, 10),
                "String");

        nationality = FrontendUtils.<String>getEachFieldFromUser(
                "Please enter your nationality: ",
                "Error. please enter a string less than 20 characters long",
                i -> MiscUtils.stringWithinLength(i, 1, 20),
                "String");

        contact = FrontendUtils.<Integer>getEachFieldFromUser(
                "Please enter a valid Singapore number (without country code, +65): ",
                "Error. Please enter a valid Singapore number (8 digits starting with 6, 8 or 9)",
                i -> MiscUtils.isValidSingaporeNumber(i),
                "Integer");

                //todo: Find discuss the validation of credit card number
        creditCardDetails = FrontendUtils.<Integer>getEachFieldFromUser(
                "Please enter the last 4 digits of your credit card number: ",
                "Error. Please enter 4 digits only",
                i -> i >= 1e3 && i < 1e4,
                "Integer");

                //todo: Find discuss the validation of identity number
        identity = FrontendUtils.<String>getEachFieldFromUser(
                    "Please enter the last 4 characters of your ID number: ",
                    "Error. Please enter 4 characters only",
                    i -> MiscUtils.stringWithinLength(i, 4, 4),
                    "String");
        // we format the data in a way to send to the database

        System.out.println("The following is the data so far: ");
        Guest guest = new Guest(name, creditCardDetails, address, country, gender, identity, nationality, contact);
        System.out.println(guest.name+ guest.contact.toString()+ guest.address+ guest.creditCardDetails.toString()+ guest.identity);
    }

    private static void manageUpdateGuest() {
    }
}
