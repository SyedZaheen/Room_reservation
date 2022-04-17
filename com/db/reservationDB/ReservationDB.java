package com.db.reservationDB;

import com.db.DB;
import com.db.SerializeDB;
import com.db.guestDB.GuestDB;
import com.db.roomDB.RoomDB;
import com.enums.RoomStatuses;
import com.models.Guest;
import com.models.Reservation;
import com.models.Room;

import java.util.List;
import java.util.ArrayList;
import java.time.*;
/**
 * The class that provides the API for interacting with the serializer DB for storing reservations 
 * @author DSF1 Group 1
 */
public class ReservationDB implements DB<Reservation> {

    private final String RESERVATION_DB_FILE_NAME = "reservationDB/all_reservations_data";
    private List<Reservation> listOfReservations = new ArrayList<>();

    
    /** 
     * Takes in a reservation object and appends it to the list of reservation objects in the DB
     * @param reservation 
     * @return boolean
     */
    @Override
    public boolean createEntry(Reservation resv) {
        for (Reservation eachReservation : findAllEntries()) {
            listOfReservations.add(eachReservation);
        }

        listOfReservations.add(resv);

        return SerializeDB.<Reservation>writeSerializedObject(DB.FILE_PATH + RESERVATION_DB_FILE_NAME,
                listOfReservations);
    }

    
    /** 
     * Gets all entries in the DB
     * @return List<Reservation>
     */
    @Override
    public List<Reservation> findAllEntries() {
        return SerializeDB.readSerializedObject(
                DB.FILE_PATH + RESERVATION_DB_FILE_NAME);
    }

    
    /** 
     * Takes in a reservation object and searches the DB for another reservation object with the same ID.
     * Once found, replaces the reservation Object in the database with the param reservation object (updating).
     * @param reservation: reservation object
     * @return boolean
     */
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

    
    /** 
     * Deletes Reservation from the database
     * @param reservation
     * @return boolean
     */
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

    
    /** 
     * Returns true if the requested room is already not reserved between the checkIn and checkOut dates
     * @param requestedRoom
     * @param checkInDate
     * @param checkoutDate
     * @return boolean
     */
    public boolean roomIsOccupied(Room requestedRoom, LocalDate checkInDate, LocalDate checkoutDate) {

        boolean vacant = true;

        for (Reservation reservation : findAllEntries()) {
            if (reservation.getReservedRoom().getRoomNumber().equals(requestedRoom.getRoomNumber())) {
                if (reservationClash(reservation, checkInDate, checkoutDate))
                    vacant = false;
                    break;
            }
        }
        return !vacant;
    }

    
    /** 
     * Finds reservation by reservation ID
     * @param reservationID
     * @return Reservation
     */
    public Reservation findSingleEntry(Integer reservationID) {
        for (Reservation eachReservation : findAllEntries()) {
            if (reservationID == eachReservation.getReservationID())
                return eachReservation;
        }

        return null;
    }

    
    /** 
     * Finds reservation in DB by guest name
     * @param guestName
     * @return Reservation
     */
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

    
    /** 
     * Returns true if, between the given dates, all rooms are full
     * @param checkInDate
     * @param checkOutDate
     * @return boolean
     */
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

    
    /** 
     * Checks if there is a reservation clash between the given reservation param and the two dates
     * @param target
     * @param checkInDate
     * @param checkOutDate
     * @return boolean
     */
    public boolean reservationClash(Reservation target, LocalDate checkInDate, LocalDate checkOutDate) {
        // only no clash if checkindate AFTER target.checkout AND tartget.chein AFTER
        // cehckout

        return !(checkInDate.compareTo(target.getCheckOutDate()) >= 0
                || target.getCheckInDate().compareTo(checkOutDate) >= 0);
    }

    
    /** 
     * Returns true of the DB is empty
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        return findAllEntries().isEmpty();
    }
}
