package com.controller;
import com.utils.FrontendUtils;

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
        // First we ask the user to give us the data that we want. 
        // we format the data in a way to send to the database
    }

    private static void manageUpdateGuest() {
    }
}
