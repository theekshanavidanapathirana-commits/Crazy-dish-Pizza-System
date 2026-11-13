package lk.sjp.pizza.model;

public class Ingredient {
    private String name;
    private double price;
    private int stockQty;

    public Ingredient(String name, double price, int stockQty) {
        this.name = name;
        this.price = price;
        this.stockQty = stockQty;
    }

    public String getName() { 
        return name; 
    }
    public double getPrice() { 
        return price;
    }
    public int getStockQty() { 
        return stockQty; 
    }
    
    public void setStockQty(int stockQty) { 
        if (stockQty >= 0) {
            this.stockQty = stockQty; 
        }
    }
}
