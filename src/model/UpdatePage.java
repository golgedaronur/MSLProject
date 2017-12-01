package model;

import com.msl.MSLMainFrame;

import java.util.ArrayList;

public abstract class UpdatePage extends Page {
    private ArrayList<KeyComponent> datas;

    public UpdatePage(String pageName, User user, MSLMainFrame mainFrame) {
        super(pageName, user, mainFrame);


    }
}
