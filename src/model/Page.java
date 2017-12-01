package model;

import com.msl.MSLMainFrame;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Page extends JPanel implements ActionListener {
    private String pageName;
    private User user;
    private MSLMainFrame mainFrame;

    public Page(String pageName, User user, MSLMainFrame mainFrame) {
        this.pageName = pageName;
        this.user = user;
        this.mainFrame = mainFrame;

        preLoadPage();
        loadPage();
    }

    // Create components for panel.
    protected abstract void loadPage();

    public void preLoadPage() {
        return;
    }

    public User getUser() {
        return user;
    }

    public MSLMainFrame getMSLMainFrame() {
        return mainFrame;
    }

    public String getPageName() {
        return pageName;
    }

    public String getPageDescription() {
        try {
            String sql = "SELECT * FROM Pages WHERE PageName = '" + pageName + "'";
            ResultSet rs = user.getConnection().createStatement().executeQuery(sql);
            rs.next();

            return rs.getString("PageDescription");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        return null;
    }
}
