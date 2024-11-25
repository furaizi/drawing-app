package org.example.controller;

import org.example.model.Model;
import org.example.model.shape_factories.*;
import org.example.model.shapes.Shape;
import org.example.view.View;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;

import static org.example.model.shape_factories.ShapeType.*;

public class Controller implements ActionListener, MouseListener, MouseMotionListener {

    private static Controller INSTANCE;

    private View view;
    private Model model = new Model();
    private boolean shapeIsBeingCreated = false;
    private HashMap<String, ShapeType> shapeTypes = new HashMap<>() {{
       put("Point", POINT);
       put("Line", LINE);
       put("Rectangle", RECTANGLE);
       put("Ellipse", ELLIPSE);
       put("LineOO", LINEOO);
       put("Cube", CUBE);
    }};

    private Controller() {}

    public static Controller getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Controller();

        return INSTANCE;
    }

    public void setView(View view) {
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

        var shapeType = shapeTypes.get(objectName);
        model.setCurrentShapeType(shapeType);
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
        view.updateTable();
    }

    public List<Shape> getShapes() {
        return model.getShapes();
    }

}
