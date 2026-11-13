package lk.sjp.pizza.util;

import lk.sjp.pizza.model.Ingredient;
import lk.sjp.pizza.model.User;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class DatabaseManager {
    private static final String USERS_DB = "users.txt";
    private static final String INVENTORY_DB = "inventory.txt";
    private static final String SALES_DB = "sales_report.txt";

    // --- 1. USER DATABASE OPERATIONS ---
    public static HashMap<String, User> loadUsers() {
        HashMap<String, User> users = new HashMap<>();
        File file = new File(USERS_DB);

        if (!file.exists()) {
            // Default Admin & Customer accounts
            users.put("admin", new User("admin", "admin123", "Store Manager", "0770000000", "ADMIN"));
            users.put("kasun", new User("kasun", "1234", "Kasun Perera", "0712345678", "CUSTOMER"));
            saveUsers(users);
            return users;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    users.put(parts[0], new User(parts[0], parts[1], parts[2], parts[3], parts[4]));
                }
            }
        } catch (IOException e) {
            System.err.println("User DB Error: " + e.getMessage());
        }
        return users;
    }

    public static void saveUsers(HashMap<String, User> users) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(USERS_DB, false))) {
            for (User u : users.values()) {
                pw.println(u.getUsername() + "," + u.getPassword() + "," + u.getFullName() + "," + u.getPhone() + "," + u.getRole());
            }
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }

    // --- 2. INVENTORY DATABASE OPERATIONS ---
    public static HashMap<String, Ingredient> loadInventory() {
        HashMap<String, Ingredient> inventory = new HashMap<>();
        File file = new File(INVENTORY_DB);

        if (!file.exists()) {
            inventory.put("Extra Mozzarella", new Ingredient("Extra Mozzarella", 180.0, 15));
            inventory.put("Roast Chicken", new Ingredient("Roast Chicken", 250.0, 4));
            inventory.put("Button Mushrooms", new Ingredient("Button Mushrooms", 140.0, 12));
            inventory.put("Black Olives", new Ingredient("Black Olives", 160.0, 3));
            inventory.put("Jalapenos", new Ingredient("Jalapenos", 110.0, 20));
            saveInventory(inventory);
            return inventory;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    inventory.put(parts[0], new Ingredient(parts[0], Double.parseDouble(parts[1]), Integer.parseInt(parts[2])));
                }
            }
        } catch (IOException e) {
            System.err.println("Inventory DB Error: " + e.getMessage());
        }
        return inventory;
    }

    public static void saveInventory(HashMap<String, Ingredient> inventory) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(INVENTORY_DB, false))) {
            for (Ingredient ing : inventory.values()) {
                pw.println(ing.getName() + "," + ing.getPrice() + "," + ing.getStockQty());
            }
        } catch (IOException e) {
            System.err.println("Error saving inventory: " + e.getMessage());
        }
    }

    // --- 3. SALES LOG DATABASE OPERATIONS ---
    public static void logSale(String orderId, String customerName, String itemsSummary, double total, String paymentInfo) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        try (PrintWriter pw = new PrintWriter(new FileWriter(SALES_DB, true))) {
            pw.println("[" + time + "] OrderID: " + orderId + " | Customer: " + customerName + 
                       " | Items: " + itemsSummary + " | Total: LKR " + total + " | " + paymentInfo);
        } catch (IOException e) {
            System.err.println("Sales DB Error: " + e.getMessage());
        }
    }
}

