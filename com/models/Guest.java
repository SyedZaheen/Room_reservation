package com.models;

import java.util.Random;

import com.enums.IDType;
import com.enums.PaymentType;

public class Guest implements Model<Guest> {

    private String name, address, country, gender, identity, nationality;
    private Integer contact;
    private IDType idType;
    private Boolean isPayingGuest;
    private PaymentType paymentType;
    private CreditCard creditCard;
    private final int guestID = new Random().nextInt(1000000); // Because youre making this final, note that if we want to update 
                                                                // the guest using GuestDB.updateEntry(), we will need to query the entire
                                                                // DB for that single guest object we want and edit its attributes

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
            PaymentType paymentType,
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
        this.paymentType = isPayingGuest ? paymentType : null;
        this.creditCard = paymentType == PaymentType.CREDITCARD ? creditCard : null;
    }

    @Override
    public String toString() {
        String[] keys = new String[] {
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

        String[] values = new String[] {
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
        for (int i = 0; i < keys.length; i++) {
            finalString = finalString.concat(
                    keys[i] + " : " + values[i] + "\n");
        }

        if (isPayingGuest) {
            if (paymentType == PaymentType.CREDITCARD)
                finalString = finalString.concat("Payment Type: \n" + creditCard.toString());

            else if (paymentType == PaymentType.CASH)
                finalString = finalString.concat("Payment Type: Cash");
        } else
            paymentType = PaymentType.NA;

        return finalString;
    }

    public int getID() {
        return this.guestID;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public String getName() {
        return name;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }
}
