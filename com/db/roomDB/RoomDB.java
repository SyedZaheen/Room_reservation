package com.db.roomDB;

import java.time.*;
import java.util.List;

import com.db.DB;
import com.db.SerializeDB;
import com.db.reservationDB.ReservationDB;
import com.enums.RoomStatuses;
import com.enums.RoomTypes;
import com.models.Reservation;
import com.models.Room;
/**
 * The class that provides the API for interacting with the serializer DB for storing rooms 
 * @author DSF1 Group 1
 */
public class RoomDB implements DB<Room> {

    private final String ROOM_DB_FILE_NAME = "roomDB/all_rooms_data.ser";
    List<Room> listOfRooms = null;

    
    /** 
     * @param room
     * @return true if database operation successfully completed, false if database opertation fails
     */
    @Override
    public boolean createEntry(Room room) {
        listOfRooms.add(room);
        return SerializeDB.writeSerializedObject(
            DB.FILE_PATH + ROOM_DB_FILE_NAME, listOfRooms);
    }

    
    /** 
     * @return List<Room>
     */
    @Override
    public List<Room> findAllEntries() {
        return SerializeDB.readSerializedObject(
                DB.FILE_PATH + ROOM_DB_FILE_NAME);
    }

    
    /** 
     * @return true if database operation successfully completed, false if database opertation fails
     */
    @Override
    public boolean isEmpty() {
        return findAllEntries().isEmpty();
    }

    
    /** 
     * @param type
     * @return Room
     */
    public Room findVacantRoomByType(RoomTypes type) {
        List<Room> allEntries = findAllEntries();
        for (Room room : allEntries)
            if (room.getStatus() == RoomStatuses.VACANT && room.getRoomType() == type)
                return room;

        return null;
    }

    
    /** 
     * @param toUpdate
     * @param rst
     * @return true if database operation successfully completed, false if database opertation fails
     */
    public boolean updateRoomStatus(Room toUpdate, RoomStatuses rst) {
        if (toUpdate == null)
            return false;
        int rmnumber = toUpdate.getRoomNumber();
        listOfRooms = findAllEntries();
        for (int i = 0; i < listOfRooms.size(); i++) {
            if (listOfRooms.get(i).getRoomNumber().equals(rmnumber)) {
                toUpdate.setStatus(rst);
                listOfRooms.set(i, toUpdate);
                SerializeDB.<Room>writeSerializedObject(DB.FILE_PATH + ROOM_DB_FILE_NAME, listOfRooms);
                return true;
            }
        }
        return false;
    }

    
    /** 
     * @param rType
     * @param cID
     * @param cOD
     * @return Room
     */
    public Room findVacantRoom(RoomTypes rType, LocalDate cID, LocalDate cOD) {
        listOfRooms = findAllEntries();
        ReservationDB rdb = new ReservationDB();

        List<Reservation> reservations = rdb.findAllEntries();
        boolean vacant = true;
        for (Room room : listOfRooms) {
            if (room.getRoomType() == rType) {
                vacant = true;
                for (Reservation reservation : reservations) {
                    if (reservation.getReservedRoom().getRoomNumber().equals(room.getRoomNumber())) {
                        if (rdb.reservationClash(reservation, cID, cOD))
                            vacant = false;
                            break;
                    }
                }
                if (vacant)
                    return room;
            }
        }
        return null;
    }

    
    /** 
     * @param roomnumber
     * @return Room
     */
    public Room findSingleEntry(int roomnumber) {
        List<Room> rooms = findAllEntries();
        for (Room room : rooms) {
            if (room.getRoomNumber().equals(roomnumber))
                return room;
        }
        return null;
    }
}
