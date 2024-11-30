package org.example.view.menu;

import org.example.controller.Controller;

import javax.swing.*;
import java.util.List;

public class FileMenu extends JMenu {

    public FileMenu() {
        setText("File");
        List<String> buttonNames = List.of("New", "Open", "Save As", "Print", "Exit");
        buttonNames.stream()
                .map(JMenuItem::new)
                .map(this::add)
                .forEach(item -> item.addActionListener(Controller.getInstance()));
    }
}
