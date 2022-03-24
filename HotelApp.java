import java.util.Scanner;

import Rooms.SingleRoom;


public class HotelApp {

    public static void getOccupiedRooms(SingleRoom[] rms) {
        for (SingleRoom rm : rms) {
            try {

                if (rm.getIsOccupied())
                    System.out.println(rm.getRoomNumber());
            } catch (NullPointerException n){
                System.out.println("There is no room in this index location!");
            }
        }
    }


    public static void main(String[] args) {
        
        
        int choice;
		Hotel hotel = new Hotel();
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("(1) Show the list of available rooms");
			System.out.println("(2) Show the list of occupied rooms");
			System.out.println("(3) Make a reservation");
			System.out.println("(4) Search guest details");
			System.out.println("(5) Update room details");
			System.out.println("(6) Make room service order");
			System.out.println("(7) Update room service menu");
            System.out.println("(8) Check out room");
            System.out.println("(9) Exit");
			System.out.println("Enter the number of your choice: ");
			
			choice = sc.nextInt();
			
			switch (choice) {
				case 1: 

                    System.out.println("(1) Single rooms");
                    System.out.println("(2) Double rooms");
                    System.out.println("(3) Deluxe rooms");
                    System.out.println("(4) VIP Suite rooms");

                    int choice2 = sc.nextInt();
                    switch (choice2) {
                        case 1: HotelApp.showAvailableSingleRooms();
                            break;
                        case 2: HotelApp.showAvailableDoubleRooms();
                            break;
                        case 3: HotelApp.showAvailableDeluxeRooms();
                            break;
                        case 4: HotelApp.showAvailablevipSuiteRooms();
                            break;
                        default: break;
                    }
					break;

				case 2: 

                    System.out.println("(1) Single rooms");
                    System.out.println("(2) Double rooms");
                    System.out.println("(3) Deluxe rooms");
                    System.out.println("(4) VIP Suite rooms");

                    int choice3 = sc.nextInt();
                    switch (choice3) {
                        case 1: hotel.showOccupiedSingleRooms();
                            break;
                        case 2: hotel.showOccupiedDoubleRooms();
                            break;
                        case 3: hotel.showOccupiedDeluxeRooms();
                            break;
                        case 4: hotel.showOccupiedvipSuiteRooms();
                            break;
                        default: break;
                    }
					break;

				case 3: hotel.makeReservation();
					break;
				case 4: hotel.searchGuest();
					break;
				case 5: hotel.updateRoomDetails();
                    break;
				case 6: hotel.roomServiceOrder();
                    break;
				case 7: hotel.updateRoomServiceMenu();
                    break;
                case 8: hotel.checkOut();
                    break;
                default: break;
                }
		} while (choice < 8);

    }

}


