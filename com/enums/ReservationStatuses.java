package com.enums;

public enum ReservationStatuses {
    CONFIRMED("Confirmed"),
    IN_WAITLIST("In Waitlist"),
    CHECKED_IN("Checked-in"),
    EXPIRED("Expired");

    final public String inString;

    ReservationStatuses(String inString) {
        this.inString = inString;
    }
}