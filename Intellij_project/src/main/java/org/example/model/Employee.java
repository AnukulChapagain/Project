package org.example.model;

public class Employee {
    private String name;
    private String role;
    private double hourlyWage;
    private boolean isManager;

    public Employee(String name, String role, double hourlyWage, boolean isManager) {
        this.name = name;
        this.role = role;
        this.hourlyWage = hourlyWage;
        this.isManager = isManager;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }
}
