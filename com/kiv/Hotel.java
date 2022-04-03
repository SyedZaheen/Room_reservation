package com.kiv;
import java.util.Scanner;

import com.models.Guest;
import com.models.Reservation;
import com.models.room.*;

public class Hotel {
    public Hotel() {
        SingleRoom singleRooms[] = new SingleRoom[12];
        DoubleRoom doubleRooms[] = new DoubleRoom[12];
        DeluxeRoom deluxeRooms[] = new DeluxeRoom[12];
        vipSuiteRoom vipSuiteRooms[] = new vipSuiteRoom[12];
        for(int i = 0; i < 12; i++) {
            singleRooms[i] = new SingleRoom(i, Room.BedTypes.SINGLE);
            doubleRooms[i] = new DoubleRoom(i+12, Room.BedTypes.DOUBLE);
            deluxeRooms[i] = new DeluxeRoom(i+24, Room.BedTypes.MASTER);
            vipSuiteRooms[i] = new vipSuiteRoom(i+36, Room.BedTypes.MASTER);
        }
    }

    public Reservation makeReservation() {
        Scanner sc = new Scanner(System.in);

        //to get vacant room 
        /*
        System.out.println("Enter room type: ");
        System.out.println("(1) Single: ");
        System.out.println("(2) Double: ");
        System.out.println("(3) Deluxe: ");
        System.out.println("(4) VIP Suite: ");
        int choice = sc.nextInt();
    
        
        switch(choice) {
            case 1:
                for (int i = 0; i < 12; i++) {
                    if(singleRooms[i].getStatus() == Room.Statuses.VACANT) {
                        SingleRoom room = singleRooms[i];
                        break;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < 12; i++) {
                    if(doubleRooms[i].getStatus() == Room.Statuses.VACANT) {
                        DoubleRoom room = doubleRooms[i];
                        break;
                    }9
                }
                break;
            case 3:
                for (int i = 0; i < 12; i++) {
                    if(deluxeRooms[i].getStatus() == Room.Statuses.VACANT) {
                        DeluxeRoom room = deluxeRooms[i];
                        break;
                    }
                }
                break;
            case 4:
                for (int i = 0; i < 12; i++) {
                    if(vipSuiteRooms[i].getStatus() == Room.Statuses.VACANT) {
                        vipSuiteRoom room = vipSuiteRooms[i];
                        break;
                    }
                }
                break;
        }
        */

        Creator create = new Creator();
        Guest guest = create.createGuest();
    }

}
