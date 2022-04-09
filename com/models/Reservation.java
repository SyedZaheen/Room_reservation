package com.models;

import com.enums.ReservationStatuses;

import java.sql.Date;
import java.util.ArrayList;

public class Reservation implements Model<Reservation> {

    private Integer reservationID, numberOfAdults, numberOfChildren;
    private ArrayList<Guest> guests;
    private Room reservedRoom;
    private CreditCard creditCardUsed;
    private Date checkInDate, checkOutDate;
    private ReservationStatuses reservationStatus;
    private ArrayList<RoomService> roomServices;

    public Reservation(
            Integer reservationID,
            Integer numberOfAdults,
            Integer numberOfChildren,
            ArrayList<Guest> guests,
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
        this.roomServices = new ArrayList<>();
    }

    @Override
    public String toString() {

        String allGuests = "";
        for (int i = 0; i < (numberOfAdults + numberOfChildren); i++) {
            allGuests = allGuests.concat(
                    "Guest " + (i + 1) + " : \n" + guests.get(i).toString() + "\n");
        }

        String creditCardUsedString = creditCardUsed != null ? creditCardUsed.toString() : "No Credit Card";

        String[] keys = new String[] {
                "Reservation ID",
                "Paying Guest Name",
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
                guests.get(guests.size() - 1).getName(),
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

    public Guest getPayingGuest()
    {
        return guests.get(guests.size() - 1);
    }

    public Date getCheckInDate() {
        return this.checkInDate;
    }

    public void addRoomServices(RoomService r) {
        roomServices.add(r);
    }

    public Integer getNumberOfAdults() {
        return numberOfAdults;
    }
    public Integer getNumberOfChildren() {
        return numberOfChildren;
    }
    public CreditCard getCreditCardUsed() {
        return creditCardUsed;
    }
    public ReservationStatuses getReservationStatus() {
        return reservationStatus;
    }
    public Room getReservedRoom() {
        return reservedRoom;
    }
    public ArrayList<RoomService> getRoomServices() {
        return roomServices;
    }
    public Date getCheckOutDate() {
        return this.checkOutDate;
    }

    public int getReservationID() {
        return 0;
    }

    public ArrayList<Guest> getGuests() {
        return guests;
    }
}
