package com.controller;

import java.time.*;
import java.util.List;

import com.db.roomDB.RoomDB;
import com.enums.RoomStatuses;
import com.enums.RoomTypes;
import com.models.Room;
import com.utils.MiscUtils;
import com.views.UserInputViews;

/**
 * RoomControl.java
 * A controller class that realises the CreatorController interface.
 * It consists of methods that will enable its users to control the outcome of a Room object.
 * 
 * @author DSF1 Group 1
 */

public class RoomControl implements CreatorController<Room> {

    /** 
     * manageCreateEntry()
     * It is a method that would create a new Room object. If the object created is valid, it will be subsequently serialised into the RoomDB.
     * @return the Room object that is created
     */

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

    /** 
     * manageCreateEntry()
     * It is an overloaded method that takes in date parameters in the process of creating a Room object. If the object created is valid, it will be subsequently serialised into the RoomDB.
     * @param cID this is the check-in date.
     * @param cOD this is the check-out date.
     * @return the Room object that is created.
     */

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

        RoomStatuses status = cID.compareTo(LocalDate.now()) == 0 ? RoomStatuses.OCCUPIED : RoomStatuses.VACANT;
        rmdb.updateRoomStatus(vacantRoom, status);
        // Return the room that has just been occupied

        return vacantRoom;
    }

    /** 
     * printRoomOptionByType()
     * It is a method that will print all the amenities and rates of a Room by a specific RoomType.
     * @param rt the particular RoomType that the user wishes to enquire on.
     */

    private void printRoomOptionByType(RoomTypes rt) {
        System.out.println("Type: " + rt.inString);
        System.out.println("Rate Per Night: SGD$" + rt.getRatePerNight());
        System.out.println("BedType: " + rt.getBedType().inString);
        System.out.println("Has Nice View: " + rt.getWithView().toString());
        System.out.println("Has Wifi Enabled: " + rt.getWifiEnabled().toString());
        System.out.println("Smoking Is Allowed: " + rt.getSmokingIsAllowed().toString());

        System.out.println("");
    }

    /**
     * displayAllOptions()
     * It is a method that will print all the amenities and rates of each RoomType in the hotel.
     */

    public void displayAllOptions() {
        System.out.println("The following are the room options for this hotel: ");
        System.out.println("");
        printRoomOptionByType(RoomTypes.SINGLE);
        printRoomOptionByType(RoomTypes.DOUBLE);
        printRoomOptionByType(RoomTypes.DELUXE);
        printRoomOptionByType(RoomTypes.VIPSUITE);
    }

    /** 
     * getRoom()
     * It is a getter function that will get a Room object by its room number.
     * @param roomnumber the particular room number that is being searched for.
     * @return the Room object that is being searched for.
     */

    public Room getRoom(int roomnumber) {
        RoomDB db = new RoomDB();
        Room rm = db.findSingleEntry(roomnumber);
        if (rm == null)
            return null;
        return rm;
    }

    /** 
     * manageUpdateEntry()
     * It is a method that updates the status of the Room object, which will then be serialised into the DB.
     * @return the Room object that has been just updated.
     */

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

    /** 
     * getAllRooms()
     * It is a method that gets all the rooms that are serialised in the RoomDB.
     * @return the list of Room objects within the RoomDB.
     */

    public List<Room> getAllRooms() {
        return new RoomDB().findAllEntries();
    }

}
