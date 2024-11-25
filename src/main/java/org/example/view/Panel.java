package org.example.view;

import org.example.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    private View view;

    public Panel(View view) {
        super(new BorderLayout());
        this.view = view;
        addMouseListener(Controller.getInstance());
        addMouseMotionListener(Controller.getInstance());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Controller.getInstance()
                .getShapes()
                .forEach(shape -> shape.paintComponent(g));
    }
}
