import java.util.ArrayList;
import java.util.List;
import com.db.DB;
import com.db.SerializeDB;
import com.db.roomDB.RoomDB;
import com.enums.IDType;
import com.enums.PaymentType;
import com.models.Guest;
import com.models.Menu;
import com.models.MenuItem;
import com.models.Reservation;
import com.models.Room;
import com.models.RoomService;
import com.utils.MiscUtils;
import com.views.UserInputViews;

// TODO: DELETE FUNCTION WHEN DONE!
public class Test {
        public static void main(String[] args) {
                List<MenuItem> rms = new ArrayList<>();
                rms.add(new MenuItem("Chicken Rice", "Delicious Rice served with chicken", 21));
                rms.add(new MenuItem("Fish Rice", "Delicious Rice served with Fish", 10.5));
                rms.add(new MenuItem("Beef Rice", "Delicious Rice served with Beef", 30));
                rms.add(new MenuItem("Not rice", "It's not actually rice this time I promise", 5));
                SerializeDB.writeSerializedObject(DB.FILE_PATH + "/menuDB/all_menu_items.ser", rms);
        }

        // public static void main(String[] args) {
        //         List<RoomService> rms = new ArrayList<>();
        //         SerializeDB.writeSerializedObject(DB.FILE_PATH + "/roomserviceDB/listOfOrders", rms);

        // }
}
