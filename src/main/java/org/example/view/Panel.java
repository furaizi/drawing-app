package org.example.view;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    private View view;

    public Panel(View view) {
        super(new BorderLayout());
        this.view = view;
        addMouseListener(view.getController());
        addMouseMotionListener(view.getController());
    }

    @Override
    protected void paintComponent(Graphics g) {
        for (var shape : view.getController().getShapes()) {
            if (shape == null)
                continue;

            shape.paintComponent(g);
        }
    }
}