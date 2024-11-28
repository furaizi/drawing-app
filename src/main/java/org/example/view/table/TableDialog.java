package org.example.view.table;

import org.example.view.View;

import javax.swing.*;
import java.awt.*;

public class TableDialog extends JDialog {

    private final View view;
    private final Table table = new Table();
    private final JScrollPane scrollPane = new JScrollPane(table);

    public TableDialog(View view) {
        super(view, "Data table", false);
        this.view = view;
        setLocationRelativeTo(view);
        setSize(400, 300);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    public void update() {
        table.update();
    }
}
