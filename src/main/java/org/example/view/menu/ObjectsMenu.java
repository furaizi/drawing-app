package org.example.view.menu;

import org.example.controller.Controller;
import org.example.view.View;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;


public class ObjectsMenu extends JMenu {

    private View view;
    private ButtonGroup buttonGroup = new ButtonGroup();

    public ObjectsMenu(View view) {
        this.view = view;
        setText("Objects");
        List<String> buttonNames = List.of("Point", "Line", "Rectangle", "Ellipse", "LineOO", "Cube");
        buttonNames.forEach(this::addRadioButtonMenuItem);
    }

    public void update(String objectName) {
        Arrays.stream(getMenuComponents())
                .map(component -> (JRadioButtonMenuItem) component)
                .filter(button -> button.getText().equals(objectName))
                .findAny()
                .orElseThrow(() -> new RuntimeException(objectName))
                .setSelected(true);
    }

    private void addRadioButtonMenuItem(String name) {
        var menuItem = new JRadioButtonMenuItem(name);
        buttonGroup.add(menuItem);
        add(menuItem);
        menuItem.addActionListener(Controller.getInstance());
    }

}
