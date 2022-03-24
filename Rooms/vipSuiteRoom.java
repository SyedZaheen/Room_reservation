package Rooms;

public class vipSuiteRoom extends Room {
    public vipSuiteRoom(int roomNumber)
    {
        super(roomNumber);
        this.roomNumber = roomNumber;
        this.fare = 400;
    }
}
