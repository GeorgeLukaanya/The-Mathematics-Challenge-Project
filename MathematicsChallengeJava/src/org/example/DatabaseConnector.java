package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class DatabaseConnector {
        private static final String DB_URL = "jdbc:mysql://localhost:3306/math-challengez";
        private static final String USER = "root"; 
        private static final String PASS = "";     

        public static Connection getConnection() throws SQLException, ClassNotFoundException {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            return DriverManager.getConnection(DB_URL, USER, PASS);
        }

        public static void closeConnection(Connection conn) {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Closing connection failed: " + e.getMessage());
                }
            }
        }
    }


