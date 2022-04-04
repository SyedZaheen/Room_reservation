package com.enums;

public enum BedTypes {
    SINGLE("Single"),
    DOUBLE("Double"),
    MASTER("Master");

    final String inString;

    BedTypes (String inString)
    {
        this.inString = inString;
    }
}
