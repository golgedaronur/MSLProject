package Pages;

import com.msl.MSLMainFrame;
import model.*;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchBrand extends SearchPage {
    private static final String PAGE_NAME = "PAGE_BRAND_SEARCH";

    public SearchBrand(User user, MSLMainFrame mainFrame) {
        super(PAGE_NAME, user, mainFrame);
    }

    public void loadSearchPage() {
        addKeyComponent("Brand ID", "BrandID", new JTextField());
        addKeyComponent("Brand Name", "BrandName", new JTextField());
    }

    public ArrayList<ListObject> displayResults(ResultSet row) {
        ArrayList<ListObject> listObjects = new ArrayList<ListObject>();

        listObjects.add(new DisplayComponent("Brand ID", "BrandID"));
        listObjects.add(new DisplayComponent("Brand Name", "BrandName"));
        listObjects.add(new LinkComponent("Display", new DisplayBrand(new Brand(row), getUser(), getMSLMainFrame())));

        return listObjects;
    }

    public ResultSet search(KeyComponent[] searchKeys) {
        String sql = "SELECT * FROM Brand" + getWhereClause(searchKeys) + " ORDER BY BrandName ASC";
        try {
            return getUser().getConnection().createStatement().executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        return null;
    }
}
