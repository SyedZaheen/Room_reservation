
import java.util.List;

import com.controller.GuestControl;
import com.db.roomDB.RoomDB;
import com.models.Room;
import com.utils.FrontendUtils;

public class HotelApp {
    public static void main(String[] args) {

        // First thing: Show the main menu
        int choice = FrontendUtils.getUserChoice(new String[]{
            "Manage Guest",
            "Manage Reservations",
            "Manage Room Service",
            "Manage Payment",
            "Manage Rooms"
        });

        // For each choice of the menu, execute what we want to do.
        switch (choice) {
            case 1:
                new GuestControl().process();
                break;
            default:
                break;
        }

    }

    
}
