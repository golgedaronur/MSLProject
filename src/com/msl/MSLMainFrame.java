package com.msl;

import Pages.MainPage;
import model.Page;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MSLMainFrame extends JFrame implements ActionListener {
    private User user;
    private Connection conn;
    private Page currentPage;

    public MSLMainFrame(User user, Connection conn) {
        this.user = user;
        this.conn = conn;

        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        changePage(new MainPage(user, this));
    }


    public void changePage(Page page) {
        currentPage = page;

        if (!isEnable())
            add(disabledPage(), BorderLayout.CENTER);
        else
            add(page, BorderLayout.CENTER);

    }

    private boolean isEnable() {
        try {
            String sql = "SELECT CheckRole('" + user.getUserName() + "', '" + currentPage.getPageName() + "') AS isEnable";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();

            return rs.getBoolean(1);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        return false;
    }

    private JPanel disabledPage() {
        String text = "You do not have to enter this page (" + currentPage.getPageName() + ")";
        JLabel label = new JLabel(text);
        label.setForeground(Color.RED);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);

        return panel;
    }

    public void actionPerformed(ActionEvent e) {
        return;
    }
}
