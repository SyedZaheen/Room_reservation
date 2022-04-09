package com.models;
import java.util.HashMap;
import java.util.Scanner;

public class Menu {
    private int menuSize = 0;
    private HashMap <Integer, MenuItem> items = new HashMap <Integer, MenuItem> (); //id = key, value = "name, description of how it's prepared, price"

    public Menu() {}
    
    public Menu(int menuSize) {
        this.menuSize = menuSize;
    }

    public void addItem() {
        Scanner sc = new Scanner(System.in);
        String itemName, description;
        double price;
        
        System.out.println("Enter Item Name:");
        itemName = sc.nextLine();
        System.out.println("Enter Item Description");
        description = sc.nextLine();
        System.out.println("Enter Item Price");
        price = sc.nextDouble();

        MenuItem newItem = new MenuItem(itemName, description, price);
        this.menuSize++;

        items.put(this.menuSize, newItem);
    }

    public void removeItem() {
        Scanner sc = new Scanner(System.in);
        int foodID;

        System.out.println("Enter ID of MenuItem that you wish to remove:");
        foodID = sc.nextInt();

        items.remove(foodID);
        
        for(int i = foodID; i < menuSize - 1; i++) // After removal, the MenuItem that follows after will be shifted upwards.
            items.put(i, items.get(i + 1));

        this.menuSize--;
    }

    public void display() {
        for(int i = 1; i <= menuSize; i++) {
            items.get(i).toString();
        }
    }
}
