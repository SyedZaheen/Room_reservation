package com.models;

public class CreditCard implements Model<CreditCard> {
  private String name;
  private Long creditCardNum;
  private String expiryDate;
  private String creditCardType;

  // todo: what is this?
  // public CreditCard(){
  // super();
  // }

  // todo: explain what we can or cannot store. CANNOT store cvv!!
  // https://www.chargebee.com/blog/db-credit-card-vault/
  public CreditCard(String name, long creditCardNum, String expiryDate, String creditCardType) {
    this.name = name;
    this.creditCardNum = creditCardNum;
    this.expiryDate = expiryDate;
    this.creditCardType = creditCardType;
  }

  @Override
  public String toString() {
    String[] keys = new String[] {
        "Name",
        "Credit Card Number (16-digit)",
        "Expiry Date",
        "Credit Card Type"
    };
    String[] values = new String[] {
        name,
        creditCardNum.toString(),
        expiryDate,
        creditCardType
    };

    String finalString = "";

    for (int i = 0; i < keys.length; i++) {
      finalString = finalString.concat(
          keys[i] + " : " + values[i] + "\n");
    }
    return finalString;
  }

}
