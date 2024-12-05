package org.example.view;

import org.example.controller.Controller;
import org.example.controller.listeners.ShapeMouseListener;
import org.example.model.observer.ModelObserver;
import org.example.model.shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Panel extends JPanel {


    public Panel() {
        super(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        getShapes().forEach(shape -> shape.paintComponent(g));
    }

    private List<Shape> getShapes() {
        return Controller.getInstance().getShapes();
    }
}
