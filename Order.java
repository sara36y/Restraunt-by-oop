/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant;

public class Order {
    private static int nextOrderId = 1;
    private int orderId;
    private String details;

    public Order(String details) {
        this.orderId = nextOrderId++;
        this.details = details;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + ", Details: " + details;
    }
}
