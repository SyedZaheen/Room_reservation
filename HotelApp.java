import com.controller.GuestControl;
import com.controller.RoomServiceController;
import com.controller.ReservationControl;
import com.controller.PaymentControl;
import com.utils.FrontendUtils;


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
                new RoomServiceController().manageCreateEntry();

            case 4:
                PaymentControl.process();
                break;

            default:
                break;
        }

    }

    public static int showMainMenuAndReturnChoice() {
        return FrontendUtils.getUserChoice(new String[]{
            "Manage Guest",
            "Manage Reservations",
            "Create Room Service",
            "Manage Payment",
            "Manage Rooms"
        });
    }

    
}