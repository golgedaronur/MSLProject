package model;

import com.msl.MSLMainFrame;

import java.util.ArrayList;

public class DisplayPage extends Page {
    private ArrayList<KeyComponent> datas;

    public Page(String pageName, User user, MSLMainFrame mainFrame) {
        super(pageName, user, mainFrame);
    }
}
