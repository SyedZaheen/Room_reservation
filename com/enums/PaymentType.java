package com.enums;

public enum PaymentType {
    CREDITCARD("Creditcard"),
    CASH("Cash"),
    NA("NA");

    final public String inString;

    PaymentType(String inString) {
        this.inString = inString;
    }
}
