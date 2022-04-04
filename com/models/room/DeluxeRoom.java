package com.models.room;

import com.enums.BedTypes;

public class DeluxeRoom extends Room{
    public DeluxeRoom(int roomNumber, BedTypes bedType) 
    {
        super(roomNumber, bedType);
        this.fare = 300;
    }
    
}
