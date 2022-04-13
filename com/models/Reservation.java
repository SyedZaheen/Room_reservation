package com.models;

import com.enums.ReservationStatuses;
import com.enums.PaymentType;

import java.time.*;
import java.util.ArrayList;

public class Reservation implements Model<Reservation> {

    private Integer reservationID, numberOfAdults, numberOfChildren;
    private ArrayList<Guest> guests;
    private Room reservedRoom;
    private PaymentType paymentType;
    private CreditCard creditCardUsed;
    private LocalDate checkInDate, checkOutDate;
    private ReservationStatuses reservationStatus;
    private ArrayList<RoomService> roomServices;

    public Reservation(
            Integer reservationID,
            Integer numberOfAdults,
            Integer numberOfChildren,
            ArrayList<Guest> guests,
            Room reservedRoom,
            PaymentType paymentType,
            CreditCard creditCardUsed,
            LocalDate checkInDate,
            LocalDate checkOutDate,
            ReservationStatuses reservationStatus)

    {
        this.reservationID = reservationID;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
        this.guests = guests;
        this.reservedRoom = reservedRoom;
        this.paymentType = paymentType;
        this.creditCardUsed = creditCardUsed;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.reservationStatus = reservationStatus;
        this.roomServices = new ArrayList<>();
    }

    @Override
    public String toString() {

        String allGuests = "";
        for (int i = 0; i < (numberOfAdults + numberOfChildren + 1); i++) {
            allGuests = allGuests.concat(
                    "\n\nGuest " + (i + 1) + " : " + guests.get(i).toString() + "\n");
        }

        String creditCardUsedString = creditCardUsed != null ? "Credit Card: \n" + creditCardUsed.toString()
                : "No Credit Card";

        String[] keys = new String[] {
                "Reservation ID",
                "Paying Guest Name",
                "Number of Adults",
                "Number of Children",
                "Reserved Room Number",
                "Guests",
                "Payment Type",
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
                reservedRoom.getRoomNumber().toString(),
                allGuests,
                paymentType.inString,
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

    public void setRoomServices(ArrayList<RoomService> roomServices) {
        this.roomServices = roomServices;
    }

    public Guest getPayingGuest() {
        return guests.get(guests.size() - 1);
    }

    public LocalDate getCheckInDate() {
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

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public int getReservationID() {
        return reservationID;
    }

    public ArrayList<Guest> getGuests() {
        return guests;
    }

    public void setReservationStatus(ReservationStatuses reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
}
