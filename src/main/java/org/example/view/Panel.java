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
        view.getController()
                .getShapes()
                .forEach(shape -> shape.paintComponent(g));
    }
}
