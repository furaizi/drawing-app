package org.example.view.menu;

import org.example.view.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class MenuBar extends JMenuBar {

    private final FileMenu fileMenu = new FileMenu();
    private final ObjectsMenu objectsMenu = new ObjectsMenu();
    private final HelpMenu helpMenu = new HelpMenu();
    private final JMenu showTableButton = new JMenu("Table");

    public MenuBar(View view) {
        showTableButton.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                view.showTable();
            }

            @Override
            public void menuDeselected(MenuEvent e) {}

            @Override
            public void menuCanceled(MenuEvent e) {}
        });

        add(fileMenu);
        add(objectsMenu);
        add(helpMenu);
        add(showTableButton);
    }


    public FileMenu getFileMenu() {
        return fileMenu;
    }

    public ObjectsMenu getObjectsMenu() {
        return objectsMenu;
    }

    @Override
    public HelpMenu getHelpMenu() {
        return helpMenu;
    }

    public JMenu getShowTableButton() {
        return showTableButton;
    }

}
