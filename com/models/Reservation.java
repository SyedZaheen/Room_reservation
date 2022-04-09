package com.models;

import com.enums.ReservationStatuses;

import java.sql.Date;

public class Reservation implements Model<Reservation> {

    private Integer reservationID, numberOfAdults, numberOfChildren;
    private Guest guests[];
    private Room reservedRoom;
    private CreditCard creditCardUsed;
    private Date checkInDate, checkOutDate;
    private ReservationStatuses reservationStatus;

    public Reservation(
            Integer reservationID,
            Integer numberOfAdults,
            Integer numberOfChildren,
            Guest guests[],
            Room reservedRoom,
            CreditCard creditCardUsed,
            Date checkInDate,
            Date checkOutDate,
            ReservationStatuses reservationStatus)

    {
        this.reservationID = reservationID;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
        this.guests = guests;
        this.reservedRoom = reservedRoom;
        this.creditCardUsed = creditCardUsed;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.reservationStatus = reservationStatus;
    }

    @Override
    public String toString() {

        String allGuests = "";
        for (int i = 0; i < (numberOfAdults + numberOfChildren); i++) {
            allGuests = allGuests.concat(
                    "Guest " + (i + 1) + " : \n" + guests[i].toString() + "\n");
        }

        String creditCardUsedString = creditCardUsed != null ? creditCardUsed.toString() : "No Credit Card";

        String[] keys = new String[] {
                "Reservation ID",
                "Number of Adults",
                "Number of Children",
                "Reserved Room",
                "Guests",
                "Credit Card Used",
                "Check-in Date",
                "Check-out Date",
                "Reservation Status"
        };

        String[] values = new String[] {
                reservationID.toString(),
                numberOfAdults.toString(),
                numberOfChildren.toString(),
                reservedRoom.toString(),
                allGuests,
                creditCardUsedString,
                checkInDate.toString(),
                checkOutDate.toString(),
                reservationStatus.inString,
        };

        String finalString = "";
        for (int j = 0; j < keys.length; j++) {
            finalString = finalString.concat(
                    keys[j] + " : " + values[j] + "\n");
        }

        return finalString;
    }

    public Date getCheckInDate() {
        return this.checkInDate;
    }

    public Date getCheckOutDate() {
        return this.checkOutDate;
    }
}
