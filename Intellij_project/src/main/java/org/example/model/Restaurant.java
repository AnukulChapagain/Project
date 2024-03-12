package org.example.model;



import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private List<MenuItem> menu;
    private List<Order> orders;
    private List<Reservation> reservations;
    private Inventory inventory;
    private List<Employee> employees;

    public Restaurant() {
        this.menu = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.inventory = new Inventory();
        this.employees = new ArrayList<>();
    }

    public void addMenuItem(MenuItem item) {
        menu.add(item);
    }

    public void updateMenuItem(MenuItem item) {

    }

    public void deleteMenuItem(MenuItem item) {
        menu.remove(item);
    }

    public void placeOrder(Order order) {
        orders.add(order);
    }

    public void updateOrderStatus(Order order, String newStatus) {
        order.updateStatus(newStatus);
    }

    public void makeReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void confirmReservation(Reservation reservation) {
        reservation.setConfirmed(true);
    }

    public void updateStock(String ingredient, int quantity) {
        inventory.updateStock(ingredient, quantity);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

}