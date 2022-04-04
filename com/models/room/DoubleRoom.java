package com.models.room;

import com.enums.BedTypes;

public class DoubleRoom extends Room {
    public DoubleRoom(int roomNumber, BedTypes bedType)
    {
        super(roomNumber, bedType);
        this.fare = 200;
    }
}
