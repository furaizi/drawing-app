package org.example.controller;

import org.example.model.Model;
import org.example.model.shape_factories.*;
import org.example.model.shapes.Shape;
import org.example.view.View;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.example.model.shape_factories.ShapeType.*;

public class Controller implements ActionListener, MouseListener, MouseMotionListener, ListSelectionListener {

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

        model.updateCurrentShapeEndpoint(e.getPoint());
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
            model.updateCurrentShapeEndpoint(e.getPoint());
        else {
            model.createShape(e.getPoint(), e.getPoint());
            shapeIsBeingCreated = true;
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting())
            return;

        var selectionModel = (DefaultListSelectionModel) e.getSource();
        var selectedIndexes = IntStream.range(0, model.getShapes().size())
                .filter(selectionModel::isSelectedIndex)
                .boxed()
                .toList();

        model.updateSelectedStatus(selectedIndexes);
    }

    public void update() {
        view.revalidate();
        view.repaint();
        view.updateTable();
    }

    public List<Shape> getShapes() {
        return model.getShapes();
    }

}
