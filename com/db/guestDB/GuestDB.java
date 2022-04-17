package com.db.guestDB;

import java.util.ArrayList;
import java.util.List;
import com.db.DB;
import com.db.SerializeDB;
import com.models.Guest;
/**
 * The class that provides the API for interacting with the serializer DB for storing Guests 
 * @author DSF1 Group 1
 */
public class GuestDB implements DB<Guest> {

    private final String GUEST_DB_FILE_NAME = "guestDB/listOfGuests.ser";
    private List<Guest> listOfGuests = null;

    
    /** 
     * Takes in a guest object to append to the ArrayList of guest objects in the database
     * @param entry: A guest object 
     * @return boolean 
     */
    @Override
    public boolean createEntry(Guest entry) {
        // We want to synchronise the listOfGuests with the files stored inside the .ser
        // file
        listOfGuests = findAllEntries();
        listOfGuests.add(entry);
        return SerializeDB.<Guest>writeSerializedObject(DB.FILE_PATH + GUEST_DB_FILE_NAME, listOfGuests);
    }

    
    /** 
     * Gets all entries in the DB
     * @return List<Guest>
     */
    @Override
    public List<Guest> findAllEntries() {
        return SerializeDB.<Guest>readSerializedObject(DB.FILE_PATH + GUEST_DB_FILE_NAME);
    }

    
    /** 
     * Takes in a guest object and searches the DB for another guest object with the same ID.
     * Once found, replaces the Guest Object in the database with the param guest object (updating).
     * @param Guest: guest object
     * @return boolean
     */
    public boolean updateEntry(Guest guest) {
        if (guest == null)
            return false;

        int gID = guest.getGuestID();

        listOfGuests = findAllEntries();

        for (int i = 0; i < listOfGuests.size(); i++) {
            if (gID == listOfGuests.get(i).getGuestID()) {
                listOfGuests.set(i, guest);
                SerializeDB.<Guest>writeSerializedObject(DB.FILE_PATH + GUEST_DB_FILE_NAME, listOfGuests);
                return true;
            }
        }
        return false;
    }

    
    /** 
     * Takes in a guest name to return the guest object
     * @param name: guest name in string
     * @return Guest object 
     */
    public Guest findSingleEntry(String name) {
        Guest match = null, cur = null;
        listOfGuests = findAllEntries();
        for (int i = 0; i < listOfGuests.size(); i++) {
            cur = listOfGuests.get(i);
            if (name.toLowerCase().equals(cur.getName().toLowerCase())) {
                match = cur;
                break;
            }
        }
        return match;
    }

    
    /** 
     * Takes in a guest id to return the guest object
     * @param id: guest id in int
     * @return Guest object
     */
    public Guest findSingleEntry(int id) {
        Guest match = null, cur = null;
        listOfGuests = findAllEntries();
        for (int i = 0; i < listOfGuests.size(); i++) {
            cur = listOfGuests.get(i);
            if (id == cur.getGuestID()) {
                match = cur;
                break;
            }
        }
        return match;
    }

    
    /** 
     * Returns true if the DB is empty
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        return findAllEntries().isEmpty();
    }

    
    /** Takes in a guests object and deletes it. returns true if deletion is successful  
     * @param toDelete: Guest object to delete
     * @return boolean
     */
    public boolean deleteEntry(Guest toDelete) {
        if (toDelete == null)
            return false;
        boolean found = false;
        List<Guest> newList = new ArrayList<>();
        for (Guest guest : findAllEntries()) {
            if (guest.getGuestID() == toDelete.getGuestID()) {
                found = true;
                continue;
            }
            newList.add(guest);
        }
        SerializeDB.writeSerializedObject(DB.FILE_PATH + GUEST_DB_FILE_NAME, newList);
        return found;

    }

    
    /** 
     * Returns true of the name is a duplicate
     * @param name
     * @return boolean
     */
    public boolean checkDuplicate(String name) {

        for (Guest eachGuest : findAllEntries()) {
            if (name.toLowerCase().equals(eachGuest.getName().toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    
    /** 
     * Returns true if the ID is a duplicate
     * @param number
     * @return boolean
     */
    public boolean checkDuplicate(int number) {
        for (Guest eachGuest : findAllEntries()) {
            if (number == eachGuest.getGuestID()) {
                return false;
            }
        }
        return true;
    }

}