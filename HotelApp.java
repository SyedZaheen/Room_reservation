import com.Views;
import com.controller.GuestControl;
import com.controller.RoomServiceControl;
import com.utils.MiscUtils;
import com.controller.ReservationControl;
import com.controller.PaymentControl;

public class HotelApp {
    public static void main(String[] args) {
        // First thing: Show the main menu
        int choice;
        System.out.println("\n\n");
        MiscUtils.printTransition();
        System.out.println("WELCOME TO THE HOTEL RESERVATION & PAYMENT SYSTEM! (HRPS)");
        MiscUtils.printTransition();
        System.out.println("\n\n");

        System.out.println("What would you like to do today? ");
        while (true) {
            choice = showMainMenuAndReturnChoice();

            // For each choice of the menu, execute what we want to do.
            switch (choice) {
                case 1:
                    new GuestControl().process();
                    break;

                case 2:
                    new ReservationControl().process();
                    break;
                case 3:
                    new RoomServiceControl().manageCreateEntry();
                    break;
                case 4:
                    new RoomServiceControl().manageUpdateOrderStatus();
                    break;
                case 5:
                    PaymentControl.process();
                    break;

                default:
                    return;
            }
        }
    }

    public static int showMainMenuAndReturnChoice() {
        return Views.getUserChoice(new String[] {
                "Manage Guests\n - See all guests' details\n - Update guest's details\n - Find guest by name\n",
                "Manage Reservations\n - Create new reservation\n - Update reservation status (e.g. check in)\n - See all reservations\n - Find reservation\n - Delete Reservation\n",
                // "Manage Room Service Orders\n - Create new room service order\n - Update/Cancel current order status\n - Manage room service menu items\n",
                // "Manage Payment\n - Make payment and check out from room \n - See payment slip for reservation\n",
                // "Manage Rooms\n - See room availabilities\n",
                "Quit application\n",
        });
    }

}