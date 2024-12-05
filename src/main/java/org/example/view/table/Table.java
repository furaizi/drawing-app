package org.example.view.table;

import org.example.model.observer.ModelObserver;
import org.example.model.shapes.Shape;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

public class Table extends JTable implements ModelObserver {

    private static final String[] COLUMN_NAMES = {"Type", "x1", "y1", "x2", "y2"};
    private static final DefaultTableModel tableModel = new DefaultTableModel(COLUMN_NAMES, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column != 0;
        }
    };

    private ListSelectionListener tableSelectionListener;

    public Table() {
        super(tableModel);
    }

    @Override
    public void modelUpdated(String data) {
    }

    @Override
    public void modelUpdated(List<Shape> shapes) {
        getSelectionModel().removeListSelectionListener(tableSelectionListener);

        int[] selectedRows = getSelectedRows();

        getDataVector().removeAllElements();
        shapes.stream()
                .map(this::convertToRow)
                .forEach(tableModel::addRow);

        clearSelection();
        for (int rowIndex : selectedRows) {
            if (rowIndex >= 0 && rowIndex < tableModel.getRowCount()) {
                addRowSelectionInterval(rowIndex, rowIndex);
            }
        }

        getSelectionModel().addListSelectionListener(tableSelectionListener);
    }

    public void addTableSelectionListener(ListSelectionListener listener) {
        this.tableSelectionListener = listener;
        getSelectionModel().addListSelectionListener(listener);
    }


    private String[] convertToRow(Shape shape) {
        var shapeType = shape.getClass().getSimpleName();
        var x1 = String.valueOf(shape.getStartPoint().x);
        var y1 = String.valueOf(shape.getStartPoint().y);
        var x2 = String.valueOf(shape.getEndPoint().x);
        var y2 = String.valueOf(shape.getEndPoint().y);

        return new String[]{shapeType, x1, y1, x2, y2};
    }

    private Vector<Vector> getDataVector() {
        return tableModel.getDataVector();
    }

}
