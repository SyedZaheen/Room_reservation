package com.models.room;

public class DeluxeRoom extends Room{
    public DeluxeRoom(int roomNumber, BedTypes bedType) 
    {
        super(roomNumber, bedType);
        this.fare = 300;
    }
    
}
