/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Employee {
    private int id;
    private String name;
    private String position;
    private final ArrayList<Customer> customers;
    private final ArrayList<Order> orders;
    private final ArrayList<Bill> bills; 
    private final ArrayList<Notification> notifications; 
    private final ArrayList<String> offers; 

    // Constructors
    public Employee(int id, String name, String position) {
        this.id = id;
        this.name = name;
        this.position = position;
        customers = new ArrayList<>();
        orders = new ArrayList<>();
        bills = new ArrayList<>();
        notifications = new ArrayList<>();
        offers = new ArrayList<>();
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

    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    public void displayEmployeeDetails() {
        System.out.println("ID: " + id + ", Name: " + name + ", Position: " + position);
    }

    // إدارة العملاء
    public void addCustomer(Customer customer) {
        customers.add(customer);
        System.out.println("Customer added: " + customer.getName());
    }

    public void deleteCustomer(int id) {
        boolean found = false;
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                customers.remove(customer);
                System.out.println("Customer deleted: " + customer.getName());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Customer with ID " + id + " not found.");
        }
    }

     public static List<String> searchCustomer(String fileName) {
    List<String> customers = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String line;
        StringBuilder customerDetails = new StringBuilder();
        boolean isCustomer = false;
        
        while ((line = br.readLine()) != null) {
            // Check if the line marks the start of a customer section
            if (line.startsWith("=== Customer ===")) {
                isCustomer = true;
                customerDetails.setLength(0); // Reset the StringBuilder for a new customer
            }

            if (isCustomer) {
                customerDetails.append(line).append("\n");
            }

            // If the line marks the end of a customer section, save the details
            if (line.isEmpty() && isCustomer) {
                customers.add(customerDetails.toString());
                isCustomer = false;
            }
        }
        
        // Add the last customer if there's no empty line at the end
        if (isCustomer) {
            customers.add(customerDetails.toString());
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    
    return customers;
}
    public void listCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
        } else {
            System.out.println("Customers List:");
            for (Customer customer : customers) {
                System.out.println("ID: " + customer.getId() + ", Name: " + customer.getName() + ", Visits: " + customer.getVisits());
            }
        }
    }

    // إدارة الطلبات
    public void makeOrder(String details) {
        Order newOrder = new Order(details);
        orders.add(newOrder);
        System.out.println("Order created: " + newOrder);
    }

    public void listOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders available.");
        } else {
            System.out.println("Orders List:");
            for (Order order : orders) {
                System.out.println(order);
            }
        }
    }

    // إدارة الفواتير
    public void createBill(int customerId, double amount) {
        Customer customer = null;
        for (Customer c : customers) {
            if (c.getId() == customerId) {
                customer = c;
                break;
            }
        }

        if (customer != null) {
            Bill newBill = new Bill(customer, amount);
            bills.add(newBill);
            System.out.println("Bill created: " + newBill);

            // التحقق من إجمالي المدفوعات
            checkPaymentRange(customer);
        } else {
            System.out.println("Customer with ID " + customerId + " not found. Bill not created.");
        }
    }

    public void listBills() {
        if (bills.isEmpty()) {
            System.out.println("No bills available.");
        } else {
            System.out.println("Bills List:");
            for (Bill bill : bills) {
                System.out.println(bill);
            }
        }
    }

    public void deleteBill(int billId) {
        Bill billToRemove = null;
        for (Bill bill : bills) {
            if (bill.getBillId() == billId) {
                billToRemove = bill;
                break;
            }
        }

        if (billToRemove != null) {
            bills.remove(billToRemove);
            System.out.println("Bill deleted: " + billToRemove);
        } else {
            System.out.println("Bill with ID " + billId + " not found.");
        }
    }

    // إدارة العروض
    public void addOffer(String offerDetails) {
        offers.add(offerDetails);
        String notificationMessage = "New Offer Added: " + offerDetails;
        sendNotification(notificationMessage);
    }

    public void listOffers() {
        if (offers.isEmpty()) {
            System.out.println("No offers available.");
        } else {
            System.out.println("Available Offers:");
            for (String offer : offers) {
                System.out.println(offer);
            }
        }
    }

    // إدارة الإشعارات
    private void sendNotification(String message) {
        Notification notification = new Notification(message);
        notifications.add(notification);
        System.out.println("Notification Sent: " + message);
    }

    public void listNotifications() {
        if (notifications.isEmpty()) {
            System.out.println("No notifications available.");
        } else {
            System.out.println("Notifications:");
            for (Notification notification : notifications) {
                System.out.println(notification);
            }
        }
    }

    // إلغاء الطلب
    public void cancelOrder(int orderId) {
        Order orderToRemove = null;
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                orderToRemove = order;
                break;
            }
        }

        if (orderToRemove != null) {
            orders.remove(orderToRemove);
            System.out.println("Order cancelled: " + orderToRemove);
        } else {
            System.out.println("Order with ID " + orderId + " not found.");
        }
    }

    // التحقق من المدفوعات وإرسال إشعار بالهدية
    private void checkPaymentRange(Customer customer) {
        double totalPayment = 0;
        for (Bill bill : bills) {
            if (bill.getCustomer().getId() == customer.getId()) {
                totalPayment += bill.getAmount();
            }
        }

        if (totalPayment >= 500) {
            String giftMessage = "Congratulations " + customer.getName() + ", you have earned a gift for your payments reaching " + totalPayment;
            sendNotification(giftMessage);
        }
    }
    public void RCustomerPrograms(Customer customer, String marketingProgram, String loyaltyProgram, String rewardProgram) {
        customer.RMarketingProgram(marketingProgram);
        customer.RLoyaltyProgram(loyaltyProgram);
        customer.RRewardProgram(rewardProgram);
    }
}

