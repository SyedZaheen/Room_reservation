package com.models;
import com.db.guestDB.GuestDB;
import com.enums.IDType;
import com.enums.PaymentType;
import com.utils.MiscUtils;
/**
 * A guest model
 * @author DSF1 Group 1
 *
 */

public class Guest implements Model<Guest> {

    private String name, address, country, gender, identity, nationality;
    private Integer contact, guestID;
    private IDType idType;
    private Boolean isPayingGuest;
    private PaymentType paymentType;
    private CreditCard creditCard;

    /**
     * Creates a guest object for a given name, address, country, gender, nationality, contact, ID type, identity, isPayingGuest, paymentType and Credit Card.
     * @param name Name of this guest.
     * @param address Address of this guest.
     * @param country Country of this guest.
     * @param gender Gender of this guest (need not be male or female)
     * @param nationality Nationality of this guest.
     * @param contact Contact number of this guest.
     * @param idType ID type of guest (passport or driving license).
     * @param identity Last four characters of this guest's ID.
     * @param isPayingGuest If this guest is a paying guest or not.
     * @param paymentType Enumeration of Cash or Credit Card.
     * @param creditCard Credit Card of this guest.
     */
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
    
    /**
     * Randomly generates a unique guestID for this guest.
     * @return the 
     */

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
    
    /**
     * Fetches the guestID for this guest.
     * @return this guest's guestID.
     */

    public int getGuestID() {
        return this.guestID;
    }
    
    /**
     * Fetches this guest's credit card object.
     * @return this guest's Credit Card.
     */

    public CreditCard getCreditCard() {
        return creditCard;
    }
    
    /**
     * Fetches this guest's name.
     * @return this guest's name.
     */
    
    public String getName() {
        return name;
    }
    
    /**
     * Fetches this guest's payment type.
     * @return this guest's payment type (Enum).
     */

    public PaymentType getPaymentType() {
        return paymentType;
    }
    
    /**
     * Tells us if this guest is a paying guest or not.
     * @return true if database operation successfully completed, false if database opertation fails of whether this guest is a paying guest.
     */

    public Boolean getIsPayingGuest() {
        return isPayingGuest;
    }



}
