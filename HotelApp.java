import com.Views;
import com.controller.GuestControl;
import com.controller.RoomServiceControl;
import com.controller.ReservationControl;
import com.controller.PaymentControl;

public class HotelApp {
    public static void main(String[] args) {
        // First thing: Show the main menu
        int choice = showMainMenuAndReturnChoice();

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
                break;
        }

    }

    public static int showMainMenuAndReturnChoice() {
        return Views.getUserChoice(new String[] {
                "Manage Guest",
                "Manage Reservations",
                "Create Room Service",
                "Manage Room Service Orders",
                "Manage Payment",
                "Manage Rooms"
        });
    }

}