/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant;

public class AdminUser extends User {
    // Constructor
    public AdminUser(int id, String name, String email, String password) {
        super(id, name, email, password);
    }
    
    public void manageUsers(User user, String action) {
        switch (action.toLowerCase()) {
            case "delete" -> System.out.println("Admin deleted user: " + user.getName());
            // Logic to delete user
            case "update" -> System.out.println("Admin is updating user: " + user.getName());
            // Logic to update user
            default -> System.out.println("Invalid action for managing users.");
        }
    }
}
