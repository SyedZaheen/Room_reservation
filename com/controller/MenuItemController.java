package com.controller;

import com.db.menuDB.MenuItemDB;
import com.models.MenuItem;
import com.utils.MiscUtils;
import com.views.UserInputViews;

/**
 * MenuItemController.java
 * A controller class that realises the CreatorController interface.
 * It consists of methods that will enable its users to control the outcome of a MenuItem object.
 * 
 * @author DSF1 Group 1
 */
public class MenuItemController implements CreatorController<MenuItem> {

    
    /** 
     * manageCreateEntry()
     * It is a method that would create a new MenuItem object. If the object created is valid, it will be subsequently serialised into the MenuItemDB.
     * 
     * @return the MenuItem object that is created.
     */
    @Override
    public MenuItem manageCreateEntry() {
        MenuItemDB db = new MenuItemDB();
        // Ask the user for the menu item name, description, and price;
        double price;
        String name, description;

        name = UserInputViews.<String>getEachFieldFromUser("Please enter the menu item name",
                "Error, please input a string between 2 and 15 characters long",
                i -> MiscUtils.stringWithinLength(i, 2, 15), "String");

        description = UserInputViews.<String>getEachFieldFromUser("Please enter the item description",
                "Error, please input a string between 1 and 50 characters long",
                i -> MiscUtils.stringWithinLength(i, 1, 50), "String");

        price = UserInputViews.<Double>getEachFieldFromUser("Please enter the price name",
                "Error, please input a number between 0.5 and 100",
                i -> i > 0.5 && i < 100, "Double");

        // Create the item, send to the DB
        MenuItem item = new MenuItem(name, description, price);
        if (db.createEntry(item))
            return item;
        return null;
    }

    
    /** 
     * manageMutateEntry()
     * It is a method that would mutate an existing MenuItem object. 
     * If the object updated is valid, it will be subsequently serialised into the MenuItemDB.
     * 
     * @return MenuItem
     */
    public MenuItem manageMutateEntry() {
        String name, description;
        double price;
        MenuItemDB db = new MenuItemDB();
        name = UserInputViews.<String>getEachFieldFromUser(
                "Please enter the menu item name that you would like to view",
                "Error, please input a string between 2 and 15 characters long",
                i -> MiscUtils.stringWithinLength(i, 2, 15), "String");
        MenuItem item = db.findSingleEntry(name);

        if (item == null) {
            System.out.println("Could not find an item with that name! Check your spelling?");
            return null;
        }
        System.out.println("Menu item found! Here are the details: ");
        System.out.println(item);

        System.out.println("\nWhat would you like to do with this item?");

        int choice = UserInputViews.getUserChoice(new String[] {
                "Update menu item",
                "Delete menu item (please double confirm you'd like to do this!)"
        });

        if (choice == 1) {
            description = UserInputViews.<String>getEachFieldFromUser("What would be the updated description?",
                    "Error, please input a string between 1 and 50 characters long",
                    i -> MiscUtils.stringWithinLength(i, 1, 50), "String");

            price = UserInputViews.<Double>getEachFieldFromUser("What would be the updated price be?",
                    "Error, please input a number between 0.5 and 100",
                    i -> i > 0.5 && i < 100, "Double");

            MenuItem newitem = new MenuItem(name, description, price);
            if (db.deleteEntry(item) && db.createEntry(newitem))
                return item;
            else
                return null;
        }

        if (choice == 2) {
            if (db.deleteEntry(item))
                return item;
            else
                return null;
        }

        return null;
    }

    
    /**
     * getDB()
     * This is a getter function that gets the ReservationDB.
     * 
     * @return the MenuItemDB.
     */
    public MenuItemDB getDB() {
        return new MenuItemDB();
    }
}
