/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant;

/**
 *
 * @author hp
 */
public class Meal {
    
    private int id;
    private String name;
    private double price;
    private String description;

    // Constructor
    public Meal(int id, String name, double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Method to display meal details
    public void displayMealDetails() {
        System.out.println("Meal ID: " + id);
        System.out.println("Meal Name: " + name);
        System.out.println("Price: " + price);
        System.out.println("Description: " + description);
    }
}

