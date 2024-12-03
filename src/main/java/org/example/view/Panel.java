package org.example.view;

import org.example.controller.Controller;
import org.example.controller.listeners.ShapeMouseListener;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    private View view;

    public Panel(View view) {
        super(new BorderLayout());
        this.view = view;
        addMouseListener(new ShapeMouseListener());
        addMouseMotionListener(new ShapeMouseListener());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Controller.getInstance()
                .getShapes()
                .forEach(shape -> shape.paintComponent(g));
    }
}
