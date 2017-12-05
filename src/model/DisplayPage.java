package model;

import com.msl.MSLMainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class DisplayPage extends Page {
    private ArrayList<KeyComponent> datas;
    private ArrayList<JButton> buttons;
    private Object reference;

    public DisplayPage(String pageName, Object reference, User user, MSLMainFrame mainFrame) {
        super(pageName, user, mainFrame);
        this.reference = reference;

        loadDisplayPage();
        createTopPanel();
        createBottomPanel();
    }

    public void loadPage() {
        datas = new ArrayList<KeyComponent>();
        buttons = new ArrayList<JButton>();
        setLayout(new BorderLayout());
    }

    public abstract void loadDisplayPage();

    protected void addKeyComponent(KeyComponent keyComponent, Object data) {
        keyComponent.setData(data);
        datas.add(keyComponent);
    }

    protected void addKeyComponent(String text, String columnName, JComponent component, Object data) {
        KeyComponent keyComponent = new KeyComponent(text, columnName, component, false, false);
        keyComponent.setData(data);
        datas.add(keyComponent);
    }

    protected void addButton(JButton button) {
        buttons.add(button);
    }

    private void createTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createEtchedBorder());
        topPanel.setLayout(new GridLayout(0,1));

        for (KeyComponent data : datas)
            topPanel.add(data.generateComponent());

        add(topPanel, BorderLayout.NORTH);
    }

    private void createBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEtchedBorder());
        bottomPanel.setLayout(new GridLayout(1, buttons.size()));

        for (JButton button : buttons)
            bottomPanel.add(button);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public Object getReference() {
        return reference;
    }
}
