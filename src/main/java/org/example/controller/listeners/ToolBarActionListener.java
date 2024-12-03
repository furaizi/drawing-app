package org.example.controller.listeners;

import org.example.controller.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBarActionListener implements ActionListener {

    private final Controller controller = Controller.getInstance();

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.handleToolBarAction(e);
    }
}
