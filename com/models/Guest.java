package com.models;

import com.enums.IDType;

public class Guest implements Model<Guest> {

    private String name, address, country, gender, identity, nationality;
    private Integer contact;
    private IDType idType;
    private Boolean isPayingGuest;
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
        String [] keys = new String[] {
            "Name",
            "Address",
            "Country",
            "Gender",
            "Nationality",
            "Contact Number",
            "ID Type",
            "ID Number",
            "Is Paying"
        };
        String [] values = new String[] {
            name,
            address,
            country,
            gender,
            nationality,
            contact.toString(),
            idType.inString,
            identity,
            isPayingGuest.toString(),
        };
        String finalString = "";

        System.out.println("\nThe following is the guest data: ");
        for (int i = 0; i < keys.length; i++) {
            finalString = finalString.concat(
                keys[i] + " : " + values[i] + "\n"
            );
        }

        if (isPayingGuest) finalString = finalString.concat(creditCard.toString());

        // TODO define method
        return finalString;
    }

}
