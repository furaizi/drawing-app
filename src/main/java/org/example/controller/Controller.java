package org.example.controller;

import org.example.model.Model;
import org.example.model.shape_factories.*;
import org.example.model.shapes.Shape;
import org.example.view.View;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;

public class Controller implements ActionListener, MouseListener, MouseMotionListener {

    private View view;
    private Model model = new Model(this);
    private boolean shapeIsBeingCreated = false;
    private HashMap<String, ShapeFactory> shapeFactories = new HashMap<>() {{
       put("Point", new PointFactory());
       put("Line", new LineFactory());
       put("Rectangle", new RectangleFactory());
       put("Ellipse", new EllipseFactory());
       put("LineOO", new LineOOFactory());
       put("Cube", new CubeFactory());
    }};

    public Controller(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String objectName = "";
        if (e.getSource() instanceof JToggleButton button) {
            objectName = button.getToolTipText();
        }
        else if (e.getSource() instanceof JRadioButtonMenuItem)
            objectName = e.getActionCommand();

        var shapeFactory = shapeFactories.get(objectName);
        model.setShapeFactory(shapeFactory);
        view.setTitle(objectName);
        view.updateSelectedObject();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        model.createShape(e.getPoint(), e.getPoint());
        model.setCurrentShapeAsCreated();
        shapeIsBeingCreated = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!shapeIsBeingCreated)
            return;

        model.updateCurrentShape(e.getPoint());
        model.setCurrentShapeAsCreated();
        shapeIsBeingCreated = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        if (shapeIsBeingCreated)
            model.updateCurrentShape(e.getPoint());
        else {
            model.createShape(e.getPoint(), e.getPoint());
            shapeIsBeingCreated = true;
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    public void update() {
        view.revalidate();
        view.repaint();
    }

    public Shape[] getShapes() {
        return model.getShapes();
    }

}
