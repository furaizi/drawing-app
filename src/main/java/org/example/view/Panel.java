package org.example.view;

import org.example.model.shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Panel extends JPanel {


    private List<Shape> shapes = new ArrayList<>();
    public Panel() {
        super(new BorderLayout());
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    @Override
    protected void paintComponent(Graphics g) {
        shapes.forEach(shape -> shape.paintComponent(g));
    }
}
