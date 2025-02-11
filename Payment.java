package com.mycompany.restaurant;

/**
 *
 * @author Mariam Yasser
 */
public class Payment {
    private double amount;
    private String paymentMethod;
    
    // Constructor
    public Payment(double amount, String paymentMethod) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Amount: " + amount + ", Payment Method: " + paymentMethod;
    }
}
