package org.example.controller;

import org.example.model.Model;
import org.example.model.shape_factories.*;
import org.example.model.shapes.Shape;
import org.example.view.View;
import org.example.view.table.Table;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.example.model.shape_factories.ShapeType.*;

public class Controller implements ListSelectionListener {

    private static Controller INSTANCE;

    private View view;
    private Model model = new Model();
    private boolean shapeIsBeingCreated = false;

    private Controller() {
        model.addObserver(this::update);
    }

    public static Controller getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Controller();
        }

        return INSTANCE;
    }

    public void setView(View view) {
        this.view = view;
    }

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

    public void handleObjectsMenuAction(ActionEvent e) {
        var objectName = e.getActionCommand();
        updateShapeType(objectName);
    }

    public void handleToolBarAction(ActionEvent e) {
        var toggleButton = (JToggleButton) e.getSource();
        var objectName = toggleButton.getToolTipText();
        updateShapeType(objectName);
    }

    public void handleSaveAsAction(ActionEvent e) {
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

        if (!selectedRows.isEmpty() && confirmDelete()) {
            selectedRows.forEach(index -> model.getShapes().remove(index.intValue()));
            update();
        }
    }

    public void update() {
        view.revalidate();
        view.repaint();
        view.updateTable();
    }

    public List<Shape> getShapes() {
        return model.getShapes();
    }

    private void updateShapeType(String objectName) {
        var shapeType = ShapeType.fromName(objectName)
                .orElseThrow(() -> new IllegalArgumentException("Unknown shape type"));
        model.setCurrentShapeType(shapeType);
        view.setTitle(objectName);
        view.updateSelectedObject();
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
