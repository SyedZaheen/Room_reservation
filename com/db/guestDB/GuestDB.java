package com.db.guestDB;

import java.util.List;
import com.db.DB;
import com.db.SerializeDB;
import com.models.Guest;

public class GuestDB implements DB<Guest> {

    private final String GUEST_DB_FILE_NAME = "guestDB/listOfGuests.ser";
    private List<Guest> listOfGuests = null;

    @Override
    public boolean createEntry(Guest entry) {
        // We want to synchronise the listOfGuests with the files stored inside the .ser
        // file
        listOfGuests = findAllEntries();
        listOfGuests.add(entry);
        return SerializeDB.<Guest>writeSerializedObject(DB.FILE_PATH + GUEST_DB_FILE_NAME, listOfGuests);
    }

    @Override
    public List<Guest> findAllEntries() {
        return SerializeDB.<Guest>readSerializedObject(DB.FILE_PATH + GUEST_DB_FILE_NAME);
    }

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

    public boolean isEmpty() {
        return findAllEntries().size() == 0;
    }

    public boolean checkDuplicate(String name) {

        for (Guest eachGuest : findAllEntries()) {
            if (name.toLowerCase().equals(eachGuest.getName().toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    public boolean checkDuplicate(int number) {
        for (Guest eachGuest : findAllEntries()) {
            if (number == eachGuest.getGuestID()) {
                return false;
            }
        }
        return true;
    }

}