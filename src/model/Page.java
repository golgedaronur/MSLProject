package model;

import com.msl.MSLMainFrame;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class Page extends JPanel implements ActionListener {
    private String pageName;
    private User user;
    private MSLMainFrame mainFrame;

    public Page(String pageName, User user, MSLMainFrame mainFrame) {
        this.pageName = pageName;
        this.user = user;
        this.mainFrame = mainFrame;

        loadPage();
    }

    // Create components for panel.
    public abstract void loadPage();

    public User getUser() {
        return user;
    }

    public MSLMainFrame getMSLMainFrame() {
        return mainFrame;
    }

    public String getPageName() {
        return pageName;
    }
}
