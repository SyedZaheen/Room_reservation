package com.db.reservationDB;
import java.util.List;
import com.db.DB;
import com.models.Reservation;

public class ReservationDB implements DB<Reservation>{

    @Override
    public boolean createEntry(Reservation resv) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Reservation> findAllEntries() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
