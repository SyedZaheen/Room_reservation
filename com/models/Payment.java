package com.models;

public class Payment implements Model <Payment> {
    
    private final double TAX = 1.17;
    private double discount;
    private double finalBill;

    /* From Reservation I have these crucial attributes that would influence
       the computation of my total bill:
       (1)  Room -> contains enum of RoomType which includes Room Rates
       (2)  Date -> contains the check-in date and check-out date (multipliying
                    factor for room rate)
       (3)  Room Service -> contains the entire ArrayList of RoomService orders */
    private Reservation reservation;
    
    public Payment(Reservation reservation, double discount) {
        this.reservation = reservation;
        this.discount = discount;
    }

    public Reservation getReservation(){
        return reservation;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

}