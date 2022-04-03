package com.models;
import java.util.HashMap;

public class Guest implements Entity<Guest> {
    
    private String name, address;
    private Integer creditCardDetails;
    private String country;
    private String gender;
    private String identity;
    private String nationality;
    private Integer contact;

    public Guest(String name, Integer creditCardDetails, String address, String country, String gender, String identity, String nationality, Integer contact) {
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
