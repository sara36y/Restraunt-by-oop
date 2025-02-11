package com.mycompany.restaurant;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.mycompany.restaurant.DataHandler;
import com.mycompany.restaurant.Meal;
import com.mycompany.restaurant.Admin;
import com.mycompany.restaurant.Customer;
import com.mycompany.restaurant.Employee;

public class Restaurant {
    private static Admin admin;
    static Scanner scanner = new Scanner(System.in);
    static final String LOGIN_FILE_PATH = "Restaurant/src/main/java/com/mycompany/restaurant/login.txt";
    static final String EMPLOYEE_FILE_PATH = "Restaurant/src/main/java/com/mycompany/restaurant/employee.txt";
    static final String USER_FILE_PATH = "Restaurant/src/main/java/com/mycompany/restaurant/user.txt";
    static final String CUSTOMER_FILE_PATH = "Restaurant/src/main/java/com/mycompany/restaurant/customer.txt";
    static final String SIGNUP_FILE_PATH = "Restaurant/src/main/java/com/mycompany/restaurant/sign.txt";
    static final String MEAL_FILE_PATH = "Restaurant/src/main/java/com/mycompany/restaurant/meal.txt";
    static final String OFFER_FILE_PATH  ="Restaurant/src/main/java/com/mycompany/restaurant/specialOffers.txt";
    static final String MRKETING_FILE_PATH ="Restaurant/src/main/java/com/mycompany/restaurant/marketingMessages.txt";
    static final String LOYALTY_FILE_PATH ="Restaurant/src/main/java/com/mycompany/restaurant/loyaltyProgram.txt";
    private static List<Meal> meals = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n==== Main Menu ====");
            System.out.println("1. Administrative Module");
            System.out.println("2. Employee Module");
            System.out.println("3. Customer Module");
            System.out.println("4. User Module");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // استهلاك السطر المتبقي

                switch (choice) {
                    case 1:
                        handleLogin("Admin");
                        break;
                    case 2:
                        handleLogin("Employee");
                        break;
                    case 3:
                        handleLogin("Customer");
                        break;
                    case 4:
                        handleLogin("User");
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        scanner.close(); // إغلاق Scanner
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // استهلاك الإدخال غير الصحيح
            }
        }
    }

    // دالة التعامل مع الدخول والتسجيل
    public static void handleLogin(String module) {
        while (true) {
            System.out.println("\n==== " + module + " Module ====");

            // في حالة الـ Admin يظهر فقط login و exit
            if (module.equals("Admin")) {
                System.out.println("1. Login");
                System.out.println("2. Exit");
            } else {
                // لبقية الموديلات يظهر login و signup و exit
                System.out.println("1. Login");
                System.out.println("2. Signup");
                System.out.println("3. Exit");
            }

            System.out.print("Choose an option: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        if (login(module)) {
                            if (module.equals("Admin")) {
                                administrativeMenu();

                            } else if (module.equals("Employee")) {
                                // employeeMenu();
                            } else if (module.equals("Customer")) {
                                customerMenu();
                            } else if (module.equals("User")) {
                                userMenu();
                            }
                        }
                        break;
                    case 2:
                        if (module.equals("Admin")) {
                            System.out.println("Exiting Admin Module...");
                            return; // Exit to main menu if admin selects Exit
                        }
                        System.out.println("Signing up as " + module + "...");
                        signup(module);
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        return; // Exit to main menu
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // استهلاك الإدخال غير الصحيح
            }
        }
    }

    // دالة تسجيل الدخول

    // Method to handle the login process
    public static boolean login(String module) {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine().trim(); // Remove extra spaces
        System.out.print("Enter Password: ");
        String password = scanner.nextLine().trim(); // Remove extra spaces

        // Read credentials from the file
        List<String> credentials = DataHandler.readDataFromFile(LOGIN_FILE_PATH);
        if (credentials == null) {
            System.out.println("Error: Failed to read credentials from login file.");
            return false;
        }

        // Check if the username and password match any stored credentials
        for (String credential : credentials) {
            String[] parts = credential.split("="); // Split the string into username and password
            if (parts.length == 2 && parts[0].trim().equals(username) && parts[1].trim().equals(password)) {
                System.out.println("Login successful as " + module + ".");
                return true; // Login successful
            }
        }
        System.out.println("Invalid login credentials.");
        return false; // Login failed
    }

    // Method to authenticate a user and return an Admin object
    public static Admin authenticate(String username, String password) {
        // Replace this with actual logic to check credentials (e.g., from a database)
        if ("admin".equals(username) && "password".equals(password)) {
            return new Admin(username, password);  // Return an Admin object if successful
        } else {
            return null;  // Return null if login fails
        }
    }

    // Example of calling login and authenticate methods
    public static void handleLogin() {
        System.out.print("Enter module (e.g., Admin, User): ");
        String module = scanner.nextLine();

        boolean loginSuccessful = login(module);  // Check login credentials

        if (loginSuccessful) {
            System.out.print("Enter Username: ");
            String username = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

        }
        // System.out.println("Invalid credentials. Please try again.");
        // return false;

    }
    // دالة التسجيل (Signup)
    public static void signup(String module) {
        System.out.println("\n==== Signup for " + module + " ====");

        String name = "";
        while (true) {
            System.out.print("Enter your Name: ");
            name = scanner.nextLine();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty. Please try again.");
            } else {
                break;
            }
        }

        String phone = "";
        while (true) {
            System.out.print("Enter your Phone Number (10 digits): ");
            phone = scanner.nextLine();
            if (!phone.matches("\\d{10}")) {
                System.out.println("Invalid phone number. Please enter a 10-digit number.");
            } else {
                break;
            }
        }

        String email = "";
        while (true) {
            System.out.print("Enter your Email: ");
            email = scanner.nextLine();
            if (!isValidEmail(email)) {
                System.out.println("Invalid email format. Please try again.");
            } else {
                break;
            }
        }

        String username = "";
        while (true) {
            System.out.print("Enter your Username: ");
            username = scanner.nextLine();
            if (username.isEmpty()) {
                System.out.println("Username cannot be empty. Please try again.");
            } else {
                break;
            }
        }

        String password = "";
        while (true) {
            System.out.print("Enter your Password (at least 6 characters): ");
            password = scanner.nextLine();
            if (password.length() < 6) {
                System.out.println("Password must be at least 6 characters long.");
            } else {
                break;
            }
        }

        // تخزين بيانات تسجيل الدخول فقط في login.txt
        List<String> loginData = new ArrayList<>();
        loginData.add("=== " + module + " ===");
        loginData.add(username + "=" + password);
        DataHandler.appendDataToFile(LOGIN_FILE_PATH, loginData);

        // تخزين بيانات الموظف (مثل الاسم، الهاتف، البريد الإلكتروني) في ملف
        // employee.txt إذا كان المستخدم من نوع الموظف
        if (module.equals("Employee")) {
            List<String> employeeData = new ArrayList<>();
            employeeData.add("Name: " + name);
            employeeData.add("Phone: " + phone);
            employeeData.add("Email: " + email);
            employeeData.add("Username: " + username);
            employeeData.add("Password: " + password); // يمكن استبعاد كلمة المرور إذا أردت
            DataHandler.appendDataToFile(EMPLOYEE_FILE_PATH, employeeData);
        }

        // تخزين بيانات المستخدم (مثل الاسم، البريد الإلكتروني) في ملف user.txt إذا كان
        // المستخدم من نوع المستخدم
        if (module.equals("User")) {
            List<String> userData = new ArrayList<>();
            userData.add("Name: " + name);
            userData.add("Email: " + email);
            userData.add("Username: " + username);
            userData.add("Password: " + password); // يمكن استبعاد كلمة المرور إذا أردت
            DataHandler.appendDataToFile(USER_FILE_PATH, userData);
        }

        // تخزين بيانات العميل (مثل الاسم، الهاتف، البريد الإلكتروني) في ملف
        // customer.txt إذا كان المستخدم من نوع العميل
        if (module.equals("Customer")) {
            List<String> customerData = new ArrayList<>();
            customerData.add("Name: " + name);
            customerData.add("Phone: " + phone);
            customerData.add("Email: " + email);
            customerData.add("Username: " + username);
            customerData.add("Password: " + password); // يمكن استبعاد كلمة المرور إذا أردت
            DataHandler.appendDataToFile(CUSTOMER_FILE_PATH, customerData);
        }

        System.out.println("\nSignup successful for " + module + "!");

        List<String> signData = new ArrayList<>();
        signData.add("=== " + module + " ===");
        signData.add("Name: " + name);
        signData.add("Phone: " + phone);
        signData.add("Email: " + email);
        signData.add("Username: " + username);
        signData.add("Password: " + password);
        DataHandler.appendDataToFile(SIGNUP_FILE_PATH, signData);
        // System.out.println("\nSignup successful for " + module + "!");
    }

    // دالة للتحقق من صحة البريد الإلكتروني
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    // Administrative Module Menu
    public static void administrativeMenu() {
        while (true) {
            System.out.println("\n==== Administrative Module ====");
            System.out.println("1. Alter Admin username and password");
            System.out.println("2. Manage Employees (Add, Delete, Update, List, Search)");
            System.out.println("3. Manage Meals (Add, Delete, Update, List, Search)");
            System.out.println("4. Create Special Offers, Marketing, Loyalty, and Reward Programs");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // استهلاك السطر المتبقي

                switch (choice) {
                    case 1:
                        Admin.alterAdminCredentials(LOGIN_FILE_PATH);
                        break;
                    case 2:
                        manageEmployees();
                        break;
                    case 3:
                        manageMeals();
                        break;
                    case 4:
                    System.out.println("Back to main Menu ");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    // Function to manage employees (can be expanded)
    public static void manageEmployees() {
        Admin admin = new Admin("adminUsername", "adminPassword");
        admin.loadEmployeesFromFile(); // تحميل الموظفين من الملف عند بداية الإدارة
        while (true) {
            System.out.println("\n==== Employee Management ====");
            System.out.println("1. Add Employee");
            System.out.println("2. Delete Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. List Employees");
            System.out.println("5. Search Employee");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // استهلاك السطر المتبقي

                switch (choice) {
                    case 1:
                        addEmployee(admin);
                        break;
                    case 2:
                        deleteEmployee(admin);
                        break;
                    case 3:
                        updateEmployee(admin);
                        break;
                    case 4:
                        admin.listEmployees();
                        break;
                    case 5:
                        searchEmployee(admin);
                        break;
                    case 6:
                        return; // العودة إلى القائمة الرئيسية
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // استهلاك الإدخال غير الصحيح
            }
        }
    }

    // إضافة موظف جديد
    public static void addEmployee(Admin admin) {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // استهلاك السطر المتبقي
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Employee Position: ");
        String position = scanner.nextLine();

        Employee employee = new Employee(id, name, position);
        admin.addEmployee(employee);
        System.out.println("Employee added successfully.");
    }

    // حذف موظف
    public static void deleteEmployee(Admin admin) {
        System.out.print("Enter Employee ID to delete: ");
        int id = scanner.nextInt();
        admin.deleteEmployee(id);
        System.out.println("Employee deleted successfully.");
    }

    // تحديث موظف
    public static void updateEmployee(Admin admin) {
        System.out.print("Enter Employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // استهلاك السطر المتبقي
        System.out.print("Enter New Name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter New Position: ");
        String newPosition = scanner.nextLine();

        admin.updateEmployee(id, newName, newPosition);
        System.out.println("Employee updated successfully.");
    }

    // البحث عن موظف
    public static void searchEmployee(Admin admin) {
        System.out.print("Enter Employee ID to search: ");
        int id = scanner.nextInt();
        admin.searchEmployee(id);
    }

    public static void addMeal() {
        System.out.print("Enter Meal ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter Meal Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Meal Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter Meal Description: ");
        String description = scanner.nextLine();

        // إضافة الوجبة إلى القائمة
        Meal newMeal = new Meal(id, name, price, description);
        meals.add(newMeal);

        // حفظ الوجبة في الملف
        saveMealsToFile();

        System.out.println("Meal added successfully!");
    }

    // دالة لحفظ الوجبات في الملف
    public static void saveMealsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MEAL_FILE_PATH))) {
            for (Meal meal : meals) {
                // Write meal details in the format: ID=Name=Price=Description
                writer.write(meal.getId() + "=" + meal.getName() + "=" + meal.getPrice() + "=" + meal.getDescription());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving meals to file: " + e.getMessage());
        }
    }

    // دالة لتحميل الوجبات من الملف (إذا كان موجودًا)
    public static void loadMealsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(MEAL_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 4) { // Ensure we have 4 parts: ID, Name, Price, Description
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    String description = parts[3];
                    meals.add(new Meal(id, name, price, description));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading meals from file: " + e.getMessage());
        }
    }

    // دالة حذف وجبة بناءً على ID
    public static void deleteMeal() {
        System.out.print("Enter Meal ID to delete: ");
        int id = scanner.nextInt();

        // البحث عن الوجبة وإزالتها
        meals.removeIf(meal -> meal.getId() == id);

        // حفظ التغييرات في الملف
        saveMealsToFile();

        System.out.println("Meal deleted successfully!");
    }

    // دالة تعديل وجبة
    public static void updateMeal() {
        System.out.print("Enter Meal ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // البحث عن الوجبة
        for (Meal meal : meals) {
            if (meal.getId() == id) {
                System.out.print("Enter new Meal Name: ");
                String newName = scanner.nextLine();
                meal.setName(newName);

                System.out.print("Enter new Meal Price: ");
                double newPrice = scanner.nextDouble();
                meal.setPrice(newPrice);

                scanner.nextLine(); // Consume newline for next input

                System.out.print("Enter new Meal Description: ");
                String newDescription = scanner.nextLine();
                meal.setDescription(newDescription);

                // حفظ التغييرات في الملف
                saveMealsToFile();

                System.out.println("Meal updated successfully!");
                return;
            }
        }

        System.out.println("Meal with ID " + id + " not found.");
    }

    // دالة عرض جميع الوجبات
    public static void listMeals() {
        if (meals.isEmpty()) {
            System.out.println("No meals available.");
        } else {
            System.out.println("Meals List:");
            for (Meal meal : meals) {
                meal.displayMealDetails();
            }
        }
    }

    // دالة البحث عن وجبة بناءً على ID
    public static void searchMeal() {
        System.out.print("Enter Meal ID to search: ");
        int id = scanner.nextInt();

        for (Meal meal : meals) {
            if (meal.getId() == id) {
                meal.displayMealDetails();
                return;
            }
        }

        System.out.println("Meal with ID " + id + " not found.");
    }

    public static void manageMeals() {
        loadMealsFromFile(); // Load meals from the file when the program starts

        while (true) {
            System.out.println("\n==== Manage Meals ====");
            System.out.println("1. Add Meal");
            System.out.println("2. Delete Meal");
            System.out.println("3. Update Meal");
            System.out.println("4. List Meals");
            System.out.println("5. Search Meal");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        addMeal();
                        break;
                    case 2:
                        deleteMeal();
                        break;
                    case 3:
                        updateMeal();
                        break;
                    case 4:
                        listMeals();
                        break;
                    case 5:
                        searchMeal();
                        break;
                    case 6:
                        System.out.println("Exiting Meal Management...");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume invalid input
            }
        }
    }




    // Employee Module Menu
    public static void employeeMenu(Employee employee, Scanner scanner) {
        while (true) {
            System.out.println("\nEmployee Menu:");
            System.out.println("1. Add Customer");
            System.out.println("2. Delete Customer");
            System.out.println("3. Update Customer");
            System.out.println("4. List Customers");
            System.out.println("5. Search Customer");
            System.out.println("6. Make Order");
            System.out.println("7. Cancel Order");
            System.out.println("8. Generate Invoice");
            System.out.println("9. Send Offer Notification");
            System.out.println("10. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Add Customer
                    System.out.print("Enter Customer ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter Customer Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Visits: ");
                    int visits = scanner.nextInt();
                    Customer newCustomer = new Customer(id, name, visits);
                    employee.addCustomer(newCustomer);  // Correct method call
                    break;

                case 2:
                    // Delete Customer
                    System.out.print("Enter Customer ID to delete: ");
                    int deleteId = scanner.nextInt();
                    employee.deleteCustomer(deleteId);
                    break;

                case 4:
                    // List Customers
                    employee.listCustomers();  // Correct method call
                    break;

                case 6:
                    // Make Order
                    System.out.print("Enter Customer ID for the order: ");
                    int orderCustomerId = scanner.nextInt();
                    Customer customerForOrder = employee.searchCustomer(orderCustomerId);  // Correct method call
                    if (customerForOrder != null) {
                        System.out.print("Enter Order Description: ");
                        scanner.nextLine(); // Consume newline
                        String orderDescription = scanner.nextLine();
                        Order newOrder = new Order(orderDescription);
                        employee.makeOrder(orderDescription);  // Correct method call
                    }
                    break;

                case 7:
                    // Cancel Order
                    System.out.print("Enter Customer ID for canceling the order: ");
                    int cancelCustomerId = scanner.nextInt();
                    Customer customerForCancel = employee.searchCustomer(cancelCustomerId);  // Correct method call
                    if (customerForCancel != null) {
                        System.out.print("Enter Order Description to cancel: ");
                        scanner.nextLine(); // Consume newline
                        String cancelOrderDescription = scanner.nextLine();
                        Order cancelOrder = new Order(cancelOrderDescription);
                        employee.cancelOrder(cancelCustomerId);  // Correct method call
                    }
                    break;

                case 8:
                    // Generate Invoice
                    System.out.print("Enter Customer ID for the invoice: ");
                    int invoiceCustomerId = scanner.nextInt();
                    Customer customerForInvoice = employee.searchCustomer(invoiceCustomerId);  // Correct method call
                    if (customerForInvoice != null) {
                        System.out.print("Enter Invoice Amount: ");
                        double amount = scanner.nextDouble();
                        employee.createBill(invoiceCustomerId, amount);  // Correct method call
                    }
                    break;

                case 9:
                    // Send Offer Notification
                    System.out.print("Enter Offer Details: ");
                    String offerDetails = scanner.nextLine();
                    employee.addOffer(offerDetails);  // Correct method call
                    break;

                case 10:
                    // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void customerMenu() {
        System.out.println("Welcome to the Customer Menu!");
        System.out.println("1. View Meals");
        System.out.println("2. View Offers");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                System.out.println("You chose: View Meals");
                showMeals(); // Ensure this method matches the expected logic
                break;
            case 2:
                System.out.println("You chose: View Offers");
                System.out.println("1. Register an Offer");
                System.out.println("2. Show Your Offers");

                int offerOption = scanner.nextInt();
                switch (offerOption) {
                    case 1:
                        System.out.println("Registering an Offer...");
                        registerOffer(); // Ensure proper handling
                        break;
                    case 2:
                        System.out.println("Showing Your Offers...");
                        showOffers(); // Ensure proper handling
                        break;
                    default:
                        System.out.println("Invalid option! Please try again.");
                }
                break;
            default:
                System.out.println("Invalid option! Please try again.");
        }
    }

    public static void showMeals() {
        // Implement the logic to display meals
        System.out.println("Displaying Meals...");
    }

    public static void registerOffer() {
        // Implement the logic to register offers
        System.out.println("Registering Offer...");
    }

    public static void showOffers() {
        // Implement the logic to display offers
        System.out.println("Displaying Offers...");
    }
    }
