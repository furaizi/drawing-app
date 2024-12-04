package org.example.view;

import org.example.controller.Controller;
import org.example.model.observer.ModelObserver;
import org.example.view.menu.MenuBar;
import org.example.view.table.Table;
import org.example.view.table.TableDialog;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame implements ModelObserver {

    private final MenuBar menuBar = new MenuBar(this);
    private final Panel panel = new Panel();
    private final ToolBar toolBar = new ToolBar();
    private final TableDialog tableDialog = new TableDialog(this);

    public View() throws HeadlessException {
        initGUI();
    }

    private void initGUI() {
        Controller.getInstance().setView(this);
        setJMenuBar(menuBar);
        add(panel, BorderLayout.CENTER);
        add(toolBar, BorderLayout.NORTH);

        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void showTable() {
        tableDialog.setVisible(true);
    }

    @Override
    public void modelUpdated(String data) {
        if (data.isEmpty()) {
            repaint();
            revalidate();
        }
        else
            setTitle(data);
    }

    public Table getTable() {
        return tableDialog.getTable();
    }

    public MenuBar getViewMenuBar() {
        return menuBar;
    }

    public Panel getPanel() {
        return panel;
    }

    public ToolBar getToolBar() {
        return toolBar;
    }

}
