import Rooms.Single;


public class HotelApp {

    public static void getOccupiedRooms(Single[] rms) {
        for (Single rm : rms) {
            try {

                if (rm.getIsOccupied())
                    System.out.println(rm.getRoomNumber());
            } catch (NullPointerException n){
                System.out.println("There is no room in this index location!");
            }
        }
    }

    public static void main(String[] args) {
        Single rooms[] = new Single[48];
        Single room1 = new Single(10);
        rooms[0] = room1;
        rooms[36] = room1;
        getOccupiedRooms(rooms);

    }

}