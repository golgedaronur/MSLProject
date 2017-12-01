package model;

import com.msl.MSLMainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LinkButton extends JButton implements ActionListener {
    private Page page;

    public LinkButton(Page page) {
        this.page = page;

        setText(page.getPageDescription());

        if (!page.getMSLMainFrame().isEnable(page)) {
            setEnabled(false);
            setToolTipText("You do not have permission.");
        }

        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        page.getMSLMainFrame().changePage(page);
    }
}
