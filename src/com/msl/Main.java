package com.msl;

import java.sql.*;

public class Main {
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/msl";
    private static final String CONNECTION_USER = "root";
    private static final String CONNECTION_PASSWORD = "Msl123456";

    public static void main(String[] args) {
        System.out.println("Connecting...");
        Connection conn = Connect();
        if (conn == null) {
            System.err.println("Connection failed.");
            return;
        }

        System.out.println("Connected.");

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM User");
            while (rs.next()) {
                System.out.println("User: " + rs.getString("UserName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static Connection Connect() {
        try {
            return DriverManager.getConnection(CONNECTION_URL, CONNECTION_USER, CONNECTION_PASSWORD);
        } catch (SQLException e) {
            throw new IllegalStateException("Error!", e);
        }
    }
}
