package model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JMenuItem implements ActionListener {
    private Page page;

    public Menu(Page page) {
        this.page = page;

        setText(page.getPageDescription());
        addActionListener(this);

        if (!page.getMSLMainFrame().isEnable(page)) {
            setEnabled(false);
            setToolTipText("You do not have permission.");
        }
    }

    public void actionPerformed(ActionEvent e) {
        page.getMSLMainFrame().changePage(page);
    }
}
