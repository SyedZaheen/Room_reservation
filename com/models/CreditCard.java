package com.models;
import java.util.HashMap;

public class CreditCard implements Model<CreditCard> {
  private String name;
  private long creditCardNum;
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
    public String toString()
    {
        // TODO define method
        return null;
    }

}
