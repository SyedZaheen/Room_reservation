import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.Views;
import com.controller.GuestControl;
import com.db.DB;
import com.db.SerializeDB;
import com.enums.IDType;
import com.enums.PaymentType;
import com.models.Guest;
import com.models.Reservation;
import com.utils.MiscUtils;

// TODO: DELETE FUNCTION WHEN DONE!
public class Test {
        public static void main(String[] args) {
                List<Guest> g = new ArrayList<>();
                List<Reservation> r = new ArrayList<>();
                SerializeDB.writeSerializedObject(DB.FILE_PATH + "guestDB/listOfGuests.ser", g);

                SerializeDB.writeSerializedObject(DB.FILE_PATH + "reservationDB/all_reservations_data.ser", r);
        }
}
