package com.controller;

import com.models.CreditCard;
import com.utils.FrontendUtils;
import com.utils.MiscUtils;

public abstract class CreditCardControl {

    public static CreditCard manageCreateCreditCard() {
        String name;
        long creditCardNum;
        String expiryDate;
        String creditCardType;

        name = FrontendUtils.<String>getEachFieldFromUser(
                "Please enter the name: ",
                "Error. please enter a string between 5 and 20 characters long",
                i -> MiscUtils.stringWithinLength(i, 5, 20),
                "String");

        creditCardNum = FrontendUtils.<Long>getEachFieldFromUser(
            "Please enter the 16-digit credit card number (Do not start with 0): ",
            "Error. Please enter a 16-digit credit card number. Do not start with 0",
            i -> i >= (long) 1e15 && i < (long) 1e16,
            "Long");
        
        //todo: create a function that validates this date. Im lazy.
        expiryDate = FrontendUtils.<String>getEachFieldFromUser(
                "Please enter the expiry date, in DD/MM format: ",
                "Error. Please enter a string in DD/MM format",
                i -> MiscUtils.stringWithinLength(i, 4, 4),
                "String");
        
        creditCardType = FrontendUtils.<String>getEachFieldFromUser(
                "Please enter the credit card type: ",
                "Error. Please enter a string between 3 and 20 characters long",
                i -> MiscUtils.stringWithinLength(i, 3, 20),
                "String");

        return new CreditCard(name, creditCardNum, expiryDate, creditCardType) ;
    }
    
}
