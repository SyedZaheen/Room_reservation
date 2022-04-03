package com.db.guestDB;

import java.util.List;
import com.db.DB;
import com.models.Guest;

public class GuestDB implements DB<Guest>{

    @Override
    public boolean createEntry() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Guest> readEntries() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean updateEntry() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Guest deleteEntry() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
