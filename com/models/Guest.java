package com.models;
import java.util.HashMap;

public class Guest implements Entity<Guest> {
    
    private String name;
    private long creditCardDetails;
    private String address;
    private String country;
    private String gender;
    private String identity;
    private String nationality;
    private long contact;

    public Guest(String name, long creditCardDetails, String address, String country, String gender, String identity, String nationality, long contact) {
        this.name = name;
        this.creditCardDetails = creditCardDetails;
        this.address = address;
        this.country = country;
        this.gender = gender;
        this.identity = identity;
        this.nationality = nationality;
        this.contact = contact;
    }

    @Override
    public HashMap<String, String> toHashMap() {
        // TODO Auto-generated method stub
        return null;
    }

    public static Guest fromHash(HashMap<String, String> hMap) {
        // TODO Auto-generated method stub
        return null;
    }


}
