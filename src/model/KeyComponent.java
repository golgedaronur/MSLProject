package model;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class KeyComponent {
    private JComponent component;
    private String columnName;
    private String text;
    private boolean isEnable;
    private boolean isRequired;

    public KeyComponent(String text, String columnName, JComponent component) {
        this.text = text;
        this.columnName = columnName;
        this.component = component;
        isEnable = true;
        isRequired = true;
    }

    public KeyComponent(String text, String columnName, JComponent component, boolean isEnable, boolean isRequired) {
        this.text = text;
        this.columnName = columnName;
        this.component = component;
        this.isEnable = isEnable;
        this.isRequired = isRequired;
    }

    public JPanel generateComponent() {
        JPanel panel = new JPanel();

        JLabel textLabel = new JLabel();
        if (isRequired)
            textLabel.setText("<html>" + text + ": <font color=red>*</font></html>");
        else
            textLabel.setText(text + ": ");

        component.setEnabled(isEnable);

        panel.setLayout(new GridLayout(1, 2));
        panel.add(textLabel);
        panel.add(component);

        return panel;
    }

    public JComponent getComponent() {
        return component;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getText() {
        return text;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public Object getData() {
        if (component instanceof JTextComponent)
            return ((JTextComponent) component).getText();
        else if (component instanceof JToggleButton)
            return ((JToggleButton) component).isSelected();
        else if (component instanceof JComboBox)
            return ((JComboBox) component).getSelectedItem();

        return null;
    }

    public String createWhereClause() {
        String data = getData().toString();

        if (data.equals(""))
            return "";
        else if (data.equals("-"))
            return columnName + " = ''";
        else if (data.indexOf('%') != -1)
            return columnName + " LIKE '" + data + "'";
        else
            return columnName + " = '" + data + "'";
    }
}
