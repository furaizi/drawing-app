package org.example.view;

import org.example.controller.Controller;
import org.example.view.menu.MenuBar;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    private MenuBar menuBar = new MenuBar(this);
    private Panel panel = new Panel(this);
    private ToolBar toolBar = new ToolBar(this);
    private Table table = new Table(this);

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
        table.setVisible(true);
    }

    public void updateTable() {
        table.update();
    }

    public void updateSelectedObject() {
        menuBar.update(getTitle());
        toolBar.update(getTitle());
    }

//    public Controller getController() {
//        return controller;
//    }
}
