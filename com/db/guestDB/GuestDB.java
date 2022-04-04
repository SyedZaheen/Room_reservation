package com.db.guestDB;

import java.util.List;
import com.db.DB;
import com.models.Guest;

public class GuestDB implements DB<Guest>{

    @Override
    public boolean createEntry(Guest entry) {
        // TODO change the below to false
        return true;
    }

    @Override
    public List<Guest> findAllEntries() {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean updateEntry(Guest guest) {
        return false;
    }


    
}
