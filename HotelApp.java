import com.controller.GuestControl;
import com.controller.ReservationControl;
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

            default:
                break;
        }

    }

    public static int showMainMenuAndReturnChoice() {
        return FrontendUtils.getUserChoice(new String[]{
            "Manage Guest",
            "Manage Reservations",
            "Manage Room Service",
            "Manage Payment",
            "Manage Rooms"
        });
    }

    
}