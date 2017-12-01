package model;

import com.msl.MSLMainFrame;

import javax.swing.*;
import java.util.ArrayList;

public abstract class SearchPage extends Page {
    private ArrayList<KeyComponent> searchKeys;
    private JPanel searchPanel;
    private JPanel resultPanel;

    public SearchPage(String pageName, User user, MSLMainFrame mainFrame) {
        super(pageName, user, mainFrame);
    }

    @Override
    public void preLoadPage() {
        searchKeys = new ArrayList<KeyComponent>();
        searchPanel = new JPanel();
        resultPanel = new JPanel();
        loadSearchPage();
    }

    public abstract void loadSearchPage();



    protected void AddComponent(KeyComponent keyComponent) {
        searchKeys.add(keyComponent);
    }

    protected void AddComponent(String text, String columnName, JComponent component) {
        searchKeys.add(new KeyComponent(text, columnName, component));
    }

    protected void AddComponent(String text, String columnName, JComponent component, boolean isEnable, boolean isRequired) {
        searchKeys.add(new KeyComponent(text, columnName, component, isEnable, isRequired));
    }
}
