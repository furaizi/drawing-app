package org.example.view;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class ToolBar extends JToolBar {

    private View view;
    private ButtonGroup buttonGroup = new ButtonGroup();

    public ToolBar(View view) {
        this.view = view;
        List<String> buttonNames = List.of("Point", "Line", "Rectangle", "Ellipse", "LineOO", "Cube");
        buttonNames.forEach(this::addButton);
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
        button.addActionListener(view.getController());

        buttonGroup.add(button);
        add(button);
    }

    private String getIconPath(String iconName) {
        return "/" + iconName.toLowerCase() + ".png";
    }
}
