package com.models.room;

public class SingleRoom extends Room {    

    public SingleRoom(int roomNumber, BedTypes bedType)
    {
        super(roomNumber, bedType);
        this.fare = 100;
    }

    public boolean getIsOccupied() {
        return false;
    }
    

    
}
