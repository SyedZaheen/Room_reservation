package Rooms;
public abstract class Room {

    protected int roomNumber;
    protected double fare;
    protected boolean wifiEnabled;
    protected boolean facingView;
    protected boolean smokingEnabled;
    protected Statuses status;
    protected BedTypes bedType;
    public enum Statuses {
        OCCUPIED,
        VACANT,
        MAINTENANCE,
        RESERVED,
    }
    public enum BedTypes {
        SINGLE, DOUBLE, MASTER,
    }

    Room(int roomNumber, BedTypes bedType)
    {
        this.roomNumber = roomNumber;
        this.bedType = bedType;    
    }

    public int getRoomNumber()
    {
        return roomNumber;
    }

    public void setStatus(Statuses status) {
        this.status = status;
    }
    public Statuses getStatus(){
        return this.status;
    }

}

