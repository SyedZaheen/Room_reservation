package com.controller;

import com.Views;
import com.models.CreditCard;
import com.utils.MiscUtils;

public class CreditCardControl implements Controller<CreditCard> {
        
        @Override
        public CreditCard manageCreateEntry() {
                String name;
                long creditCardNum;
                String expiryDate;
                String creditCardType;

                System.out.println("If the guest is paying, please collect his/her credit card details");

                name = Views.<String>getEachFieldFromUser(
                                "Please enter the name on the credit card: ",
                                "Error. please enter a string between 5 and 20 characters long",
                                i -> MiscUtils.stringWithinLength(i, 5, 20),
                                "String");

                creditCardNum = Views.<Long>getEachFieldFromUser(
                                "Please enter the 16-digit credit card number (No spaces, do not start with 0): ",
                                "Error. Please enter a 16-digit credit card number. No spaces, do not start with 0",
                                i -> i >= (long) 1e15 && i < (long) 1e16,
                                "Long");

                // todo: create a function that validates this date. Im lazy.
                expiryDate = Views.<String>getEachFieldFromUser(
                                "Please enter the expiry date, in DD/MM format: ",
                                "Error. Please enter a string in DD/MM format",
                                i -> MiscUtils.stringWithinLength(i, 5, 5),
                                "String");

                creditCardType = Views.<String>getEachFieldFromUser(
                                "Please enter the credit card type (Master, Visa, etc): ",
                                "Error. Please enter a string between 3 and 20 characters long",
                                i -> MiscUtils.stringWithinLength(i, 3, 20),
                                "String");

                CreditCard newCC = new CreditCard(name, creditCardNum, expiryDate, creditCardType);

                if (!Views.<CreditCard>userDoubleConfirmDetails(newCC))
                        newCC = manageCreateEntry();

                return newCC;
        }


}
