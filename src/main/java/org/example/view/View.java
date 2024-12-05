package org.example.view;

import org.example.controller.Controller;
import org.example.model.observer.ModelObserver;
import org.example.model.shapes.Shape;
import org.example.view.menu.FileMenu;
import org.example.view.menu.HelpMenu;
import org.example.view.menu.MenuBar;
import org.example.view.menu.ObjectsMenu;
import org.example.view.table.Table;
import org.example.view.table.TableDialog;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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
        setTitle(data);
    }

    @Override
    public void modelUpdated(List<Shape> shapes) {
        panel.setShapes(shapes);
        repaint();
        revalidate();
    }

    public TableDialog getTableDialog() {
        return tableDialog;
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

    public FileMenu getFileMenu() {
        return menuBar.getFileMenu();
    }

    public ObjectsMenu getObjectsMenu() {
        return menuBar.getObjectsMenu();
    }

    public HelpMenu getHelpMenu() {
        return menuBar.getHelpMenu();
    }
}
