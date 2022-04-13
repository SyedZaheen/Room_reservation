package testing;
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

                List<Reservation> listOfRoomServices = new ArrayList<>();
                SerializeDB.writeSerializedObject(DB.FILE_PATH + "reservationDB/all_reservations_data", listOfRoomServices);
        }

        // public static void main(String[] args) {
        //         List<RoomService> rms = new ArrayList<>();
        //         SerializeDB.writeSerializedObject(DB.FILE_PATH + "/roomserviceDB/listOfOrders", rms);

        // }
}
