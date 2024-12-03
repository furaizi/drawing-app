package org.example.controller.listeners;

import org.example.controller.Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShapeMouseListener extends MouseAdapter {
    private final Controller controller = Controller.getInstance();

    @Override
    public void mouseClicked(MouseEvent e) {
        controller.handleMouseClick(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        controller.handleMouseRelease(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        controller.handleMouseDrag(e);
    }
}
