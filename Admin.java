/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.mycompany.restaurant.DataHandler;
import com.mycompany.restaurant.*;

/**
 *
 * @author hp
 */
public class Admin {
    private String username;
    private String password;
    // private final ArrayList<Employee> employees;
    private final ArrayList<Meal> meals;
    private final ArrayList<Customer> customers;
    private final ArrayList<String> specialOffers;
    private final ArrayList<String> marketingMessages;
    private static Scanner scanner = new Scanner(System.in);
    private static final String EMPLOYEE_FILE_PATH = "Restaurant/src/main/java/com/mycompany/restaurant/employee.txt";  // تحديث المسار حسب الحاجة
    static final String CUSTOMER_FILE_PATH = "Restaurant/src/main/java/com/mycompany/restaurant/customer.txt";
    private List<Employee> employees = new ArrayList<>();

 //Constructor
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
        this.employees = new ArrayList<>();
        this.meals = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.specialOffers = new ArrayList<>();
        this.marketingMessages = new ArrayList<>();
        System.out.println("Admin created with username: " + username + " and password: " + password);
        loadEmployeesFromFile();

    }
    public void addEmployee(Employee employee) {
        employees.add(employee);
        saveEmployeesToFile();  // حفظ الموظفين في الملف
    }
    public void deleteEmployee(int id) {
        employees.removeIf(emp -> emp.getId() == id);
        saveEmployeesToFile();  // حفظ التغييرات في الملف
    }
    public void updateEmployee(int id, String newName, String newPosition) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                emp.setName(newName);
                emp.setPosition(newPosition);
                saveEmployeesToFile();  // حفظ التغييرات في الملف
                break;
            }
        }
    }
    public void listEmployees() {
        for (Employee emp : employees) {
            emp.displayEmployeeDetails();
        }
    }
    public void searchEmployee(int id) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                emp.displayEmployeeDetails();
                return;
            }
        }
        System.out.println("Employee NOT found.");
    }
    public void loadEmployeesFromFile() {
        List<String> lines = DataHandler.readDataFromFile(EMPLOYEE_FILE_PATH);
        if (lines != null) {
            for (String line : lines) {
                String[] parts = line.split(",");  // افترض أن البيانات مفصولة بفواصل
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String position = parts[2].trim();
                    employees.add(new Employee(id, name, position));
                }
            }
        }
    }
    public void saveEmployeesToFile() {
        List<String> dataToSave = new ArrayList<>();
        for (Employee emp : employees) {
            dataToSave.add(emp.getId() + "," + emp.getName() + "," + emp.getPosition());
        }
        DataHandler.writeDataToFile(EMPLOYEE_FILE_PATH, dataToSave);
    }

    public static void alterAdminCredentials(String LOGIN_FILE_PATH) {
        Scanner scanner = new Scanner(System.in);
    
        // طلب الاسم الجديد وكلمة المرور
        System.out.print("Enter new Username: ");
        String newUsername = scanner.nextLine().trim();
    
        System.out.print("Enter new Password: ");
        String newPassword = scanner.nextLine().trim();
    
        // قراءة البيانات الحالية من الملف
        List<String> credentials = DataHandler.readDataFromFile(LOGIN_FILE_PATH);
        if (credentials == null) {
            System.out.println("Error: Failed to read credentials from login file.");
            return;
        }
    
        // تعديل بيانات الاعتماد للمسؤول
        boolean adminFound = true;
        String oldCredentials = null;
        for (int i = 0; i < credentials.size(); i++) {
            String credential = credentials.get(i);
            if (credential.startsWith("Admin")) {
                String[] parts = credential.split("=");
                String oldUsername = parts[1];  // استخراج الاسم القديم
                String oldPassword = parts[2];  // استخراج كلمة المرور القديمة
    
                // بناء البيانات القديمة بالكامل لتعديلها في الملف لاحقاً
                oldCredentials = "Admin=" + oldUsername + "=" + oldPassword;
    
                // تحديث البيانات إلى الاسم الجديد وكلمة المرور الجديدة
                credentials.set(i, "Admin=" + newUsername + "=" + newPassword);
                adminFound = true;
    
                // طباعة البيانات القديمة والجديدة للتأكد من التعديل
                System.out.println("Old Admin Credentials: " + oldUsername + "=" + oldPassword);
                System.out.println("New Admin Credentials: " + newUsername + "=" + newPassword);
                break;
            }
        }
    
        // إذا لم يتم العثور على بيانات المسؤول في الملف
        if (!adminFound) {
            System.out.println("Admin credentials not found.");
            return;
        }
    
        // حفظ التعديلات في الملف
        boolean success = DataHandler.writeDataToFile(LOGIN_FILE_PATH, credentials);
        if (success) {
            System.out.println("Admin credentials updated successfully!");
    
            // استبدال البيانات القديمة في الملف
            if (oldCredentials != null) {
                // استبدال بيانات المسؤول القديمة بالجديدة باستخدام الدالة updateDataInFile
                DataHandler.updateDataInFile(LOGIN_FILE_PATH, oldCredentials, "Admin=" + newUsername + "=" + newPassword);
            }
        } else {
            System.out.println("Error updating credentials.");
        }
    }
    
    
    //ADD MEAL
    public void addMeal(Meal meal) {
        meals.add(meal);
        System.out.println("Meal Added successfully");
    }
    //DELETE MEAL
    public void deleteMeal(int id) {
        for(Meal meal : meals) {
            if(meal.getId() == id) {
                meals.remove(meal);
                System.out.println("Meal deleted Successsfully");
                return;
            }
        }
        System.out.println("Mealwith ID " + id + "not found");
    }
    //UPDATE MEAL
    public void updateMeal(int id, String newName, double newPrice, String newDescription) {
        for(Meal meal : meals) {
            if(meal.getId() == id) {
                meal.setName(newName);
                meal.setPrice(newPrice);
                meal.setDescription(newDescription);
                System.out.println("Meal updated Successsfully");
                return;
            }
        }
        System.out.println("Meal with ID " + id + "not foun");
    }
    //LIST MEALS
    public void listMeals() {
        if(meals.isEmpty()) {
            System.out.println("No meals available");
            return;
        }
        for(Meal meal : meals) {
            meal.displayMealDetails();
        }
    }

    //Getter and Setter
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
    return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void displayAdminDetails() {
    
    System.out.println("Username: " + username);
    System.out.println("Password: " + password);

    }

    void listNotifications() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
