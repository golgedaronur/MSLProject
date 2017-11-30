package com.msl;

import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MSLLogin extends JFrame implements ActionListener {
    private Connection conn;
    private JTextArea userName;
    private JPasswordField password;

    public User user;

    public MSLLogin(Connection conn) {
        this.conn = conn;

        user = null;
        userName = new JTextArea();
        password = new JPasswordField();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 480);
        draw();
        setVisible(true);
    }

    private void draw() {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(new JLabel("Username: "));
        panel.add(userName);
        panel.add(new JLabel("Password: "));
        panel.add(password);
        panel.add(new JLabel());

        JButton button = new JButton();
        button.setText("Login");
        button.addActionListener(this);
        panel.add(button);

        add(panel, BorderLayout.NORTH);
    }

    public void actionPerformed(ActionEvent e) {
        String userName = this.userName.getText().trim();
        String password = toMD5(this.password.getText());
        String sql = "SELECT * FROM User WHERE UserName = '" + userName + "' AND Password = '" + password + "'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                user = new User(userName, password, conn);
                MSLMainFrame mainFrame = new MSLMainFrame(user, conn);
                setVisible(false);
                mainFrame.setVisible(true);

            } else
                JOptionPane.showMessageDialog(this, "User cannot be found.\nWrong Username or Password");

            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

    }

    private String toMD5(String md5) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < array.length; i++)
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        return null;
    }

}
