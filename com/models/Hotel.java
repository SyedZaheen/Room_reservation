package com.models;

import java.util.ArrayList;
import java.util.List;

import com.db.SerializeDB;
import com.db.roomDB.RoomDB;
import com.enums.RoomStatuses;
import com.enums.RoomTypes;

public class Hotel implements Model<Hotel> {
    
    public static void main(String[] args) {
        List<Room> rooms = new RoomDB().findAllEntries();
        for (Room room : rooms) {
            System.out.println(room.getRoomType().inString+ " "+ room.getRoomType().getRatePerNight());
        }
    }


}
