package com.db;


import java.util.List;
import com.db.DB;
import com.db.SerializeDB;
import com.models.RoomService;

public class RoomServiceDB implements DB<RoomService> {

    private final String ROOMSERVICE_DB_FILE_NAME = "listOfGuests";
    private List<RoomService> listOfOrders = null;

    @Override
    public boolean createEntry(RoomService entry) {
        // We want to synchronise the listOfGuests with the files stored inside the .ser
        // file
        listOfOrders = findAllEntries();
        listOfOrders.add(entry);
        return SerializeDB.<RoomService>writeSerializedObject(ROOMSERVICE_DB_FILE_NAME, listOfOrders);
    }

    @Override
    public List<RoomService> findAllEntries() {
        return SerializeDB.<RoomService>readSerializedObject(ROOMSERVICE_DB_FILE_NAME);
    }

    public boolean updateEntry(RoomService roomservice) {
        return false;
    }


}