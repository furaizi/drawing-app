package org.example.controller;

import org.example.controller.listeners.DeleteKeyListener;
import org.example.controller.listeners.ShapeMouseListener;
import org.example.model.Model;
import org.example.model.shapes.Shape;
import org.example.model.shapes.ShapeType;
import org.example.view.View;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

import static org.example.model.observer.ModelEvents.*;

public class Controller {

    private static Controller INSTANCE;

    private View view;
    private final Model model = new Model();
    private boolean shapeIsBeingCreated = false;

    private Controller() {
    }

    public static Controller getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Controller();

        return INSTANCE;
    }

    public void setView(View view) {
        this.view = view;
        addAllSubscribers();
        addAllListeners();
    }

    private void addAllSubscribers() {
        model.getObserversManager().subscribe(SHAPES_LIST_CHANGED, view);
        model.getObserversManager().subscribe(SHAPES_LIST_CHANGED, view.getTable());
        model.getObserversManager().subscribe(CHOSEN_SHAPE_CHANGED, view);
        model.getObserversManager().subscribe(CHOSEN_SHAPE_CHANGED, view.getToolBar());
        model.getObserversManager().subscribe(CHOSEN_SHAPE_CHANGED, view.getViewMenuBar().getObjectsMenu());
    }

    private void addAllListeners() {
        var mouseListener = new ShapeMouseListener();

        view.getViewMenuBar().getFileMenu().addActionListener(this::handleFileMenuAction);
        view.getViewMenuBar().getObjectsMenu().addActionListener(this::handleObjectsMenuAction);
        view.getToolBar().addActionListener(this::handleToolBarAction);

        view.getPanel().addMouseListener(mouseListener);
        view.getPanel().addMouseMotionListener(mouseListener);

        view.getTable().addKeyListener(new DeleteKeyListener());
        view.getTable().addTableSelectionListener(this::handleTableRowSelection);
    }

    public void handleTableRowSelection(ListSelectionEvent e) {
        if (e.getValueIsAdjusting())
            return;

        var selectionModel = (DefaultListSelectionModel) e.getSource();
        var selectedIndexes = IntStream.range(0, model.getShapes().size())
                .filter(selectionModel::isSelectedIndex)
                .boxed()
                .toList();

        model.updateSelectedStatus(selectedIndexes);
    }

    public void handleObjectsMenuAction(ActionEvent e) {
        var objectName = e.getActionCommand();
        updateShapeType(objectName);
    }

    public void handleToolBarAction(ActionEvent e) {
        var toggleButton = (JToggleButton) e.getSource();
        var objectName = toggleButton.getToolTipText();
        updateShapeType(objectName);
    }

    public void handleFileMenuAction(ActionEvent e) {
        if (e.getActionCommand().equals("Save As"))
            handleSaveAsAction(e);
    }

    private void handleSaveAsAction(ActionEvent e) {
        var fileChooser = FileManager.getFileChooser();
        int userSelection = fileChooser.showSaveDialog(view);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try {
                FileManager.saveToFile(model.getStringRepresentation(), fileChooser.getSelectedFile());
                JOptionPane.showMessageDialog(view,
                        "The file was saved successfully",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(view,
                        "Error while saving the file: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void handleMouseClick(MouseEvent e) {
        model.createShape(e.getPoint(), e.getPoint());
        model.setCurrentShapeAsCreated();
        shapeIsBeingCreated = false;
    }

    public void handleMouseRelease(MouseEvent e) {
        if (!shapeIsBeingCreated)
            return;

        model.updateCurrentShapeEndpoint(e.getPoint());
        model.setCurrentShapeAsCreated();
        shapeIsBeingCreated = false;
    }

    public void handleMouseDrag(MouseEvent e) {
        if (shapeIsBeingCreated)
            model.updateCurrentShapeEndpoint(e.getPoint());
        else {
            model.createShape(e.getPoint(), e.getPoint());
            shapeIsBeingCreated = true;
        }
    }


    public void handleDeleteKey() {
        var table = view.getTable();
        var selectedRows = IntStream.of(table.getSelectedRows())
                .boxed()
                .sorted(Comparator.reverseOrder())
                .toList();

        if (!selectedRows.isEmpty() && confirmDelete())
            selectedRows.forEach(model::removeShape);
    }


    public List<Shape> getShapes() {
        return model.getShapes();
    }

    private void updateShapeType(String objectName) {
        var shapeType = ShapeType.fromName(objectName)
                .orElseThrow(() -> new IllegalArgumentException("Unknown shape type"));
        model.setCurrentShapeType(shapeType);
    }

    private boolean confirmDelete() {
        return JOptionPane.showConfirmDialog(
                view,
                "Delete chosen rows?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION
        ) == JOptionPane.YES_OPTION;
    }

}
