package Pages;

import com.msl.MSLMainFrame;
import model.Page;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainPage extends Page {
    private final static String PAGE_NAME = "PAGE_MAIN";

    public MainPage(User user, MSLMainFrame mainFrame) {
        super(PAGE_NAME, user, mainFrame);
    }

    public void loadPage() {
        setLayout(new BorderLayout());
        String welcomeText = "Hello " + getUser().getFullName() + ", \nWelcome to Market Solution program.";

        add(new JLabel(welcomeText), BorderLayout.NORTH);
    }

    public void actionPerformed(ActionEvent e) {
        return;
    }
}
