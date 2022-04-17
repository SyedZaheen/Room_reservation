package com.models;

import com.enums.ReservationStatuses;
import com.enums.PaymentType;

import java.time.*;
import java.util.ArrayList;

/**
 * Represents a reservation model.
 * @author DSF 1 Group 1
 *
 */

public class Reservation implements Model<Reservation> {

    private Integer reservationID, numberOfAdults, numberOfChildren;
    private ArrayList<Guest> guests;
    private Room reservedRoom;
    private PaymentType paymentType;
    private CreditCard creditCardUsed;
    private LocalDate checkInDate, checkOutDate;
    private ReservationStatuses reservationStatus;
    private ArrayList<RoomService> roomServices;
    
    /**
     * Creates a reservation object with a given reservation ID, Number of Adults, Number of Children, Array List of Guests,
     * reserved room object, payment type (enum), credit card object used, check in date, check our date and reservation status (enum).
     * @param reservationID Reservation ID.
     * @param numberOfAdults Number of adults staying in the room. 
     * @param numberOfChildren Number of children staying in the room.
     * @param guests ArrayList of guests.
     * @param reservedRoom Reserved room object.
     * @param paymentType Payment type
     * @param creditCardUsed Credit card object used.
     * @param checkInDate Check in date.
     * @param checkOutDate Check out date.
     * @param reservationStatus Reservation status.
     */

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

    /**
     * Sets this reservation's room service orders.
     * @param roomServices ArrayList of room service objects.
     */
    public void setRoomServices(ArrayList<RoomService> roomServices) {
        this.roomServices = roomServices;
    }
    
    /**
     * Get the guest that is paying for the this reservation.
     * @return The guest that is paying for this reservation.
     */

    public Guest getPayingGuest() {
        return guests.get(guests.size() - 1);
    }
    /**
     * Gets the check in date of this reservation.
     * @return The check in date of this reservation.
     */

    public LocalDate getCheckInDate() {
        return this.checkInDate;
    }

    /**
     * Adds a room service object to to this's reservation's array of room service objects.
     * @return void.
     */

    public void addRoomServices(RoomService r) {
        roomServices.add(r);
    }
    /**
     * Gets the number of adults in this reservation.
     * @return The number of adults in this reservation.
     */

    public Integer getNumberOfAdults() {
        return numberOfAdults;
    }
    /**
     * Gets the number of children in this reservation.
     * @return The number of children in this reservation.
     */

    public Integer getNumberOfChildren() {
        return numberOfChildren;
    }
    /**
     * Gets the credit card object used in this reservation.
     * @return The credit card object used in this reservation.
     */

    public CreditCard getCreditCardUsed() {
        return creditCardUsed;
    }
    
    /**
     * Gets the reservation status of this reservation.
     * @return The reservation status of this reservation.
     */

    public ReservationStatuses getReservationStatus() {
        return reservationStatus;
    }
    
    /**
     * Gets the room object reserved to this reservation.
     * @return The room object reserved to this reservation.
     */

    public Room getReservedRoom() {
        return reservedRoom;
    }

    /**
     * Gets the array of room service objects in this reservation.
     * @return The array of room service objects in this reservation.
     */
    public ArrayList<RoomService> getRoomServices() {
        return roomServices;
    }
    
    /**
     * Gets the check out date of this reservation.
     * @return The check out date of this reservation.
     */

    
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }
    
    /**
     * Gets the reservation ID of this reservation.
     * @return The reservation ID of this reservation.
     */

    public int getReservationID() {
        return reservationID;
    }
    
    /**
     * Gets the ArrayList of Guest objects in this reservation.
     * @return The ArrayList of Guest objects in this reservation.
     */

    public ArrayList<Guest> getGuests() {
        return guests;
    }
    
    /**
     * Sets the ArrayList of Guest objects in this reservation.
     * @return void
     */
    
	    
	public void setGuests(ArrayList<Guest> guests) {
	    this.guests = guests;
	}
	/**
     * Sets the reservation status in this reservation.
     * @return void
     */
    
	
    public void setReservationStatus(ReservationStatuses reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
}
