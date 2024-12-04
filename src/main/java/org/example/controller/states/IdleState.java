package org.example.controller.states;

import org.example.controller.Controller;
import org.example.model.Model;

import java.awt.event.MouseEvent;

public class IdleState implements ShapeCreationState {

    private final Model model;

    public IdleState(Model model) {
        this.model = model;
    }

    @Override
    public void handleMousePress(MouseEvent e) {
        model.createShape(e.getPoint());
        Controller.getInstance().setState(new CreatingShapeState(model));
    }

    @Override
    public void handleMouseDrag(MouseEvent e) {}

    @Override
    public void handleMouseRelease(MouseEvent e) {}
}
