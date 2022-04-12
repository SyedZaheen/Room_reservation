package com.models;

import java.util.Scanner;

import com.db.menuDB.MenuItemDB;
import java.util.List;

public class Menu {
    private int menuSize = 0;
    private List<MenuItem> items; 

    public Menu() {
        items = new MenuItemDB().findAllEntries();
        for (MenuItem menuItem : items) {
            this.menuSize++;
        }
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

        for (int i = foodID; i < menuSize - 1; i++) // After removal, the MenuItem that follows after will be shifted
                                                    // upwards.
            items.add(i, items.get(i + 1));

        this.menuSize--;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public String[] getAllItemsInString() {
        List<MenuItem> items = new MenuItemDB().findAllEntries();
        if (items == null) return new String[]{};

        String[] s = new String[menuSize];
        for (int i = 0; i < menuSize; i++) {
            s[i] = items.get(i).toString();
        }
        return s;
    }
}
