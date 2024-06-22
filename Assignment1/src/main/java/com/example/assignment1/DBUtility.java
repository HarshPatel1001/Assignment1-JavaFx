package com.example.assignment1;

// Import necessary classes for SQL operations
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// Define the DBUtility class
public class DBUtility {
    // Constants for database connection URL, user, and password
    private static final String URL = "jdbc:mysql://localhost:3307/IPL";
    private static final String USER = "root";
    private static final String PASSWORD = "root123";

    // Method to fetch IPL data from the database
    public static ResultSet fetchIPLData() {
        try {
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // Create a statement object to execute the query
            Statement stmt = conn.createStatement();

            // Define the SQL query to fetch all records from the IPL_Team_Performance table
            String query = "SELECT * FROM IPL_Team_Performance";

            // Execute the query and return the result set
            return stmt.executeQuery(query);
        } catch (Exception e) {
            // Print stack trace if there is an exception during the database operations
            e.printStackTrace();
            return null;
        }
    }
}
