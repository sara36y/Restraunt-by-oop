/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant;


public class User {
    private int id;
    private String name;
    private String email;
    private String password;

    // Constructor
    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    // No setter for ID to prevent updates

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // تسجيل الدخول
    public void login(String inputEmail, String inputPassword) {
        if (this.email.equals(inputEmail) && this.password.equals(inputPassword)) {
            System.out.println(name + " logged in successfully.");
        } else {
            System.out.println("Invalid email or password.");
        }
    }

    // تسجيل الخروج
    public void logout() {
        System.out.println(name + " logged out.");
    }

    // تحديث المعلومات
    public void updateInfo(String newName, String newEmail, String newPassword) {
        this.name = newName;
        this.email = newEmail;
        this.password = newPassword;
        System.out.println("Information updated successfully for: " + name);
    }
}
