package model;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Brand {
    private int brandID;
    private String brandName;

    public Brand(ResultSet row) {
        try {
            this.brandID = row.getInt("BrandID");
            this.brandName = row.getString("BrandName");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public int getBrandID() {
        return brandID;
    }

    public String getBrandName() {
        return brandName;
    }
}
