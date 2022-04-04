package com.enums;

public enum IDType {
    DRIVING_LICENSE("Driving License"), 
    PASSPORT("Passport");

    final String inString;

    IDType (String inString)
    {
        this.inString = inString;
    }
}
