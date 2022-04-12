package com.models;
public class MenuItem implements Model<MenuItem> {
    private String name;
    private String description;
    private double price;

    public MenuItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s: %s - %.2f", this.name, this.description, this.price);
    }

    public double getPrice() {
        return this.price;
    }
    
}


