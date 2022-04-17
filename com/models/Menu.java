package com.models;

import com.db.menuDB.MenuItemDB;
import java.util.List;

/**
 * Represents the menu for the hotel
 * @author DSF 1 Group 1
 *
 */
public class Menu {
    private int menuSize = 0;
    private List<MenuItem> items; 
    
    /**
     * Pulls all the menu items from the menu database upon calling the menu object
     */

    public Menu() {
        items = new MenuItemDB().findAllEntries();
        for (MenuItem m : items) {
            this.menuSize++;
        }
    }

    public List<MenuItem> getItems() {
        return items;
    }
    
    /**
     * Pulls all the menu item from the menu database
     * @return A string of all the menu's items
     */

    public String[] getAllItemsInString() {
        List<MenuItem> items = new MenuItemDB().findAllEntries();
        if (items == null) return new String[]{};

        String[] s = new String[menuSize];
        for (int i = 0; i < menuSize; i++) {
            s[i] = items.get(i).toString();
        }
        return s;
    }
}
