package com.models.room;
import com.enums.BedTypes;
import com.enums.RoomStatuses;

public abstract class Room {

    protected int roomNumber;
    protected double fare;
    protected boolean wifiEnabled;
    protected boolean facingView;
    protected boolean smokingEnabled;
    protected RoomStatuses status;
    protected BedTypes bedType;
    

    Room(int roomNumber, BedTypes bedType)
    {
        this.roomNumber = roomNumber;
        this.bedType = bedType;    
    }

    public int getRoomNumber()
    {
        return roomNumber;
    }

    public void setStatus(RoomStatuses status) {
        this.status = status;
    }
    public RoomStatuses getStatus(){
        return this.status;
    }

}

