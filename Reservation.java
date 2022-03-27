import Rooms.Room;
import java.util.Scanner;

public class Reservation {
    private Guest guest;
    private Room room;
    private long[] date; //import date type
    private int[] guestArray; //first element = number of adults, second element = number of children
    private ReservationStatus reservationStatus;
    private RoomService roomService;
    public enum ReservationStatus {
        CONFIRMED, WAIT_LIST, CHECKED_IN, EXPIRED
    }

    public Reservation(Room room, Guest guest, long checkIn, long checkOut){
        this.date[0] = checkIn;
        this.date[1] = checkOut;
        this.guest = guest;
        this.room = room;
        room.setStatus(Room.Statuses.RESERVED);
    }
    

    public String printReceipt(){
        return String.format("Guest details: %s, %nRoom details: %s, %nDate of reservation: %d - %d, %nNumber of adults: %d, %nNumber of children: %d", 
        this.guest, this.room, this.date[0], this.date[1], this.guestArray[0], this.guestArray[1]);
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
        if(this.reservationStatus == Reservation.ReservationStatus.CHECKED_IN) 
            room.setStatus(Room.Statuses.OCCUPIED);
        else if(this.reservationStatus == Reservation.ReservationStatus.EXPIRED) 
            room.setStatus(Room.Statuses.VACANT);
    }
    
    public double totalRoomFare(){
        
    }

    
}
