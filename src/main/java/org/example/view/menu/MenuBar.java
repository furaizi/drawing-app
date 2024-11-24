package org.example.view.menu;

import org.example.view.View;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    private View view;
    private ObjectsMenu objectsMenu;

    public MenuBar(View view) {
        this.view = view;
        this.objectsMenu = new ObjectsMenu(view);

        add(new FileMenu());
        add(objectsMenu);
        add(new HelpMenu());
    }

    public void update(String objectName) {
        objectsMenu.update(objectName);
    }

}
