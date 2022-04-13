package com.models;
import com.db.guestDB.GuestDB;
import com.enums.IDType;
import com.enums.PaymentType;
import com.utils.MiscUtils;

public class Guest implements Model<Guest> {

    private String name, address, country, gender, identity, nationality;
    private Integer contact, guestID;
    private IDType idType;
    private Boolean isPayingGuest;
    private PaymentType paymentType;
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
        this.guestID = setGuestID();
    }

    @Override
    public String toString() {
        String[] keys = new String[] {
                "Name",
                "Guest ID",
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
                guestID.toString(),
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

    private int setGuestID() {
        int id = MiscUtils.generateID();
        if (new GuestDB().checkDuplicate(id)) {
            return id;
        } else
            return setGuestID();
    }

    public void setGuestID(int id) {
        this.guestID = id;
    }

    public int getGuestID() {
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

    public Boolean getIsPayingGuest() {
        return isPayingGuest;
    }



}
