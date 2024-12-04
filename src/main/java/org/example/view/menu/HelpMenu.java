package org.example.view.menu;

import javax.swing.*;
import java.util.List;

public class HelpMenu extends JMenu {

    public HelpMenu() {
        setText("Help");
        List<String> buttonNames = List.of("Help 1", "Help 2", "Help 3");

        buttonNames.stream()
                    .map(JMenuItem::new)
                    .forEach(this::add);
    }
}
