package org.example.controller.states;

import org.example.controller.Controller;
import org.example.model.Model;

import java.awt.event.MouseEvent;

public class CreatingShapeState implements ShapeCreationState {

    private final Model model;

    public CreatingShapeState(Model model) {
        this.model = model;
    }

    @Override
    public void handleMousePress(MouseEvent e) {}

    @Override
    public void handleMouseDrag(MouseEvent e) {
        model.updateCurrentShapeEndpoint(e.getPoint());
    }

    @Override
    public void handleMouseRelease(MouseEvent e) {
        model.setCurrentShapeAsCreated();
        Controller.getInstance().setState(new IdleState(model));
    }
}
