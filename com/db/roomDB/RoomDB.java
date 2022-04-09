package com.db.roomDB;

import java.util.List;
import java.sql.Date;

import com.db.DB;
import com.db.SerializeDB;
import com.enums.RoomStatuses;
import com.enums.RoomTypes;
import com.models.Room;
<<<<<<< HEAD
=======
<<<<<<< Updated upstream
import com.utils.AnonymousFunction;
=======
>>>>>>> Stashed changes
>>>>>>> nalin-payment

public class RoomDB implements DB<Room> {

    private final String ROOM_DB_FILE_NAME = "roomDB/all_rooms_data.ser";

    // todo: Create entry doesnt make sense here
    @Override
    public boolean createEntry(Room room) {
<<<<<<< Updated upstream

=======
        
>>>>>>> Stashed changes
        return false;
    }

    @Override
    public List<Room> findAllEntries() {
        return SerializeDB.readSerializedObject(
                DB.FILE_PATH + ROOM_DB_FILE_NAME);
    }

<<<<<<< Updated upstream

    public Room findVacantRoomByType(RoomTypes type) {
        List<Room> allEntries = findAllEntries();
        for (Room room : allEntries) 
            if (room.getStatus() == RoomStatuses.VACANT && room.getRoomType() == type) 
                return room;

        return null;
    }

    public boolean checkIfHotelIsFull(Date checkInDate, Date checkOutDate) {
        return false;
    }

    public Room findVacantRoom(RoomTypes rType, Date cID, Date cOD) {
        return null;
    }

    public void updateRoomStatus(Room vacantRoom, RoomStatuses occupied) {
    }

=======
>>>>>>> Stashed changes
}
