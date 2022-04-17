package com.models;

import com.enums.RoomStatuses;
import com.enums.RoomTypes;

/**
 * Represents a room model.
 * @author DSF 1 Group 1.
 *
 */
public class Room implements Model<Room> {

    private RoomStatuses status;
    private RoomTypes roomType;
    private Integer roomNumber;
    
    /**
     * Creates a room object with a given room status, room type and room number
     * @param status This room's status.
     * @param roomType This room's type.
     * @param roomNumber This room's room number.
     */

    Room(RoomStatuses status, RoomTypes roomType, Integer roomNumber) {

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
    
    /**
     * Gets this room's room type.
     * @return This room's room type.
     */

    public RoomTypes getRoomType() {
        return roomType;
    }
   
    /**
     * Sets this room's room status.
     * @return void.
     */ 

    public void setStatus(RoomStatuses status) {
        this.status = status;
    }
    
    /**
     * Gets this room's room status.
     * @return This room's room status.
     */

    public RoomStatuses getStatus() {
        return status;
    }
    /**
     * Gets this room's room number.
     * @return This room's room number.
     */

    public Integer getRoomNumber() {
        return roomNumber;
    }
}
