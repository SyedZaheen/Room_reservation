package com.models;

import com.enums.RoomStatuses;
import com.enums.RoomTypes;

public class Room implements Model<Room> {

    private RoomStatuses status;
    private RoomTypes roomType;
    private Integer roomNumber;

    Room(RoomStatuses status, RoomTypes roomType, Integer roomNumber) {

        this.status = status;
        this.roomType = roomType;
        this.roomNumber = roomNumber;
    }

    public RoomTypes getRoomType() {
        return roomType;
    }

    public void setStatus(RoomStatuses status) {
        this.status = status;
    }

    public RoomStatuses getStatus() {
        return status;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }
}
