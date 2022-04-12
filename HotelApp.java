
import com.controller.RoomServiceControl;
import com.utils.MiscUtils;
import com.views.GuestViews;
import com.views.PaymentViews;
import com.views.ReservationViews;
import com.views.RoomServiceViews;
import com.views.RoomViews;
import com.views.UserInputViews;

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
                    new GuestViews().process();
                    break;

                case 2:
                    new ReservationViews().process();
                    break;

                case 3:
                    new RoomServiceViews().process();
                    break;

                case 4:
                    new PaymentViews().process();
                    break;

                case 5:
                    new RoomViews().process();
                    break;

                default:
                    return;
            }
        }
    }

    public static int showMainMenuAndReturnChoice() {
        return UserInputViews.getUserChoice(new String[] {
                "Manage Guests\n - See all guests' details\n - Update guest's details\n - Find guest by name\n",
                "Manage Reservations\n - Create new reservation (walk-in or advanced)\n - Update reservation status (e.g. checked-in)\n - See all reservations\n - Find reservation\n - Delete Reservation\n",
                "Manage Room Service Orders\n - Create new room service order\n - View all orders\n - View/update/cancel current order \n - Manage room service menu items\n",
                "Manage Payment\n - Make payment and check out from room \n - See payment slip for reservation\n",
                "Manage Rooms\n - View all room options for this hotel \n - Check room availability by room number\n - Update room status\n - View full room status report\n",
                "Quit application\n",
        });
    }

}