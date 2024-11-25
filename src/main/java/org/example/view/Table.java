package org.example.view;

import org.example.controller.Controller;
import org.example.model.shapes.Shape;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Table extends JDialog {

    private static final String[] columnNames = {"Type", "x1", "y1", "x2", "y2"};
    private View view;
    private DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
    private JTable table = new JTable(tableModel);
    private JScrollPane scrollPane = new JScrollPane(table);
    private List<Shape> shapes;

    public Table(View view) {
        super(view, "Data table", false);
        this.view = view;
        this.shapes = Controller.getInstance().getShapes();
        setLocationRelativeTo(view);
        setSize(400, 300);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    public void update() {
        removeAllRows();
        shapes.forEach(shape -> this.tableModel.addRow(convertToRow(shape)));
    }

    private void removeAllRows() {
        tableModel.getDataVector().removeAllElements();
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
