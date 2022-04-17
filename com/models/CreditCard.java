package com.models;
/**
 * A credit card model
 * @author DSF 1 Group 1
 *
 */

public class CreditCard implements Model<CreditCard> {
  private String name;
  private Long creditCardNum;
  private String expiryDate;
  private String creditCardType;

  /**
   * Creates a credit card object for a given name, credit card number, expiry date and credit card type.
   * @param name Name of this credit card's owner.
   * @param creditCardNum This credit card number.
   * @param expiryDate This credit card expiry date.
   * @param creditCardType This credit card type.
   */
  
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
