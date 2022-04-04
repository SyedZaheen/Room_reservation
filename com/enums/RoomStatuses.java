package com.enums;

public enum RoomStatuses {

    OCCUPIED("Occupied"),
    VACANT("Vacant"),
    MAINTENANCE("Maintenance"),
    RESERVED("Reserved");

    final public String inString;

    RoomStatuses (String inString)
    {
        this.inString = inString;
    }
}
