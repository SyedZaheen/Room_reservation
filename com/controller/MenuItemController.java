package com.controller;

import com.db.menuDB.MenuItemDB;
import com.models.MenuItem;
import com.utils.MiscUtils;
import com.views.UserInputViews;

public class MenuItemController implements CreatorController<MenuItem> {

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

        price = UserInputViews.<Double>getEachFieldFromUser("Please enter the menu item name",
                "Error, please input a number between 0.5 and 100",
                i -> i > 0.5 && i < 100, "Double");

        // Create the item, send to the DB
        MenuItem item = new MenuItem(name, description, price);
        if (db.createEntry(item))
            return item;
        return null;
    }

    public MenuItem manageMutateEntry() {
        return null;
    }

}
