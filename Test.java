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
import com.utils.MiscUtils;

// TODO: DELETE FUNCTION WHEN DONE!
public class Test {
    public static void main(String[] args) {
        LocalDate checkInDate, checkOutDate;
        boolean isValidDates;
        int day, month, year;

        do {

            year = Views.<Integer>getEachFieldFromUser(
                    "Please enter the year (For Check-In): ",
                    "Error. Please enter a valid year!",
                    i -> MiscUtils.isValidYear(i),
                    "Integer");

            final Integer monthIn = Views.<Integer>getEachFieldFromUser(
                    "Please enter the month (For Check-In): ",
                    "Error. Please enter a valid month!",
                    i -> MiscUtils.isValidMonth(i),
                    "Integer");

            day = Views.<Integer>getEachFieldFromUser(
                    "Please enter the day (For Check-In): ",
                    "Error. Please enter a valid day!",
                    i -> MiscUtils.isValidDay(i, monthIn),
                    "Integer");

            checkInDate = LocalDate.of(year, monthIn, day);

            year = Views.<Integer>getEachFieldFromUser(
                    "Please enter the year (For Check-Out): ",
                    "Error. Please enter a valid year!",
                    i -> MiscUtils.isValidYear(i),
                    "Integer");

            final Integer monthOut = Views.<Integer>getEachFieldFromUser(
                    "Please enter the month (For Check-Out): ",
                    "Error. Please enter a valid month!",
                    i -> MiscUtils.isValidMonth(i),
                    "Integer");

            day = Views.<Integer>getEachFieldFromUser(
                    "Please enter the day (For Check-Out): ",
                    "Error. Please enter a valid day!",
                    i -> MiscUtils.isValidDay(i, monthOut),
                    "Integer");

            checkOutDate = LocalDate.of(year, monthOut, day);

            System.out.println("These are the dates: ");
            System.out.println(checkInDate);
            System.out.println(checkOutDate);
            System.out.println(checkOutDate.compareTo(checkInDate));
            isValidDates = checkOutDate.compareTo(checkInDate) > 0;
            if (!isValidDates)
                System.out.println("The check in date is ahead of the checkout date! Try again!\n");
        } while (!isValidDates);
    }
}
