package com.controller;
import com.utils.FrontendUtils;
import com.utils.MiscUtils;

public abstract class GuestControl {

    public static void process(){
        // First, we need to see if we need to create, read, update or delete guest.
        int choice = FrontendUtils.getUserChoice(new String[]{
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

    
    private static void manageCreateGuest()
    {
        // Initialise the guest data that we want
        String name, address, country, gender, nationality, identity;
        Integer contact, creditCardDetails;

        // First we ask the user to give us the data that we want
        name = FrontendUtils.<String>getEachFieldFromUser(
            "Please enter your name: ",
            "Error. please enter a string between 5 and 50 characters long",
            i-> MiscUtils.stringWithinLength(i,5,50),
            "String"
        );

        address = FrontendUtils.<String>getEachFieldFromUser(
            "Please enter your address: ",
            "Error. please enter a string between 5 and 50 characters long",
            i-> MiscUtils.stringWithinLength(i,5,50),
            "String"
        );

        country = FrontendUtils.<String>getEachFieldFromUser(
            "Please enter your country: ",
            "Error. please enter a string less than 20 characters long",
            i-> MiscUtils.stringWithinLength(i,1,20),
            "String"
        );

        gender = FrontendUtils.<String>getEachFieldFromUser(
            "Please enter your gender (may not be male or female): ",
            "Error. Please enter a string less than 10 characters long",
            i-> MiscUtils.stringWithinLength(i,1,10),
            "String"
        );

        nationality = FrontendUtils.<String>getEachFieldFromUser(
            "Please enter your nationality: ",
            "Error. please enter a string less than 20 characters long",
            i-> MiscUtils.stringWithinLength(i,1,20),

            "String"
        ); 

        contact = FrontendUtils.<Integer>getEachFieldFromUser(
            "Please enter a valid Singapore number (without country code, +65): ",
            "Error. please enter a valid Singapore number",
            i-> MiscUtils.isValidSingaporeNumber(i),
            "Integer"
        );

        // we format the data in a way to send to the database
    }

    private static void manageUpdateGuest() {
    }
}
