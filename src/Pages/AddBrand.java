package Pages;

import com.msl.MSLMainFrame;
import jdk.nashorn.internal.scripts.JO;
import model.AddPage;
import model.KeyComponent;
import model.User;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class AddBrand extends AddPage {
    private static final String PAGE_NAME = "PAGE_BRAND_ADD";

    public AddBrand(User user, MSLMainFrame mainFrame) {
        super(PAGE_NAME, user, mainFrame);
    }

    public void loadAddPage() {
        AddComponent("Brand name", "BrandName", new JTextField());
    }

    public void Add(KeyComponent[] datas) {
        try {
            Connection conn = getUser().getConnection();
            String brandName = datas[0].getData().toString();
            String sql = "INSERT INTO Brand (BrandName) VALUE ('" + brandName + "')";

            if (conn.createStatement().execute(sql))
                JOptionPane.showMessageDialog(this, brandName + " has been created.");
            else
                JOptionPane.showMessageDialog(this, brandName + " could not be created.", "Error!", JOptionPane.ERROR_MESSAGE);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}
