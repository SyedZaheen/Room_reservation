package com.models;

/**
 * Represents a single menu item
 * @author DSF 1 Group 1
 *
 */
public class MenuItem implements Model<MenuItem> {
    private String name;
    private String description;
    private double price;
    
    /**
     * Creates a menu item object with a given name, description and price.
     * @param name This menu item name
     * @param description This menu item description
     * @param price This menu item price
     */

    public MenuItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
   /**
    * Gets this menu item's name
    * @return This menu item's name
    */

    public String getName() {
        return name;
    }
    
    /**
     * Gets this menu item's description
     * @return This menu item's description
     */

    public String getDescription() {
        return description;
    }

    /**
     * Gets this menu item's price
     * @return This menu item's price
     */
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s: %s - %.2f", this.name, this.description, this.price);
    }
}
