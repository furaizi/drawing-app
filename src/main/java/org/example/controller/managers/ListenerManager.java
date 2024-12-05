package org.example.controller.managers;

import org.example.controller.Controller;
import org.example.controller.listeners.DeleteKeyListener;
import org.example.controller.listeners.ShapeMouseListener;
import org.example.view.View;

public class ListenerManager {

    public static void addAllListeners(Controller controller, View view) {
        var mouseListener = new ShapeMouseListener();

        view.getFileMenu().addActionListener(controller::handleFileMenuAction);
        view.getObjectsMenu().addActionListener(controller::handleObjectsMenuAction);
        view.getToolBar().addActionListener(controller::handleToolBarAction);

        view.getPanel().addMouseListener(mouseListener);
        view.getPanel().addMouseMotionListener(mouseListener);

        view.getTable().addKeyListener(new DeleteKeyListener());
        view.getTable().addTableSelectionListener(controller::handleTableRowSelection);
    }
}
