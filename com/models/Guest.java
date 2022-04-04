package com.models;

import java.util.HashMap;

import com.enums.IDType;

public class Guest implements Model<Guest> {

    private String name, address, country, gender, identity, nationality;
    private int contact;
    private IDType idType;
    private boolean isPayingGuest;
    private CreditCard creditCard;

    public Guest(
            String name,
            String address,
            String country,
            String gender,
            String nationality,
            int contact,
            IDType idType,
            String identity,
            boolean isPayingGuest,
            CreditCard creditCard)

    {
        this.name = name;
        this.address = address;
        this.country = country;
        this.gender = gender;
        this.nationality = nationality;
        this.identity = identity;
        this.contact = contact;
        this.idType = idType;
        this.isPayingGuest = isPayingGuest;
        this.creditCard = isPayingGuest ? creditCard : null;
    }

    @Override
    public String toString()
    {
        // TODO define method
        return null;
    }

}
