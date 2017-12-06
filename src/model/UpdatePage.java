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

    protected void addComponent(KeyComponent keyComponent, Object data) {
        keyComponent.setData(data);
        datas.add(keyComponent);
    }

    protected void addComponent(String text, String columnName, JComponent component, Object data) {
        KeyComponent keyComponent = new KeyComponent(text, columnName, component);
        keyComponent.setData(data);
        datas.add(keyComponent);
    }

    protected void addComponent(String text, String columnName, JComponent component, boolean isEnable, boolean isRequired, Object data) {
        KeyComponent keyComponent = new KeyComponent(text, columnName, component, isEnable, isRequired);
        keyComponent.setData(data);
        datas.add(keyComponent);
    }

    // Update reference and database
    public abstract Object update(KeyComponent[] datas);

    public Object getReference() {
        return reference;
    }
}
