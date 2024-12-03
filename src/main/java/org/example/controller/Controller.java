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

public class Controller implements ActionListener, MouseListener, MouseMotionListener, ListSelectionListener {

    private static Controller INSTANCE;

    private View view;
    private Model model = new Model();
    private boolean shapeIsBeingCreated = false;

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
        else if (e.getSource() instanceof JRadioButtonMenuItem || e.getSource() instanceof JMenuItem)
            objectName = e.getActionCommand();

        if (objectName.equals("Save As")) {
            var fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Сохранить таблицу");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files (*.txt)", "txt"));

            int userSelection = fileChooser.showSaveDialog(view);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                if (!fileToSave.getName().endsWith(".txt")) {
                    fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
                }

                try {
                    var table = model.getStringRepresentation();
                    Files.writeString(Paths.get(fileToSave.getAbsolutePath()), table);
                    JOptionPane.showMessageDialog(view, "Файл успешно сохранен!", "Успех", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(view, "Ошибка при сохранении файла: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }


        }
        else {
            var shapeType = ShapeType.fromName(objectName)
                            .orElseThrow(() -> new IllegalArgumentException("Unknown shape type"));
            model.setCurrentShapeType(shapeType);
            view.setTitle(objectName);
            view.updateSelectedObject();
        }
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

    private boolean confirmDelete() {
        return JOptionPane.showConfirmDialog(
                view,
                "Удалить выбранные строки?",
                "Подтверждение",
                JOptionPane.YES_NO_OPTION
        ) == JOptionPane.YES_OPTION;
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
