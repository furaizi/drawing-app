package org.example.view.menu;

import org.example.model.observer.ModelObserver;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;


public class ObjectsMenu extends JMenu implements ModelObserver {

    private final ButtonGroup buttonGroup = new ButtonGroup();
    private final List<JMenuItem> menuItems;

    public ObjectsMenu() {
        setText("Objects");
        List<String> buttonNames = List.of("Point", "Line", "Rectangle", "Ellipse", "LineOO", "Cube");
        this.menuItems = buttonNames.stream()
                                    .map(JRadioButtonMenuItem::new)
                                    .map(this::add)
                                    .peek(buttonGroup::add)
                                    .toList();
    }

    @Override
    public void modelUpdated(String objectName) {
        Arrays.stream(getMenuComponents())
                .map(component -> (JRadioButtonMenuItem) component)
                .filter(button -> button.getText().equals(objectName))
                .findAny()
                .orElseThrow(() -> new RuntimeException(objectName))
                .setSelected(true);
    }

    public void addActionListener(ActionListener listener) {
        menuItems.forEach(menuItem -> menuItem.addActionListener(listener));
    }
}
