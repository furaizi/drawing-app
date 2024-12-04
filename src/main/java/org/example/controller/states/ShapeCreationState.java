package org.example.controller.states;

import org.example.controller.Controller;

import java.awt.event.MouseEvent;

public interface ShapeCreationState {
    void handleMouseClick(MouseEvent e);
    void handleMouseDrag(MouseEvent e);
    void handleMouseRelease(MouseEvent e);
}
