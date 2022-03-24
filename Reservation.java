import Rooms.Room;
public class Reservation {
    private Guest guest;
    private Room room;
    private long date; //import date type
    private int[] guestArray; //first element = number of adults, second element = number of children

    public Reservation(){

    }

    public String printReceipt(){
        return String.format("Guest details: %s, %nRoom details: %s, %nDate of reservation: %d, %nNumber of adults: %d, %nNumber of children: %d", 
        this.guest, this.room, this.date, this.guestArray[0], this.guestArray[1]);
    }

}
