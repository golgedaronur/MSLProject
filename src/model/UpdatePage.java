package model;

import com.msl.MSLMainFrame;

import javax.swing.*;
import java.util.ArrayList;

public abstract class UpdatePage extends Page {
    private ArrayList<KeyComponent> datas;
    private ArrayList<JButton> buttons;
    private Object reference;

    public UpdatePage(String pageName, Object reference, User user, MSLMainFrame mainFrame) {
        super(pageName, user, mainFrame);
        this.reference = reference;

        loadUpdatePage();

    }

    public abstract void loadUpdatePage();
}
