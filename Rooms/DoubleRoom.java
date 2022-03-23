package Rooms;

public class DoubleRoom extends Room {
    public DoubleRoom(int roomNumber)
    {
        super(roomNumber);
        this.roomNumber = roomNumber;
        this.fare = 200;
    }
}
