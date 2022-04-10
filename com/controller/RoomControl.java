package com.controller;

import java.sql.Date;

import com.Views;
import com.db.roomDB.RoomDB;
import com.enums.RoomStatuses;
import com.enums.RoomTypes;
import com.models.Room;

public class RoomControl implements CreatorController<Room> {

    @Override
    public Room manageCreateEntry() {
        RoomTypes rType = null;
        RoomDB rmdb = new RoomDB();

        // Display all room options to user
        System.out.println("The following are the room options avaliable currently: ");
        System.out.println("");
        printRoomOptionByType(RoomTypes.SINGLE);
        printRoomOptionByType(RoomTypes.DOUBLE);
        printRoomOptionByType(RoomTypes.DELUXE);
        printRoomOptionByType(RoomTypes.VIPSUITE);

        // Get the room type from the user
        System.out.println("Please enter your room type choice: ");

        do {
            int roomchoice = Views.getUserChoice(new String[] {
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

    public Room manageCreateEntry(Date cID, Date cOD) {
        RoomTypes rType = null;
        RoomDB rmdb = new RoomDB();

        // Display all room options to user
        System.out.println("The following are the room options avaliable currently: ");
        System.out.println("");
        printRoomOptionByType(RoomTypes.SINGLE);
        printRoomOptionByType(RoomTypes.DOUBLE);
        printRoomOptionByType(RoomTypes.DELUXE);
        printRoomOptionByType(RoomTypes.VIPSUITE);

        // Get the room type from the user
        System.out.println("Please enter your room type choice: ");

        do {
            int roomchoice = Views.getUserChoice(new String[] {
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
            if (rmdb.findVacantRoom(rType, cID, cOD) == null)
                System.out.println("That room type is all occupied! Please choose another room!");
        } while (rmdb.findVacantRoom(rType, cID, cOD) != null);
        // Update the db that the room is occupied
        Room vacantRoom = rmdb.findVacantRoom(rType, cID, cOD);

        rmdb.updateRoomStatus(vacantRoom, RoomStatuses.OCCUPIED);
        // Return the room that has just been occupied

        return vacantRoom;
    }

    private boolean all_Rooms_Are_Occupied(Date dt) {
        return false;
    }

    private void printRoomOptionByType(RoomTypes rt) {
        System.out.println("Type: " + rt.inString);
        System.out.println("Rate Per Night: " + rt.getRatePerNight());
        System.out.println("BedType: " + rt.getBedType().inString);
        System.out.println("Has Nice View: " + rt.getWithView().toString());
        System.out.println("Has Wifi Enabled: " + rt.getWifiEnabled().toString());
        System.out.println("Smoking Is Allowed: " + rt.getSmokingIsAllowed().toString());

        System.out.println("");
    }

}
