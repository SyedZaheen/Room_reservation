package com;
import java.util.Scanner;

import com.models.Guest;

public class Creator {
    

    public Guest createGuest() {
        String name;
        long creditCardDetails;
        String address;
        String country;
        String gender;
        String identity;
        String nationality;
        long contact; 

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter guest name: ");
        name = sc.next();
        System.out.println("Enter credit card details: ");
        creditCardDetails = sc.nextLong();
        System.out.println("Enter address: ");
        address = sc.next();
        System.out.println("Enter country: ");
        country = sc.next();
        System.out.println("Enter gender: ");
        gender = sc.next();
        System.out.println("Enter identity: ");
        identity = sc.next();
        System.out.println("Enter nationality: ");
        nationality = sc.next();
        System.out.println("Enter contact: ");
        contact = sc.nextLong();

        Guest guest = new Guest(name, creditCardDetails, address, country, gender, identity, nationality, contact);
        return guest;

    }
}
