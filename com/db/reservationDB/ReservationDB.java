package com.db.reservationDB;

import com.db.DB;
import com.db.SerializeDB;
import com.db.roomDB.RoomDB;
import com.models.Reservation;
import com.models.Room;

import java.util.List;
import java.util.ArrayList;
import java.time.*;

public class ReservationDB implements DB<Reservation> {

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
    public boolean searchByDate(LocalDate checkInDate) {
        return false;
    }

    public boolean deleteEntry(Reservation r) {
        // TODO
        return false;
    }

    // TODO: Unimplmented function
    public boolean searchByRoom(Room requestedRoom) {
        if (new RoomDB().findVacantRoomByType(requestedRoom.getRoomType()) == null)
            return true;

        return false;
    }

    public boolean isOccupied(Room requestedRoom, LocalDate checkInDate) {
        if (searchByRoom(requestedRoom) && searchByDate(checkInDate))
            return true;

        return false;
    }

    public Reservation findReservationByID(Integer reservationID) {
        for (Reservation eachReservation : findAllEntries()) {
            if (reservationID == eachReservation.getReservationID())
                return eachReservation;
        }

        return null;
    }

    @Override
    public boolean isEmpty() {
        return findAllEntries().size() == 0;
    }
}
