package com.db.reservationDB;

import com.db.DB;
import com.db.SerializeDB;
import com.db.guestDB.GuestDB;
import com.db.roomDB.RoomDB;
import com.enums.ReservationStatuses;
import com.enums.RoomStatuses;
import com.models.Guest;
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

        return SerializeDB.<Reservation>writeSerializedObject(DB.FILE_PATH + RESERVATION_DB_FILE_NAME,
                listOfReservations);
    }

    @Override
    public List<Reservation> findAllEntries() {
        return SerializeDB.readSerializedObject(
                DB.FILE_PATH + RESERVATION_DB_FILE_NAME);
    }

    public boolean updateEntry(Reservation resv) {
        if (resv == null)
            return false;
        int rmnumber = resv.getReservationID();
        listOfReservations = findAllEntries();
        for (int i = 0; i < listOfReservations.size(); i++) {
            if (rmnumber == listOfReservations.get(i).getReservationID()) {
                listOfReservations.set(i, resv);
                SerializeDB.<Reservation>writeSerializedObject(DB.FILE_PATH + RESERVATION_DB_FILE_NAME,
                        listOfReservations);
                return true;
            }
        }
        return false;
    }

    public boolean deleteEntry(Reservation r) {
        if (r == null)
            return false;
        GuestDB gDb = new GuestDB();
        RoomDB rDB = new RoomDB();
        // find the guest, delete

        for (Guest g : r.getGuests()) {
            gDb.deleteEntry(g);
        }

        // Find the room, update
        rDB.updateRoomStatus(r.getReservedRoom(), RoomStatuses.VACANT);

        // delete reservation
        List<Reservation> newList = new ArrayList<>();
        for (Reservation res : findAllEntries()) {
            if (res.getReservationID() == r.getReservationID()) {
                continue;
            }
            newList.add(res);
        }
        SerializeDB.writeSerializedObject(DB.FILE_PATH + RESERVATION_DB_FILE_NAME, newList);
        return true;
    }

    public boolean roomIsOccupied(Room requestedRoom, LocalDate checkInDate, LocalDate checkoutDate) {

        boolean vacant = true;

        for (Reservation reservation : findAllEntries()) {
            if (reservation.getReservedRoom().getRoomNumber() == requestedRoom.getRoomNumber()) {
                if (reservationClash(reservation, checkInDate, checkoutDate))
                    vacant = false;
            }
        }
        return !vacant;
    }

    public Reservation findSingleEntry(Integer reservationID) {
        for (Reservation eachReservation : findAllEntries()) {
            if (reservationID == eachReservation.getReservationID())
                return eachReservation;
        }

        return null;
    }

    public Reservation findSingleEntry(String guestName) {

        for (Reservation eachReservation : findAllEntries()) {
            List<Guest> guests = eachReservation.getGuests();
            for (Guest g : guests) {
                if (g.getName().equals(guestName)) {
                    return eachReservation;
                }
            }
        }

        return null;
    }

    public boolean checkIfHotelIsFull(LocalDate checkInDate, LocalDate checkOutDate) {
        // Get all reservations
        boolean isFull = true;
        RoomDB rdb = new RoomDB();
        List<Room> rooms = rdb.findAllEntries();
        for (Room room : rooms) {
            if (!roomIsOccupied(room, checkInDate, checkOutDate))
                isFull = false;
        }
        return isFull;
    }

    public boolean reservationClash(Reservation target, LocalDate checkInDate, LocalDate checkOutDate) {
        // only no clash if checkindate AFTER target.checkout AND tartget.chein AFTER
        // cehckout
        return !(target.getCheckInDate().compareTo(checkOutDate) >= 0
                && checkInDate.compareTo(target.getCheckOutDate()) >= 0);
    }

    @Override
    public boolean isEmpty() {
        return findAllEntries().size() == 0;
    }
}
