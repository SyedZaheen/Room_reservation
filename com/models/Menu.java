package com.models;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;

public class Menu {
    private int menuSize = 0;
    public ArrayList <MenuItem> items; //id = key, value = "name, description of how it's prepared, price"
    
    
    public Menu() {
        this.items = new ArrayList<>();
        items.add(new MenuItem("Chicken", "chicken", 4));
        menuSize++;
        items.add(new MenuItem("Fish", "seafood", 5));
        menuSize++;
        items.add(new MenuItem("Beef", "cow", 6));
        menuSize++;
        items.add(new MenuItem("Mutton", "lamb", 7));
        menuSize++;
    }
    
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

        items.add(newItem);
    }

    public void removeItem() {
        Scanner sc = new Scanner(System.in);
        int foodID;

        System.out.println("Enter ID of MenuItem that you wish to remove:");
        foodID = sc.nextInt();

        items.remove(foodID);
        
        for(int i = foodID; i < menuSize - 1; i++) // After removal, the MenuItem that follows after will be shifted upwards.
            items.add(i, items.get(i + 1));

        this.menuSize--;
    }

    public String[] getAllItemsInString() {
        String[] s = new String[menuSize];
        for(int i = 0; i < menuSize; i++) {
            s[i] = items.get(i).toString();
        }
        return s;
    }
}
