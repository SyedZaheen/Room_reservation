package com.models;

import com.db.menuDB.MenuItemDB;
import java.util.List;

public class Menu {
    private int menuSize = 0;
    private List<MenuItem> items; 

    public Menu() {
        items = new MenuItemDB().findAllEntries();
        for (MenuItem m : items) {
            this.menuSize++;
        }
    }

    public List<MenuItem> getItems() {
        return items;
    }

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
