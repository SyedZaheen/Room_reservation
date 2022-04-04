package com.models.room;

import com.enums.BedTypes;

public class vipSuiteRoom extends Room {
    public vipSuiteRoom(int roomNumber, BedTypes bedType)
    {
        super(roomNumber, bedType);
        this.fare = 400;
    }
}
