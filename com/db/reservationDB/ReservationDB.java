package com.db.reservationDB;

import com.db.DB;
import com.db.SerializeDB;
import com.db.roomDB.RoomDB;
import com.models.Reservation;
import com.models.Room;

import java.util.List;
import java.util.ArrayList;
import java.sql.Date;

public class ReservationDB implements DB <Reservation> {

    private final String RESERVATION_DB_FILE_NAME = "reservationDB/all_reservations_data.ser";
    private List<Reservation> listOfReservations = new ArrayList<>();

    @Override
    public boolean createEntry(Reservation resv) {
        for (Reservation eachReservation : findAllEntries()) {
            listOfReservations.add(eachReservation);
        }

        listOfReservations.add(resv);

        return SerializeDB.<Reservation>writeSerializedObject(RESERVATION_DB_FILE_NAME, listOfReservations);
    }

    @Override
    public List<Reservation> findAllEntries() {
        return SerializeDB.readSerializedObject(
            DB.FILE_PATH + RESERVATION_DB_FILE_NAME);
    }

    // TODO : To implement method
    public boolean updateEntry(Reservation resv) {
        return false;
    }

    // TODO : Flawed logic for time series.
    public boolean searchByDate(Date checkInDate) {
        Date start, end;
        int startDay, endDay;
        for (Reservation eachReservation : findAllEntries()) {
            start = eachReservation.getCheckInDate();
            end = eachReservation.getCheckOutDate();
            
            if(checkInDate == start)
                return true;

            startDay = ((start.toString().charAt(8) - '0') * 10) + 
                       (start.toString().charAt(9) - '0');

            endDay = ((end.toString().charAt(8) - '0') * 10) + 
                     (end.toString().charAt(9) -  '0');

            for(int i = startDay + 1; i < endDay; i++) {
                int secondDigit = i % 10;
                int firstDigit = i - secondDigit;

                char first = (char) (firstDigit + '0');
                char second = (char) (secondDigit + '0');
                char targetF = start.toString().charAt(8);
                char targetS = start.toString().charAt(9);

                String date = start.toString().replace(targetF, first);
                date.replace(targetS, second);

                Date nextDate = Date.valueOf(date);

                if(checkInDate == nextDate)
                    return true;
            }
        }

        return false;
    }

    public boolean deleteEntry(Reservation r) {
        // TODO
        return false;
    }

    // TODO: Unimplmented function
    public boolean searchByRoom(Room requestedRoom) {
        if(new RoomDB().findVacantRoomByType(requestedRoom.getRoomType()) == null)
            return true;
        
        return false;
    }

    public boolean isOccupied(Room requestedRoom, Date checkInDate) {
        if(searchByRoom(requestedRoom) && searchByDate(checkInDate))
            return true;

        return false;
    }

    public Reservation findReservationByID(Integer reservationID) {
        for(Reservation eachReservation : findAllEntries()) {
            if(reservationID == eachReservation.getReservationID())
                return eachReservation;
        }

        return null;
    }
}
