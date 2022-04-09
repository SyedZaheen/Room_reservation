package com.enums;

public enum OrderStatus {

    COMPLETED("Completed"),
    CANCELLED("Cancelled"),
    PREPARING("Preparing");

    final public String inString;

    OrderStatus (String inString)
    {
        this.inString = inString;
    }
    
}
