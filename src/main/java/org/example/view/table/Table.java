package org.example.view.table;

import org.example.controller.Controller;
import org.example.model.shapes.Shape;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.List;

public class Table extends JTable {

    private static final String[] COLUMN_NAMES = {"Type", "x1", "y1", "x2", "y2"};
    private List<Shape> shapes = Controller.getInstance().getShapes();
    private static DefaultTableModel tableModel = new DefaultTableModel(COLUMN_NAMES, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column != 0;
        }
    };

    public Table() {
        super(tableModel);
        getSelectionModel().addListSelectionListener(Controller.getInstance());
    }

    public void update() {
        getSelectionModel().removeListSelectionListener(Controller.getInstance());

        int[] selectedRows = getSelectedRows();

        tableModel.getDataVector().removeAllElements();
        shapes.forEach(shape -> tableModel.addRow(convertToRow(shape)));

        clearSelection();
        for (int rowIndex : selectedRows) {
            if (rowIndex >= 0 && rowIndex < tableModel.getRowCount()) {
                addRowSelectionInterval(rowIndex, rowIndex);
            }
        }

        getSelectionModel().addListSelectionListener(Controller.getInstance());
    }


    private String[] convertToRow(Shape shape) {
        var shapeType = shape.getClass().getSimpleName();
        var x1 = String.valueOf(shape.getStartPoint().x);
        var y1 = String.valueOf(shape.getStartPoint().y);
        var x2 = String.valueOf(shape.getEndPoint().x);
        var y2 = String.valueOf(shape.getEndPoint().y);

        return new String[]{shapeType, x1, y1, x2, y2};
    }

}
