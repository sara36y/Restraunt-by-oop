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

public class EmployeeUser extends User {
    private String position;

    // Constructor
    public EmployeeUser(int id, String name, String email, String password, String position) {
        super(id, name, email, password);
        this.position = position;
    }

    // Getter and Setter for Position
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    // عرض تفاصيل الموظف
    public void displayDetails() {
        System.out.println("Employee ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Position: " + position);
    }
   
}
