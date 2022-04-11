package com.models;

import java.util.ArrayList;
import java.util.List;

import com.db.DB;
import com.db.SerializeDB;
import com.db.roomDB.RoomDB;
import com.enums.RoomStatuses;
import com.enums.RoomTypes;

public class Hotel implements Model<Hotel> {

    public static void main(String[] args) {
        // List<Room> rooms = new ArrayList<>();
        // for (int i = 0; i < 48; i++) {

        //     int order = (i / 12) + 1;
        //     switch (order) {
        //         case 1:
        //             rooms.add(
        //                     new Room(RoomStatuses.VACANT, RoomTypes.SINGLE, order * 100 + (i % 12) + 1));
        //             break;
        //         case 2:
        //             rooms.add(
        //                     new Room(RoomStatuses.VACANT, RoomTypes.DOUBLE, order * 100 + (i % 12) + 1));
        //             break;
        //         case 3:
        //             rooms.add(
        //                     new Room(RoomStatuses.VACANT, RoomTypes.DELUXE, order * 100 + (i % 12) + 1));
        //             break;
        //         case 4:
        //             rooms.add(
        //                     new Room(RoomStatuses.VACANT, RoomTypes.VIPSUITE, order * 100 + (i % 12) + 1));
        //             break;
        //         default:
        //             break;
        //     }
        // }
        // SerializeDB.writeSerializedObject(DB.FILE_PATH + "roomDB/all_rooms_data.ser", rooms);

        for (Room room : new RoomDB().findAllEntries()) {
            System.out.println(room.getRoomType().inString + " " + room.getRoomType().getRatePerNight());
            System.out.println(room.getRoomNumber());
        }
    }

}
