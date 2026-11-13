package com.mycompany.crazydishpizzasystem;

import lk.sjp.pizza.model.Ingredient;
import lk.sjp.pizza.model.User;
import lk.sjp.pizza.util.DatabaseManager;
import java.util.HashMap;

public class CrazyDishPizzaSystem {
    public static void main(String[] args) {
        System.out.println("Testing Database Creation...");
        
        HashMap<String, User> users = DatabaseManager.loadUsers();
        System.out.println("Users loaded: " + users.size());

        HashMap<String, Ingredient> inventory = DatabaseManager.loadInventory();
        System.out.println("Inventory items loaded: " + inventory.size());

        DatabaseManager.logSale("ORD-1001", "Kasun Perera", "BBQ Chicken (Large)", 2150.0, "Cash on Delivery");
        
        System.out.println("SUCCESS! Files created.");
    }
}
