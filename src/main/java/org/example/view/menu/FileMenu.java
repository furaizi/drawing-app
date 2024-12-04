package org.example.view.menu;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

public class FileMenu extends JMenu {

    private final List<JMenuItem> menuItems;

    public FileMenu() {
        setText("File");
        List<String> buttonNames = List.of("New", "Open", "Save As", "Print", "Exit");
        this.menuItems = buttonNames.stream()
                                .map(JMenuItem::new)
                                .map(this::add)
                                .toList();
    }

    public void addActionListener(ActionListener listener) {
        menuItems.forEach(menuItem -> menuItem.addActionListener(listener));
    }
}
