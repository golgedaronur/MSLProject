package model;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplayComponent extends ListObject {
    private String columnName;

    public DisplayComponent(String titleName, String columnName) {
        super(titleName);
        this.columnName = columnName;
    }

    public JComponent draw(ResultSet row) {
        try {
            return new JLabel(row.getString(columnName));
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return null;
    }
}
