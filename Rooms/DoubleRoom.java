package Rooms;

public class DoubleRoom extends Room {
    public DoubleRoom(int roomNumber, BedTypes bedType)
    {
        super(roomNumber, bedType);
        this.fare = 200;
    }
}
