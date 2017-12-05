package Pages;

import com.msl.MSLMainFrame;
import model.Brand;
import model.DisplayPage;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DisplayBrand extends DisplayPage {
    private final static String PAGE_NAME = "PAGE_BRAND_DISPLAY";

    public DisplayBrand(Brand brand, User user, MSLMainFrame mainFrame) {
        super(PAGE_NAME, brand, user, mainFrame);
    }

    public void loadDisplayPage() {
        Brand brand = (Brand) getReference();
        addKeyComponent("Brand ID", "BrandID", new JLabel(), brand.getBrandID());
        addKeyComponent("Brand Name", "BrandName", new JLabel(), brand.getBrandName());
    }

    public void actionPerformed(ActionEvent e) {
        return;
    }
}
