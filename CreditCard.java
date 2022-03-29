public class CreditCard {
    private String name; 
    private long creditCardNum;
    private String creditCardExpiry;
    private int creditCardCVV;
    private String creditCardType;
    
    public CreditCard(){
      super();
    }
    
    public CreditCard(long creditCardNum, String expiryDate, int cvv, String creditCardType, String name) {
        super();
        this.creditCardNum = creditCardNum;
        this.creditCardExpiry = expiryDate; 
        this.creditCardCVV = cvv;
        this.creditCardType = creditCardType;
        this.name = name;
        
    }


}
