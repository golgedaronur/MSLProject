package model;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    private String userName;
    private String password;
    private Connection conn;

    public User(String userName, String password, Connection conn) {
        this.userName = userName;
        this.password = password;
        this.conn = conn;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Connection getConnection() {
        return conn;
    }

    public String getUserTitle() {
        try {
            String sql = "SELECT TitleName FROM User WHERE UserName = '" + userName + "' AND Password = '" + password + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next())
                return rs.getString(1);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }

    public String getFullName() {
        return getName() + " " + getSurname();
    }

    public String getName() {
        try {
            String sql = "SELECT Name FROM User WHERE UserName = '" + userName + "' AND Password = '" + password + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next())
                return rs.getString(1);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }

    public String getSurname() {
        try {
            String sql = "SELECT Surname FROM User WHERE UserName = '" + userName + "' AND Password = '" + password + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next())
                return rs.getString(1);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }

    public String getEmail() {
        try {
            String sql = "SELECT UserEmail FROM User WHERE UserName = '" + userName + "' AND Password = '" + password + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next())
                return rs.getString(1);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }
}
