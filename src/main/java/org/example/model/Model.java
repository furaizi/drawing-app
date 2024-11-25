package org.example.model;

import org.example.controller.Controller;

import org.example.model.shape_factories.ShapeFactory;
import org.example.model.shape_factories.ShapeType;
import org.example.model.shapes.Shape;

import java.awt.*;

public class Model {

    private static final int NUMBER_IN_JOURNAL = 19 + 1;

    private Controller controller;
    private ShapeFactory shapeFactory = new ShapeFactory();
    private Shape currentShape;
    private ShapeType currentShapeType;
    private Shape[] shapes = new Shape[100 + NUMBER_IN_JOURNAL];
    private int currentIndex = 0;

    public Model(Controller controller) {
        this.controller = controller;
    }

    public Shape[] getShapes() {
        return shapes;
    }

    public void createShape(Point startPoint, Point endPoint) {
        var shape = shapeFactory.create(currentShapeType, startPoint, endPoint);
        this.currentShape = shape;
        shapes[currentIndex++] = shape;
        controller.update();
    }

    public void updateCurrentShape(Point endPoint) {
        currentShape.setEndPoint(endPoint);
        controller.update();
    }

    public void setCurrentShapeAsCreated() {
        currentShape.setBeingCreated(false);
    }

    public void setCurrentShapeType(ShapeType currentShapeType) {
        this.currentShapeType = currentShapeType;
    }
}
