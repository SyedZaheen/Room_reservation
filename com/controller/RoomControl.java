package com.controller;

import java.time.*;
import java.util.List;

import com.db.roomDB.RoomDB;
import com.enums.RoomStatuses;
import com.enums.RoomTypes;
import com.models.Room;
import com.utils.MiscUtils;
import com.views.UserInputViews;

public class RoomControl implements CreatorController<Room> {

    @Override
    public Room manageCreateEntry() {
        RoomTypes rType = null;
        RoomDB rmdb = new RoomDB();

        // Display all room options to user
        displayAllOptions();

        // Get the room type from the user
        System.out.println("Please enter your room type choice: ");

        do {
            int roomchoice = UserInputViews.getUserChoice(new String[] {
                    "Single Room",
                    "Double Room",
                    "Deluxe Room",
                    "VIP Suite"
            });

            switch (roomchoice) {
                case 1:
                    rType = RoomTypes.SINGLE;
                    break;
                case 2:
                    rType = RoomTypes.DOUBLE;
                    break;
                case 3:
                    rType = RoomTypes.DELUXE;
                    break;
                case 4:
                    rType = RoomTypes.VIPSUITE;
                    break;
                default:
                    break;
            }
            if (rmdb.findVacantRoomByType(rType) == null)
                System.out.println("That room type is all occupied! Please choose another room!");
        } while (rmdb.findVacantRoomByType(rType) != null);
        // Update the db that the room is occupied
        Room vacantRoom = rmdb.findVacantRoomByType(rType);

        rmdb.updateRoomStatus(vacantRoom, RoomStatuses.OCCUPIED);
        // Return the room that has just been occupied

        return vacantRoom;
    }

    public Room manageCreateEntry(LocalDate cID, LocalDate cOD) {
        RoomTypes rType = null;
        RoomDB rmdb = new RoomDB();

        // Display all room options to user
        displayAllOptions();

        // Get the room type from the user
        System.out.println("Please enter your room type choice: ");

        do {
            int roomchoice = UserInputViews.getUserChoice(new String[] {
                    "Single Room",
                    "Double Room",
                    "Deluxe Room",
                    "VIP Suite",
                    "Return to reservation menu"
            });

            switch (roomchoice) {
                case 1:
                    rType = RoomTypes.SINGLE;
                    break;
                case 2:
                    rType = RoomTypes.DOUBLE;
                    break;
                case 3:
                    rType = RoomTypes.DELUXE;
                    break;
                case 4:
                    rType = RoomTypes.VIPSUITE;
                    break;
                case 5:
                    return null;
                default:
                    return null;
            }
            if (rmdb.findVacantRoom(rType, cID, cOD) == null)
                System.out.println("That room type is occupied during that time! Please choose another room!");
        } while (rmdb.findVacantRoom(rType, cID, cOD) == null);
        // Update the db that the room is occupied
        Room vacantRoom = rmdb.findVacantRoom(rType, cID, cOD);

        rmdb.updateRoomStatus(vacantRoom, RoomStatuses.OCCUPIED);
        // Return the room that has just been occupied

        return vacantRoom;
    }

    private void printRoomOptionByType(RoomTypes rt) {
        System.out.println("Type: " + rt.inString);
        System.out.println("Rate Per Night: SGD$" + rt.getRatePerNight());
        System.out.println("BedType: " + rt.getBedType().inString);
        System.out.println("Has Nice View: " + rt.getWithView().toString());
        System.out.println("Has Wifi Enabled: " + rt.getWifiEnabled().toString());
        System.out.println("Smoking Is Allowed: " + rt.getSmokingIsAllowed().toString());

        System.out.println("");
    }

    public void displayAllOptions() {
        System.out.println("The following are the room options for this hotel: ");
        System.out.println("");
        printRoomOptionByType(RoomTypes.SINGLE);
        printRoomOptionByType(RoomTypes.DOUBLE);
        printRoomOptionByType(RoomTypes.DELUXE);
        printRoomOptionByType(RoomTypes.VIPSUITE);
    }

    public Room getRoom(int roomnumber) {
        RoomDB db = new RoomDB();
        Room rm = db.findSingleEntry(roomnumber);
        if (rm == null)
            return null;
        return rm;
    }

    public Room manageUpdateEntry() {
        int roomnumber = UserInputViews.<Integer>getEachFieldFromUser("Please enter the room number to update",
                "Not a valid room number", i -> MiscUtils.roomNumberExists(i), "Integer");
        RoomDB db = new RoomDB();
        Room rm = db.findSingleEntry(roomnumber);
        if (rm == null)
            return null;

        System.out.println("\nThe following is the details of the room that you wish to update: ");
        System.out.println(rm);
        System.out.println("\nPlease choose the status to update the room status to: ");
        int statusChoice = UserInputViews.getUserChoice(new String[] {
                "Occupied",
                "Vacant",
                "Maintenance",
                "Reserved",
                "Maintain current status"
        });

        switch (statusChoice) {
            case 1:
                return db.updateRoomStatus(rm, RoomStatuses.OCCUPIED) ? rm : null;

            case 2:
                return db.updateRoomStatus(rm, RoomStatuses.VACANT) ? rm : null;

            case 3:
                return db.updateRoomStatus(rm, RoomStatuses.MAINTENANCE) ? rm : null;

            case 4:
                return db.updateRoomStatus(rm, RoomStatuses.RESERVED) ? rm : null;

            default:
                break;
        }

        return null;
    }

    public List<Room> getAllRooms() {
        return new RoomDB().findAllEntries();
    }

}
