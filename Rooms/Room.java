package Rooms;
public abstract class Room {

    protected boolean occupied = false;
    protected int roomNumber;
    protected double fare;

    Room(int roomNumber)
    {
        this.roomNumber = roomNumber;
        this.occupied = true;
    }

    public boolean getIsOccupied()
    {
        return occupied;
    }

    public int getRoomNumber()
    {
        return roomNumber;
    }

}

