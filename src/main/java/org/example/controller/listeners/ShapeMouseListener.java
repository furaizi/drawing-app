package org.example.controller.listeners;

import org.example.controller.Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShapeMouseListener extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
        Controller.getInstance().handleMouseClick(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Controller.getInstance().handleMouseRelease(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Controller.getInstance().handleMouseDrag(e);
    }
}
