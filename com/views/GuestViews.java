package com.views;

import java.util.List;

import com.controller.GuestControl;
import com.models.Guest;
import com.utils.MiscUtils;

public class GuestViews implements Views {
        @Override
        public void process() {
                Guest newguest = null;
                GuestControl controller = new GuestControl();
                int choice;

                MiscUtils.printTransition();
                while (true) {
                        choice = UserInputViews.getUserChoice(new String[] {
                                        "See all guest details",
                                        "Update guest details",
                                        "Find guest by name",
                                        "Return to main menu"
                        });

                        // For each, we call the corresponding function.
                        switch (choice) {
                                case 1:
                                        if (controller.getDB().isEmpty()) {
                                                System.out.println("There are no guests in the hotel currently!");
                                                break;
                                        }
                                        List<Guest> guests = controller.getDB().findAllEntries();
                                        System.out.println("The following are all the available guest data so far: ");
                                        for (Guest eachGuest : guests) {
                                                System.out.println("");
                                                System.out.println(eachGuest);
                                        }
                                        break;
                                case 2:
                                        if (controller.getDB().isEmpty()) {
                                                System.out.println("There are no guests in the hotel currently!");
                                                break;
                                        }
                                        newguest = controller.manageUpdateEntry();
                                        if (newguest == null)
                                                break;
                                        System.out.println(
                                                        "The guest details were successfully updated! These are the updated guest data: ");

                                        break;
                                case 3:
                                        if (controller.getDB().isEmpty()) {
                                                System.out.println("There are no guests in the hotel currently!");
                                                break;
                                        }
                                        String name = UserInputViews.<String>getEachFieldFromUser(
                                                        "Please enter the full name of the guest who you wish to search.",
                                                        "Error. please enter a string between 3 and 50 characters long",
                                                        i -> MiscUtils.stringWithinLength(i, 3, 50),
                                                        "String");
                                        newguest = controller.getDB().findSingleEntry(name);
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
}
