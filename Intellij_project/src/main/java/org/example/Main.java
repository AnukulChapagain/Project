package org.example;

import org.example.model.Reservation;
import org.example.model.Inventory;
import org.example.model.Order;
import org.example.model.MenuItem;
import org.example.model.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner sc;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String url = "jdbc:sqlite:D:/project/Intellij_project/src/main/mydb.db";
        MenuItem menuItem = createMenuItem(sc);
        insertMenuItem(menuItem, url);
        Order o = createOrder(sc);
        insertOrder(o, url);
        Reservation r = createReservation(sc);
        insertReservation(r, url);
        Inventory i = updateInventory(sc);
        Employee e = clone(sc);
        insertEmployee(e, url);
        System.out.println("\nMenu Item: " + menuItem.getName());
        System.out.println("Order Items: " + o.getItems());
        System.out.println("Reservation Details: " + r.getCustomerName() + " for " + r.getNumGuests() + " guests on " + r.getDate());
        System.out.println(new StringBuilder().append("Inventory Update: ").append(i.getIngredient()).append(" quantity updated to ").append(i.getQuantity()).toString());
        System.out.println("Employee: " + e.getName() + ", Role: " + e.getRole());

        sc.close();
    }

    private static Employee clone(Scanner sc) {
        return null;
    }


    private static MenuItem createMenuItem(Scanner sc) {
        Main.sc = sc;
        System.out.println("Enter menu item details:");
        System.out.print("Item Name: ");
        String itemName = sc.nextLine();
        System.out.print("Description: ");
        String itemDescription = sc.nextLine();
        System.out.print("Price: ");
        double itemPrice = sc.nextDouble();
        sc.nextLine(); // Consume newline character
        System.out.print("Ingredients: ");
        String ingredientInput = sc.nextLine();
        List<String> ingredients = new ArrayList<>();
        for (String ingredient : ingredientInput.split(",")) {
            ingredients.add(ingredient.trim());
        }
        return new MenuItem(itemName, itemDescription, itemPrice, ingredients);
    }


    private static Inventory updateInventory(Scanner sc) {
        System.out.println("Enter inventory update details:");
        System.out.print("Ingredient: ");
        String ingredient = sc.nextLine();
        System.out.print("Quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine(); // Consume newline character
        return new Inventory(ingredient, quantity);
    }

    private static Reservation createReservation(Scanner sc) {
        System.out.println("Enter reservation details:");
        System.out.print("Customer name: ");
        String customerName = sc.nextLine();
        System.out.print("Date (yyyy-MM-dd/HH:mm:ss): ");
        String dateStr = sc.nextLine();
        Date date = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = formatter.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter date in the format yyyy-MM-dd HH:mm:ss");
            System.exit(1);
        }
        System.out.print("Number of guests: ");
        int numGuests = sc.nextInt();
        sc.nextLine(); // Consume newline character
        return new Reservation(customerName, date, numGuests);
    }

    private static Order createOrder(Scanner scanner) {
        Order order = new Order();
        System.out.println("Enter order details:");
        System.out.print("Enter number of items in the order: ");
        int numItems = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        for (int i = 0; i < Math.min(numItems, 5); i++) {
            System.out.print("Enter item " + (i + 1) + " name: ");
            String itemNameInput = scanner.nextLine();
            System.out.print("Enter quantity for item " + (i + 1) + ": ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            MenuItem item = new MenuItem(itemNameInput, "", 0, new ArrayList<>());
            order.addItem(item, quantity);
        }
        return order;
    }
    private static Employee createEmployee(Scanner sc) {
        System.out.println("\t\t\t\t\t\t\t\tEnter employee details:");
        System.out.print("\t\t\t\t\t\t\t\t\t\tName: ");
        String employeeName = sc.nextLine();
        System.out.print("\t\t\t\t\t\t\t\t\t\tRole: ");
        String role = sc.nextLine();
        System.out.print("\t\t\t\t\t\t\t\t\t\tHourly wage given to employee: ");
        double hourlyWage = sc.nextDouble();
        sc.nextLine(); // Consume newline character
        System.out.print("\t\t\t\t\t\t\t\t\t\tIs manager? (true/false): ");
        boolean isManager = sc.nextBoolean();
        sc.nextLine(); // Consume newline character
        return new Employee(employeeName, role, hourlyWage, isManager);
    }


    private static void insertMenuItem(MenuItem menuItem, String url) {
        String sql = "INSERT INTO MenuItems (name, description, price, ingredients) VALUES (?, ?, ?, ?)";
        try (
                Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, menuItem.getName());
            statement.setString(2, menuItem.getDescription());
            statement.setDouble(3, menuItem.getPrice());
            statement.setString(4, String.join(", ", menuItem.getIngredients()));
            statement.executeUpdate();
            System.out.println("Menu item inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting menu item: " + e.getMessage());
        }
    }

    private static void insertOrder(Order order, String url) {
        String sql = "INSERT INTO Orders (item_name, quantity) VALUES (?, ?)";
        try (
                Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            for (MenuItem item : order.getItems()) {
                statement.setString(1, item.getName());
                statement.setInt(2, order.getQuantity(item));
                statement.executeUpdate();
            }
            System.out.println("Order inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting order: " + e.getMessage());
        }
    }

    private static void insertReservation(Reservation reservation, String url) {
        String sql = "INSERT INTO Reservations (customer_name, date, num_guests) VALUES (?, ?, ?)";
        try (
                Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = formatter.format(reservation.getDate());
            statement.setString(1, reservation.getCustomerName());
            statement.setString(2, formattedDate);
            statement.setInt(3, reservation.getNumGuests());
            statement.executeUpdate();
            System.out.println("Reservation inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting reservation: " + e.getMessage());
        }
    }

    private static void insertEmployee(Employee employee, String url) {
        String sql = "INSERT INTO Employees (name, role, hourly_wage, is_manager) VALUES (?, ?, ?, ?)";
        try (
                Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getRole());
            statement.setDouble(3, employee.getHourlyWage());
            statement.setBoolean(4, employee.isManager());
            statement.executeUpdate();
            System.out.println("Employee inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting employee: " + e.getMessage());
        }
    }
}
