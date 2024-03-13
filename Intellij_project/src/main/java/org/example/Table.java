package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Table {
    public static void main(String[] args) {

        String url = "jdbc:sqlite:D:/project/Intellij_project/src/main/mydb.db";


        String createMenuItemsTableSQL = "CREATE TABLE IF NOT EXISTS MenuItems (\n"
                + "    id INTEGER PRIMARY KEY,\n"
                + "    name TEXT NOT NULL,\n"
                + "    description TEXT,\n"
                + "    price REAL NOT NULL,\n"
                + "    ingredients TEXT\n"
                + ");";


        String createEmployeesTableSQL = "CREATE TABLE IF NOT EXISTS Employees (\n"
                + "    id INTEGER PRIMARY KEY,\n"
                + "    name TEXT NOT NULL,\n"
                + "    role TEXT NOT NULL,\n"
                + "    hourly_wage REAL NOT NULL,\n"
                + "    is_manager INTEGER NOT NULL\n"
                + ");";

        String createReservationsTableSQL = "CREATE TABLE IF NOT EXISTS Reservations (\n"
                + "    id INTEGER PRIMARY KEY,\n"
                + "    customer_name TEXT NOT NULL,\n"
                + "    reservation_date TEXT NOT NULL,\n"
                + "    num_guests INTEGER\n"
                + ");";

            String createOrdersTableSQL = "CREATE TABLE IF NOT EXISTS Orders (\n"
                    + "    id INTEGER PRIMARY KEY,\n"
                    + "    item_name TEXT NOT NULL,\n"
                    + "    quantity INTEGER NOT NULL,\n"
                    + "    FOREIGN KEY (item_name) REFERENCES MenuItems(name)\n"
                    + ");";

            try (Connection connection = DriverManager.getConnection(url);
                 Statement statement = connection.createStatement()) {

           
                statement.execute(createMenuItemsTableSQL);
                System.out.println("MenuItems table created successfully.");

           
                statement.execute(createEmployeesTableSQL);
                System.out.println("Employees table created successfully.");

                statement.execute(createReservationsTableSQL);
                System.out.println("Reservations table created successfully.");

                statement.execute(createOrdersTableSQL);
                System.out.println("Orders table created successfully.");


            } catch (SQLException e) {
                System.out.println("Error creating table: " + e.getMessage());
            }
        }
    }
