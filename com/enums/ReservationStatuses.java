package com.enums;

public enum ReservationStatuses {
    RESERVED("Reserved"),
    PENDING("Pending"),
    REJECTED("Rejected");

    final public String inString;

    ReservationStatuses(String inString) {
        this.inString = inString;
    }
}