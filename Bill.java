/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant;

public class Bill {
    private static int nextBillId = 1;
    private int billId;
    private Customer customer;
    private double amount;

    public Bill(Customer customer, double amount) {
        this.billId = nextBillId++;
        this.customer = customer;
        this.amount = amount;
    }

    public int getBillId() {
        return billId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Bill ID: " + billId + ", Customer: " + customer.getName() + ", Amount: $" + amount;
    }
}
