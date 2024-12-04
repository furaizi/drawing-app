package org.example.controller.listeners;

import org.example.controller.Controller;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TableSelectionListener implements ListSelectionListener {

    private final Controller controller = Controller.getInstance();

    @Override
    public void valueChanged(ListSelectionEvent e) {
        controller.handleTableRowSelection(e);
    }
}
