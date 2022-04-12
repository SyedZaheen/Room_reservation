package com.db.menuDB;

import java.util.ArrayList;
import java.util.List;

import com.db.DB;
import com.db.SerializeDB;
import com.models.MenuItem;

public class MenuItemDB implements DB<MenuItem> {

    private final String MENU_DB_FILE_NAME = "/menuDB/all_menu_items.ser";
    List<MenuItem> listOfMenuItems = null;

    @Override
    public boolean createEntry(MenuItem entry) {
        listOfMenuItems = findAllEntries();
        if (listOfMenuItems == null)
            return false;
        listOfMenuItems.add(entry);
        return SerializeDB.writeSerializedObject(DB.FILE_PATH + MENU_DB_FILE_NAME, listOfMenuItems);
    }

    @Override
    public List<MenuItem> findAllEntries() {
        return SerializeDB.readSerializedObject(
                DB.FILE_PATH + MENU_DB_FILE_NAME);
    }

    public MenuItem findSingleEntry(String itemname) {
        List<MenuItem> items = findAllEntries();
        if (items != null)
            for (MenuItem menuItem : items) {
                if (itemname.toLowerCase().equals(menuItem.getName().toLowerCase()))
                    return menuItem;
            }
        return null;
    }

    public boolean deleteEntry(MenuItem item) {
        boolean found = false;
        List<MenuItem> newList = new ArrayList<>();

        for (MenuItem menuItem : findAllEntries()) {
            if (item.getName().toLowerCase().equals(menuItem.getName().toLowerCase())) {
                found = true;
                continue;
            }
            newList.add(menuItem);
        }
        return SerializeDB.writeSerializedObject(DB.FILE_PATH + MENU_DB_FILE_NAME, newList) && found;
    }

    @Override
    public boolean isEmpty() {
        return findAllEntries().size() == 0;
    }

}
