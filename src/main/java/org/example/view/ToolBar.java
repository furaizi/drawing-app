package org.example.view;

import org.example.model.observer.ModelObserver;
import org.example.model.shapes.Shape;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToolBar extends JToolBar implements ModelObserver {

    private final ButtonGroup buttonGroup = new ButtonGroup();
    private final List<JToggleButton> buttons = new ArrayList<>();

    public ToolBar() {
        List<String> buttonNames = List.of("Point", "Line", "Rectangle", "Ellipse", "LineOO", "Cube");
        buttonNames.forEach(this::addButton);
    }

    public void addActionListener(ActionListener listener) {
        buttons.forEach(button -> button.addActionListener(listener));
    }

    @Override
    public void modelUpdated(String objectName) {
        Arrays.stream(getComponents())
                .map(component -> (JToggleButton) component)
                .filter(button -> button.getToolTipText().equals(objectName))
                .findAny()
                .orElseThrow()
                .setSelected(true);
    }

    @Override
    public void modelUpdated(List<Shape> shapes) {
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
