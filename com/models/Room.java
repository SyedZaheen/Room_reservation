package com.models;

import com.enums.RoomStatuses;
import com.enums.RoomTypes;

public class Room implements Model<Room> {

    private RoomStatuses status;
    private RoomTypes roomType;
    private Integer roomNumber;

    public Room(RoomStatuses status, RoomTypes roomType, Integer roomNumber) {

        this.status = status;
        this.roomType = roomType;
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        RoomTypes rt = roomType;
        String toReturn = "Room number: " + roomNumber.toString() + "\n" +
                "Room Status: " + status.inString + "\n" +
                "Type: " + rt.inString + "\n" +
                "Rate Per Night: SGD$" + rt.getRatePerNight() + "\n" +
                "BedType: " + rt.getBedType().inString + "\n" +
                "Has Nice View: " + rt.getWithView().toString() + "\n" +
                "Has Wifi Enabled: " + rt.getWifiEnabled().toString() + "\n" +
                "Smoking Is Allowed: " + rt.getSmokingIsAllowed().toString() + "\n";
        return toReturn;
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
