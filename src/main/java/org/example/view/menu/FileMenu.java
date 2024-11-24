package org.example.view.menu;

import javax.swing.*;
import java.util.List;

public class FileMenu extends JMenu {

    public FileMenu() {
        setText("File");
        List<String> buttonNames = List.of("New", "Open", "Save As", "Print", "Exit");
        buttonNames.forEach(name -> add(new JMenuItem(name)));
    }
}
