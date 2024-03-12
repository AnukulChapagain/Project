package org.example.model;

import java.util.*;

public class Inventory {
    private Map<String, Integer> stockLevels;

    public Inventory() {
        this.stockLevels = new HashMap<>();
    }

    public Inventory(String ingredient, int quantity) {
    }

    public void updateStock(String ingredient, int quantity) {
        stockLevels.put(ingredient, quantity);
    }

    public boolean isLowStock(String ingredient) {
        return stockLevels.getOrDefault(ingredient, 0) < 10; // Example threshold
    }
    public Map<String, Integer> getStockLevels() {
        return stockLevels;
    }

    public void setStockLevels(Map<String, Integer> stockLevels) {
        this.stockLevels = stockLevels;
    }

    public Object getIngredient() {
        return null;
    }

    public String getQuantity() {
        return null;
    }
}
