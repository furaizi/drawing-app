package org.example.view;

import org.example.controller.Controller;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ToolBar extends JToolBar {

    private final ButtonGroup buttonGroup = new ButtonGroup();
    private final List<JToggleButton> buttons = new ArrayList<>();

    public ToolBar() {
        List<String> buttonNames = List.of("Point", "Line", "Rectangle", "Ellipse", "LineOO", "Cube");
        buttonNames.forEach(this::addButton);
    }

    public void addActionListener(ActionListener listener) {
        buttons.forEach(button -> button.addActionListener(listener));
    }

    public void update(String objectName) {
        Arrays.stream(getComponents())
                .map(component -> (JToggleButton) component)
                .filter(button -> button.getToolTipText().equals(objectName))
                .findAny()
                .orElseThrow()
                .setSelected(true);
    }

    private void addButton(String name) {
        var iconUrl = getClass().getResource(getIconPath(name));
        if (iconUrl == null) {
            throw new IllegalArgumentException("Icon not found: " + getIconPath(name));
        }
        var icon = new ImageIcon(iconUrl);
        var button = new JToggleButton(icon);

        button.setToolTipText(name);
        button.setFocusPainted(false);

        buttonGroup.add(button);
        buttons.add(button);
        add(button);
    }

    private String getIconPath(String iconName) {
        return "/" + iconName.toLowerCase() + ".png";
    }
}
