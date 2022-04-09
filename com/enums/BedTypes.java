package com.enums;

public enum BedTypes {
    SINGLE("Single"),
    DOUBLE("Double"),
    MASTER("Master");

    final public String inString;

    BedTypes (String inString)
    {
        this.inString = inString;
    }
}
