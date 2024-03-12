package org.example.model;

import java.util.*;

public class Order {
    private List<MenuItem> items;
    private String status;

    public Order() {
        this.items = new ArrayList<>();
        this.status = "pending";
    }

    public void addItem(MenuItem item, int quantity) {
        items.add(item);
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity(MenuItem item) {
        return 0;
    }

    public static class OrderItem {
    }
}
