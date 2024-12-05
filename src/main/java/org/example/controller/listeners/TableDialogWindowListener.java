package org.example.controller.listeners;

import org.example.view.table.TableDialog;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TableDialogWindowListener extends WindowAdapter {

    @Override
    public void windowClosing(WindowEvent e) {
        var tableDialog = (TableDialog) e.getWindow();
        tableDialog.getTable().clearSelection();
    }
}
