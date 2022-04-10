import java.util.ArrayList;
import java.util.List;

import com.controller.GuestControl;
import com.db.DB;
import com.db.SerializeDB;
import com.enums.IDType;
import com.enums.PaymentType;
import com.models.Guest;

// TODO: DELETE FUNCTION WHEN DONE!
public class Test {
    public static void main(String[] args) {
        new GuestControl().manageCreateEntry(true);
        List<Guest> guests = new ArrayList<Guest>();

        guests.add(new Guest("syed", "Singapore", "Singapore", "Male", "Singapore", 87654321, IDType.PASSPORT, "290D",
                false, PaymentType.NA, null));
        SerializeDB.writeSerializedObject(DB.FILE_PATH + "guestDB/listOfGuests.ser", guests);
    }
}
