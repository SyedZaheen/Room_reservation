package com.views;

import java.util.List;

import com.controller.RoomControl;
import com.enums.RoomStatuses;
import com.enums.RoomTypes;
import com.models.Room;
import com.utils.MiscUtils;
/**
 * The class that provides the API for the presentation layer interacting with the user to get input on generating summaries on Room data and updating Room Statuses.  
 * @author DSF1 Group 1
 *
 */
public class RoomViews implements Views {
    @Override
    public void process() {
        int choice;
        RoomControl control = new RoomControl();

        while (true) {
            choice = UserInputViews.getUserChoice(new String[] {
                    "View all room options for this hotel",
                    "Check room availability by room number",
                    "Update room status",
                    "View full room status report",
                    "Return to main menu"
            });

            switch (choice) {
                case 1:
                    control.displayAllOptions();
                    break;

                case 2:
                    int roomnumber = UserInputViews.<Integer>getEachFieldFromUser("Please enter the room number",
                            "Not a valid room number", i -> MiscUtils.roomNumberExists(i), "Integer");
                    Room room = control.getRoom(roomnumber);
                    if (room.getStatus() == RoomStatuses.VACANT) {
                        System.out.println("The following are the details of room entered: \n");
                        System.out.println(room);
                        System.out.println("This room is available!");
                    } else {
                        System.out.println("The following are the details of room entered: \n");
                        System.out.println(room);
                        System.out.println(
                                "That room is not available! The current status of that room is "
                                        + room.getStatus().inString);
                    }
                    break;

                case 3:
                    Room rm = control.manageUpdateEntry();
                    if (rm == null)
                        System.out.println(
                                "Something went wrong trying to update the entry. ");
                    else
                        System.out.println("Updating successful!");
                    break;

                case 4:
                    // First, we grab all of the rooms
                    List<Room> rms = control.getAllRooms();
                    System.out.println("Which report would you like to see?");
                    String roomString = "";

                    int cho = UserInputViews.getUserChoice(new String[] {
                            "List of rooms by occupancy rate (Room Status = Vacant)",
                            "List of rooms by room status",
                            "Return to menu"
                    });

                    if (cho == 3)
                        break;
                    System.out.println("\n The following is the full report by occupancy rate: \n");

                    if (cho == 1) {
                        // For all room types
                        for (RoomTypes type : new RoomTypes[] {
                                RoomTypes.SINGLE,
                                RoomTypes.DOUBLE,
                                RoomTypes.DELUXE,
                                RoomTypes.VIPSUITE
                        }) {
                            int count = 0;
                            // We go throug all rooms
                            for (Room rom : rms) {
                                if (rom.getRoomType() == type && rom.getStatus() == RoomStatuses.VACANT) {
                                    roomString = roomString + rom.getRoomNumber().toString() + ", ";
                                    count++;
                                }
                            }
                            System.out.printf(type.inString + ": Number: %d out of 12\n", count);
                            System.out.println("Rooms: " + roomString + "\n");

                            count = 0;
                            roomString = "";
                        }
                    }

                    if (cho == 2) {
                        // For all room types
                        for (RoomStatuses status : new RoomStatuses[] {
                                RoomStatuses.VACANT,
                                RoomStatuses.OCCUPIED,
                                RoomStatuses.RESERVED,
                                RoomStatuses.MAINTENANCE
                        }) {
                            // We go throug all rooms
                            for (Room rom : rms) {
                                if (rom.getStatus() == status) {
                                    roomString = roomString + rom.getRoomNumber().toString() + ", ";
                                }
                            }
                            System.out.printf(status.inString + "\n");
                            System.out.println("Rooms: " + roomString + "\n");

                            roomString = "";
                        }
                    }
                    break;
                default:
                    return;
            }
        }
    }
}
