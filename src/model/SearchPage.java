package model;

import com.msl.MSLMainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class SearchPage extends Page {
    private ArrayList<KeyComponent> searchKeys;
    private JPanel resultPanel;

    public SearchPage(String pageName, User user, MSLMainFrame mainFrame) {
        super(pageName, user, mainFrame);
    }

    @Override
    public void preLoadPage() {
        searchKeys = new ArrayList<KeyComponent>();
        resultPanel = new JPanel();

        loadSearchPage();
    }

    public abstract void loadSearchPage();

    public void loadPage() {
        setLayout(new BorderLayout());

        generateSearchButton();
    }

    private void generateSearchButton() {
        JPanel topPanel = new JPanel(new BorderLayout());

        JButton searchButton = new JButton();
        searchButton.addActionListener(this);
        searchButton.setText("Search");
        topPanel.add(searchButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
    }

    protected void addKeyComponent(KeyComponent keyComponent) {
        searchKeys.add(keyComponent);
    }

    protected void addKeyComponent(String text, String columnName, JComponent component) {
        searchKeys.add(new KeyComponent(text, columnName, component, true, false));
    }

    protected void addKeyComponent(String text, String columnName, JComponent component, boolean isEnable) {
        searchKeys.add(new KeyComponent(text, columnName, component, isEnable, false));
    }

    public abstract ArrayList<ListObject> displayResults(ResultSet row);

    public void actionPerformed(ActionEvent e) {
        JPanel searchPanel = getSearchPanel();
        int option = JOptionPane.showConfirmDialog(getMSLMainFrame(), searchPanel, "Search", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION)
            startSearch();
    }

    private JPanel getSearchPanel() {
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(0, 1));

        for (KeyComponent searchKey : searchKeys)
            searchPanel.add(searchKey.generateComponent());

        return searchPanel;
    }

    private void startSearch() {
        KeyComponent[] array = new KeyComponent[searchKeys.size()];
        ResultSet rs  = search(searchKeys.toArray(array));
        int count = 0;

        try {
            while (rs.next()) {
                count++;
                ArrayList<ListObject> listObjects = displayResults(rs);
                if (count == 1)
                    createResultPanel(listObjects);

                drawListObjects(listObjects, rs, count);
            }

            showResultCount(count);

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(resultPanel, BorderLayout.NORTH);
            add(panel);

            revalidate();
            repaint();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void showResultCount(int count) {
        if (count == 0)
            JOptionPane.showMessageDialog(this, "No result has been found.", "Search completed.", JOptionPane.WARNING_MESSAGE);
        else if (count == 1)
            JOptionPane.showMessageDialog(this, "Only one result has been found.", "Search completed.", JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(this, count + " results have been found.", "Search completed.", JOptionPane.INFORMATION_MESSAGE);
    }

    public abstract ResultSet search(KeyComponent[] searchKeys);

    private void drawListObjects(ArrayList<ListObject> listObjects, ResultSet row, int count) {
        resultPanel.add(new JLabel("" + count));

        for (ListObject listObject : listObjects)
            resultPanel.add(listObject.draw(row));

    }

    private void createResultPanel(ArrayList<ListObject> listObjects) {
        removeAll();
        generateSearchButton();

        int columnSize = listObjects.size();
        resultPanel = new JPanel();
        resultPanel.setBorder(BorderFactory.createEtchedBorder());
        resultPanel.setLayout(new GridLayout(0, columnSize + 1));

        JLabel titleNo = new JLabel("No");
        titleNo.setForeground(Color.RED);
        resultPanel.add(titleNo);

        for (ListObject listObject : listObjects) {
            JLabel title = new JLabel(listObject.getTitleName());
            title.setForeground(Color.RED);
            resultPanel.add(title);
        }
    }

    public String getWhereClause(KeyComponent[] searchKeys) {
        int count = 0;

        String result = "";

        for (KeyComponent searchKey : searchKeys) {
            String clause = searchKey.createWhereClause();

            if (clause.equals(""))
                continue;

            if (count > 0)
                result += " AND ";
            else
                result = " WHERE ";

            result += clause;
        }

        return result;
    }
}
