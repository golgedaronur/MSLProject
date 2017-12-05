package model;

import javax.swing.*;
import java.sql.ResultSet;

public class LinkComponent extends ListObject {
    private Page page;

    public LinkComponent(String titleName, Page page) {
        super(titleName);
        this.page = page;
    }

    public JComponent draw(ResultSet row) {
        return new LinkButton(page);
    }
}
