/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant;

import java.util.ArrayList;

public class Customer {
    private int id;
    private String name;
    private int visits;
    private ArrayList<String> marketingPrograms; 
    private ArrayList<String> loyaltyPrograms;    
    private ArrayList<String> rewardPrograms;  
    private ArrayList<Order> orders;  
    private ArrayList<Payment> payments; 
    private ArrayList<String> giftsAndOffers;

    // Constructor
    public Customer(int id, String name, int visits) {
        this.id = id;
        this.name = name;
        this.visits = visits;
        this.marketingPrograms = new ArrayList<>();
        this.loyaltyPrograms = new ArrayList<>();
        this.rewardPrograms = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.payments = new ArrayList<>();
        this.giftsAndOffers = new ArrayList<>();
    }
    private Employee assignedEmployee;

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

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
    
    public ArrayList<Payment> getPayments() {
        return payments;
    }

   
    public void RMarketingProgram(String programName) {
        marketingPrograms.add(programName);
        System.out.println(name + " registered in Marketing Program: " + programName);
    }

 
    public void RLoyaltyProgram(String programName) {
        loyaltyPrograms.add(programName);
        System.out.println(name + " registered in Loyalty Program: " + programName);
    }

    public void RRewardProgram(String programName) {
        rewardPrograms.add(programName);
        System.out.println(name + " registered in Reward Program: " + programName);
    }
    
    // إضافة طلب للعميل
    public void addOrder(Order order) {
        orders.add(order);
        System.out.println("Order added for " + name + ": " + order);
    }

    // إضافة دفعة للعميل
    public void addPayment(Payment payment) {
        payments.add(payment);
        System.out.println("Payment added for " + name + ": " + payment);
    }

    // عرض الطلبات
    public void listOrders() {
        System.out.println(name + "'s Orders:");
        if (orders.isEmpty()) {
            System.out.println("No orders available.");
        } else {
            for (Order order : orders) {
                System.out.println(order);
            }
        }
    }

    // عرض المدفوعات
    public void listPayments() {
        System.out.println(name + "'s Payments:");
        if (payments.isEmpty()) {
            System.out.println("No payments available.");
        } else {
            for (Payment payment : payments) {
                System.out.println(payment);
            }
        }
    }
  

    public void listPrograms() {
        System.out.println("Programs for " + name + ":");
        System.out.println("Marketing Programs: " + marketingPrograms);
        System.out.println("Loyalty Programs: " + loyaltyPrograms);
        System.out.println("Reward Programs: " + rewardPrograms);
    }
    
     public void addGiftOrOffer(String giftOrOffer) {
        giftsAndOffers.add(giftOrOffer);
        System.out.println("Gift/Offer added: " + giftOrOffer);
    }

    public void listGiftsAndOffers() {
        if (giftsAndOffers.isEmpty()) {
            System.out.println("No gifts or offers available for " + name + ".");
        } else {
            System.out.println("Gifts and Offers for " + name + ":");
            for (String giftOrOffer : giftsAndOffers) {
                System.out.println("- " + giftOrOffer);
            }
        }
    }

    // عرض ملف العميل بالكامل
    public void displayProfile() {
        System.out.println("Customer Profile:");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);


        // عرض الطلبات
        System.out.println("\nOrders:");
        if (orders.isEmpty()) {
            System.out.println("No orders available.");
        } else {
            for (Order order : orders) {
                System.out.println(order);
            }
        }

        // عرض المدفوعات
        System.out.println("\nPayments:");
        if (payments.isEmpty()) {
            System.out.println("No payments available.");
        } else {
            for (Payment payment : payments) {
                System.out.println(payment);
            }
        }

        // عرض الهدايا والعروض
        System.out.println("\nGifts and Offers:");
        if (giftsAndOffers.isEmpty()) {
            System.out.println("No gifts or offers available.");
        } else {
            for (String giftOrOffer : giftsAndOffers) {
                System.out.println("- " + giftOrOffer);
            }
        }
    }
    
    // Display customer details
    public void displayCustomerDetails() {
        System.out.println("Customer ID: " + id);
        System.out.println("Customer Name: " + name);
        System.out.println("Number of visits: " + visits);
    }

public Employee getAssignedEmployee() {
    return assignedEmployee;
}

public void setAssignedEmployee(Employee assignedEmployee) {
    this.assignedEmployee = assignedEmployee;
}
   
}

