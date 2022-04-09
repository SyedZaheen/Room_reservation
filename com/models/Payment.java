package com.models;


public class Payment implements Model<Payment> {
    private Reservation reservation;
    private final double TAX = 1.17;
    private long bill;
    
    
    @Override
    public String toString()
    {
        // TODO define method
        return null;
    }

    public Reservation getReservation(){
        return reservation;
    }

    public double getTaxedTotal() {
    
    }


