package com.db.guestDB;
import java.util.ArrayList;
import java.util.List;
import com.db.DB;
import com.db.SerializeDB;
import com.models.Guest;

public class GuestDB implements DB<Guest>{

    private final String GUEST_DB_FILE_NAME = "listOfGuests";
    private List<Guest> listOfGuests = new ArrayList<>();

    
    @Override
    public boolean createEntry(Guest entry) {
        // We want to synchronise the listOfGuests with the files stored inside the .ser file
        for (Guest eachGuest : findAllEntries() ) {
            listOfGuests.add(eachGuest);
        }
        listOfGuests.add(entry);
        return SerializeDB.<Guest>writeSerializedObject(GUEST_DB_FILE_NAME, listOfGuests);
    }

    @Override
    public List<Guest> findAllEntries() {
        return SerializeDB.<Guest>readSerializedObject(GUEST_DB_FILE_NAME);
    }

    public boolean updateEntry(Guest guest) {
        return false;
    }


    
}
