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

public class RoomDB implements DB<Room> {

    private final String ROOM_DB_FILE_NAME = "roomDB/all_rooms_data.ser";
    List<Room> listOfRooms = null;

    @Override
    public boolean createEntry(Room room) {
        listOfRooms.add(room);
        return SerializeDB.writeSerializedObject(
            DB.FILE_PATH + ROOM_DB_FILE_NAME, listOfRooms);
    }

    @Override
    public List<Room> findAllEntries() {
        return SerializeDB.readSerializedObject(
                DB.FILE_PATH + ROOM_DB_FILE_NAME);
    }

    @Override
    public boolean isEmpty() {
        return findAllEntries().isEmpty();
    }

    public Room findVacantRoomByType(RoomTypes type) {
        List<Room> allEntries = findAllEntries();
        for (Room room : allEntries)
            if (room.getStatus() == RoomStatuses.VACANT && room.getRoomType() == type)
                return room;

        return null;
    }

    public boolean updateRoomStatus(Room toUpdate, RoomStatuses rst) {
        if (toUpdate == null)
            return false;
        int rmnumber = toUpdate.getRoomNumber();
        listOfRooms = findAllEntries();
        for (int i = 0; i < listOfRooms.size(); i++) {
            if (rmnumber == listOfRooms.get(i).getRoomNumber()) {
                toUpdate.setStatus(rst);
                listOfRooms.set(i, toUpdate);
                SerializeDB.<Room>writeSerializedObject(DB.FILE_PATH + ROOM_DB_FILE_NAME, listOfRooms);
                return true;
            }
        }
        return false;
    }

    public Room findVacantRoom(RoomTypes rType, LocalDate cID, LocalDate cOD) {
        listOfRooms = findAllEntries();
        ReservationDB rdb = new ReservationDB();

        List<Reservation> reservations = rdb.findAllEntries();
        boolean vacant = true;
        for (Room room : listOfRooms) {
            if (room.getRoomType() == rType) {
                vacant = true;
                for (Reservation reservation : reservations) {
                    if (reservation.getReservedRoom().getRoomNumber() == room.getRoomNumber()) {
                        if (rdb.reservationClash(reservation, cID, cOD))
                            vacant = false;
                    }
                }
                if (vacant)
                    return room;
            }
        }
        return null;
    }

    public Room findSingleEntry(int roomnumber) {
        List<Room> rooms = findAllEntries();
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomnumber)
                return room;
        }
        return null;
    }
}
