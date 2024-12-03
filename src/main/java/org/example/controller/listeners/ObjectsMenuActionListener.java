package org.example.controller.listeners;

import org.example.controller.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ObjectsMenuActionListener implements ActionListener {

    private Controller controller = Controller.getInstance();

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.handleObjectsMenuAction(e);
    }
}
