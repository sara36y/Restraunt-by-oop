package com.mycompany.restaurant;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class DataHandler {

    public static void appendDataToFile(String filePath, List<String> data) {
        try {
            // Ensure the file exists
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                Files.createFile(path);
            }

            // Open the file and append data
            try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                for (String line : data) {
                    writer.write(line);
                    writer.newLine();  // Add a new line after each entry
                }
            }

            System.out.println("Data successfully appended to file: " + filePath);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filePath);
            e.printStackTrace();
        }
    }


    // مسح البيانات من الملف
    public static void clearDataInFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // كتابة بيانات فارغة لتفريغ الملف
            writer.write("");
        } catch (IOException e) {
            System.out.println("Error clearing file data: " + e.getMessage());
        }
    }

    // حذف بيانات معينة من الملف بناءً على شرط معين (مثل اسم المستخدم أو أي قيمة)
    public static void removeDataFromFile(String fileName, String dataToRemove) {
        List<String> data = readDataFromFile(fileName);
        List<String> updatedData = new ArrayList<>();

        for (String line : data) {
            if (!line.contains(dataToRemove)) {  // تأكد أن السطر لا يحتوي على البيانات المطلوب حذفها
                updatedData.add(line);
            }
        }

        // إعادة كتابة البيانات المعدلة في الملف
        writeDataToFile(fileName, updatedData);
    }

    // تعديل بيانات موجودة في الملف
    public static void updateDataInFile(String fileName, String oldData, String newData) {
        // قراءة البيانات من الملف
        List<String> data = readDataFromFile(fileName);
        List<String> updatedData = new ArrayList<>();
    
        // التعديل على البيانات
        for (String line : data) {
            if (line.contains(oldData)) {
                updatedData.add(line.replace(oldData, newData));  // استبدال البيانات القديمة بالجديدة
            } else {
                updatedData.add(line);
            }
        }
    
        // إعادة كتابة البيانات المعدلة في الملف
        writeDataToFile(fileName, updatedData);
    }
    
    public static List<String> readDataFromFile(String filePath) {
        List<String> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
            return null;
        }
        return data;
    }

    // دالة لكتابة البيانات إلى الملف
    public static boolean writeDataToFile(String filePath, List<String> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : data) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return false;
        }
        return true;
    }
}
