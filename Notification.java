/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant;

public class Notification {
    private static int nextNotificationId = 1;
    private int notificationId;
    private String message;

    public Notification(String message) {
        this.notificationId = nextNotificationId++;
        this.message = message;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Notification ID: " + notificationId + ", Message: " + message;
    }
}
