package com.db.menuDB;

import java.util.ArrayList;
import java.util.List;

import com.db.DB;
import com.db.SerializeDB;
import com.models.MenuItem;

/**
 * The class that provides the API for interacting with the serializer DB for storing menu items 
 * @author DSF1 Group 1
 */
public class MenuItemDB implements DB<MenuItem> {
    private final String MENU_DB_FILE_NAME = "/menuDB/all_menu_items.ser";
    List<MenuItem> listOfMenuItems = null;

    
    /** 
     * Takes in a menu item object to append to the ArrayList of menu item objects in the database
     * @param entry: A guest object 
     * @return boolean 
     */
    @Override
    public boolean createEntry(MenuItem entry) {
        listOfMenuItems = findAllEntries();
        if (listOfMenuItems == null)
            return false;
        listOfMenuItems.add(entry);
        return SerializeDB.writeSerializedObject(DB.FILE_PATH + MENU_DB_FILE_NAME, listOfMenuItems);
    }

    
    /** 
     * Gets all entries in the DB
     * @return List<MenuItem>
     */
    @Override
    public List<MenuItem> findAllEntries() {
        return SerializeDB.readSerializedObject(
                DB.FILE_PATH + MENU_DB_FILE_NAME);
    }

    
    /** 
     * @param itemname
     * @return MenuItem
     */
    public MenuItem findSingleEntry(String itemname) {
        List<MenuItem> items = findAllEntries();
        if (items != null)
            for (MenuItem menuItem : items) {
                if (itemname.toLowerCase().equals(menuItem.getName().toLowerCase()))
                    return menuItem;
            }
        return null;
    }

    
    /** Takes in a menu item to delete. Returns true if successfully deleted
     * @param item
     * @return boolean
     */
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

    
    /** 
     * Returns true if the guestDB is empty
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        return findAllEntries().isEmpty();
    }

}
