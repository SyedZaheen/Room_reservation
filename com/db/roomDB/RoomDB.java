package com.db.roomDB;

import java.util.List;

import com.db.DB;
import com.db.SerializeDB;
import com.models.Room;
import com.utils.AnonymousFunction;

public class RoomDB implements DB<Room> {

    private final String ROOM_DB_FILE_NAME = "roomDB/all_rooms_data.ser";

    // todo: Create entry doesnt make sense here
    @Override
    public boolean createEntry(Room room) {

        return false;
    }

    @Override
    public List<Room> findAllEntries() {
        return SerializeDB.readSerializedObject(
                DB.FILE_PATH + ROOM_DB_FILE_NAME);
    }


    // public Room findRoomByVacancy(
            
    //         AnonymousFunction<Boolean, Room> validator
            
    //         ) {
    //     List<Room> allEntries = findAllEntries();
    //     for (Room room : allEntries) {
    //         if (validator.execute(room))
    //             return room;
    //     }
    //     return null;
    // }

}
