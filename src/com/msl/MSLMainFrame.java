package com.msl;

import Pages.AddBrand;
import Pages.MainPage;
import Pages.SearchBrand;
import model.Page;
import model.User;
import model.Menu;

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
    private JMenuBar menu;

    public MSLMainFrame(User user, Connection conn) {
        this.user = user;
        this.conn = conn;

        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setMenu();
        changePage(new MainPage(user, this));
    }

    private void setMenu() {
        menu = new JMenuBar();

        JMenuItem home = new JMenuItem("Home");
        home.addActionListener(this);
        menu.add(home);

        JMenu brand = new JMenu("Brand");
        brand.add(new Menu(new AddBrand(user, this)));
        brand.add(new Menu(new SearchBrand(user, this)));

        menu.add(brand);

        setJMenuBar(menu);
    }

    public void changePage(Page page) {
        if (currentPage != null)
            remove(currentPage);

        currentPage = page;
        setTitle(page.getPageDescription());

        if (!isEnable(currentPage))
            add(disabledPage(), BorderLayout.CENTER);
        else
            add(page, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    public boolean isEnable(Page page) {
        try {
            String sql = "SELECT CheckRole('" + user.getUserName() + "', '" + page.getPageName() + "') AS isEnable";
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
        String text = "You do not have permission to enter this page (" + currentPage.getPageName() + ")";
        JLabel label = new JLabel(text);
        label.setForeground(Color.RED);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);

        return panel;
    }

    public User getUser() {
        return user;
    }

    public Connection getConnection() {
        return conn;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void actionPerformed(ActionEvent e) {
        JMenuItem menu = (JMenuItem) e.getSource();

        if (menu.getText().equals("Home"))
            changePage(new MainPage(user, this));
    }
}
