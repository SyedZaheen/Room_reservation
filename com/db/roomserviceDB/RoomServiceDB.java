package com.db.roomserviceDB;

import java.util.ArrayList;
import java.util.List;
import com.db.DB;
import com.db.SerializeDB;
import com.models.RoomService;

public class RoomServiceDB implements DB<RoomService> {

    private final String ROOMSERVICE_DB_FILE_NAME = "/roomserviceDB/listOfOrders";
    private List<RoomService> listOfOrders = null;

    @Override
    public boolean createEntry(RoomService entry) {
        // We want to synchronise the listOfGuests with the files stored inside the .ser
        // file
        if (entry == null) return false;
        listOfOrders = findAllEntries();
        listOfOrders.add(entry);
        return SerializeDB.<RoomService>writeSerializedObject(DB.FILE_PATH + ROOMSERVICE_DB_FILE_NAME, listOfOrders);
    }

    @Override
    public List<RoomService> findAllEntries() {
        return SerializeDB.<RoomService>readSerializedObject(DB.FILE_PATH + ROOMSERVICE_DB_FILE_NAME);
    }

    public boolean updateEntry(RoomService rs) {
        int rsID = rs.getOrderID();
        listOfOrders = findAllEntries();
        List<RoomService> newlist = new ArrayList<>();
        boolean found = false;
        for (RoomService eachRoomService : listOfOrders) {
            if (rs.getRoomServiceID() == eachRoomService.getRoomServiceID()) {
                newlist.add(rs);
                found = true;
            } else {
                newlist.add(eachRoomService);
            }
        }
        return SerializeDB.<RoomService>writeSerializedObject(DB.FILE_PATH + ROOMSERVICE_DB_FILE_NAME, newlist)
                && found;
    }

    @Override
    public boolean isEmpty() {
        return findAllEntries().isEmpty();
    }

    public boolean deleteEntry(RoomService toDelete) {
        if (toDelete == null)
            return false;
        boolean found = false;
        List<RoomService> newList = new ArrayList<>();
        for (RoomService roomService : findAllEntries()) {
            if (roomService.getRoomServiceID() == toDelete.getRoomServiceID()) {
                found = true;
                continue;
            }
            newList.add(roomService);
        }
        SerializeDB.writeSerializedObject(DB.FILE_PATH + ROOMSERVICE_DB_FILE_NAME, newList);
        return found;
    }

}

