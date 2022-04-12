import java.util.ArrayList;
import java.util.List;
import com.db.DB;
import com.db.SerializeDB;
import com.db.roomDB.RoomDB;
import com.enums.IDType;
import com.enums.PaymentType;
import com.models.Guest;
import com.models.Reservation;
import com.models.Room;
import com.utils.MiscUtils;
import com.views.UserInputViews;

// TODO: DELETE FUNCTION WHEN DONE!
public class Test {
        public static void main(String[] args) {
                List<Room> rms = new RoomDB().findAllEntries();
                for (Room room : rms) {
                     System.out.println(room);   
                }
        }
}
