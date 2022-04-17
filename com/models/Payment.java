package com.models;

/**
 * Represents a Payment model
 * @author DSF 1 Group 1
 *
 */

public class Payment implements Model <Payment> {
    
    private final double TAX = 1.17;
    private double discount;
    private double finalBill;
    private Reservation reservation;
    
    /**
     * Creates a payment object with a given reservation and discount.
     * @param reservation Reservation object
     * @param discount The discount a guest is entitled to from 0 to 1.
     */
    
    public Payment(Reservation reservation, double discount) {
        this.reservation = reservation;
        this.discount = discount;
    }
    
    /**
     * Gets this payment's reservation object.
     * @return This payment's reservation object.
     */

    public Reservation getReservation(){
        return reservation;
    }
    
    /**
     * Sets this payment's discount value from 0 to 1.
     * @return void.
     */

    public void setDiscount(double discount) {
        this.discount = discount;
    }

}