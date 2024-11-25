package org.example.view.menu;

import org.example.view.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class MenuBar extends JMenuBar {

    private View view;
    private ObjectsMenu objectsMenu;

    public MenuBar(View view) {
        this.view = view;
        this.objectsMenu = new ObjectsMenu(view);
        var showTableButton = new JMenu("Table");
        showTableButton.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                view.showTable();
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });

        add(new FileMenu());
        add(objectsMenu);
        add(new HelpMenu());
        add(showTableButton);
    }

    public void update(String objectName) {
        objectsMenu.update(objectName);
    }

}
