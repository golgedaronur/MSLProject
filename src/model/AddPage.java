package model;

import com.msl.MSLMainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public abstract class AddPage extends Page {
    private ArrayList<KeyComponent> datas;

    public AddPage(String pageName, User user, MSLMainFrame mainFrame) {
        super(pageName, user, mainFrame);
    }

    @Override
    public void preLoadPage() {
        datas = new ArrayList<KeyComponent>();
        loadAddPage();
    }

    protected abstract void loadAddPage();

    protected abstract void Add(KeyComponent[] datas);

    protected void AddComponent(KeyComponent keyComponent) {
        datas.add(keyComponent);
    }

    protected void AddComponent(String text, String columnName, JComponent component) {
        datas.add(new KeyComponent(text, columnName, component));
    }

    protected void AddComponent(String text, String columnName, JComponent component, boolean isEnable, boolean isRequired) {
        datas.add(new KeyComponent(text, columnName, component, isEnable, isRequired));
    }

    public void loadPage() {
        if (datas == null)
            return;

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(0,1));

        for (KeyComponent i : datas)
            topPanel.add(i.generateComponent());


        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        JButton addButton = new JButton();
        addButton.setText("Add");
        addButton.addActionListener(this);

        bottomPanel.add(addButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        KeyComponent list[] = new KeyComponent[datas.size()];
        Add(datas.toArray(list));
    }
}
