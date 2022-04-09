package com.db.reservationDB;

import java.util.List;
import java.util.ArrayList;
import com.db.DB;
import com.db.SerializeDB;
import com.models.Reservation;

import java.sql.Date;

public class ReservationDB implements DB<Reservation> {

    private final String RESERVATION_DB_FILE_NAME = "listOfReservations";
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
        return SerializeDB.<Reservation>readSerializedObject(RESERVATION_DB_FILE_NAME);
    }

    public boolean updateEntry(Reservation resv) {
        return false;
    }

    public boolean searchByDate(Date checkInDate) {
        Date start, end;
        int startDay, endDay;
        for (Reservation eachReservation : findAllEntries()) {
            start = eachReservation.getCheckInDate();
            end = eachReservation.getCheckOutDate();
            
            if(checkInDate == start)
                return true;

            startDay = (start.toString().charAt(8) - '0') + 
                       (start.toString().charAt(9));

            endDay = (end.toString().charAt(8) - '0') + 
                     (end.toString().charAt(9));

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
}
