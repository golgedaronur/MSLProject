package model;

import javax.swing.*;
import java.sql.ResultSet;

public abstract class ListObject {
    private String titleName;

    public ListObject(String titleName) {
        this.titleName = titleName;
    }

    abstract JComponent draw(ResultSet row);

    public String getTitleName() {
        return titleName;
    }
}
