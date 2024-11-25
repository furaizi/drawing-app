package org.example.model;

import org.example.controller.Controller;

import org.example.model.shape_factories.ShapeFactory;
import org.example.model.shape_factories.ShapeType;
import org.example.model.shapes.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Model {

    private static final int NUMBER_IN_JOURNAL = 19 + 1;

    private Controller controller;
    private ShapeFactory shapeFactory = new ShapeFactory();
    private Shape currentShape;
    private ShapeType currentShapeType;
    private List<Shape> shapes = new ArrayList<>();
    private int currentIndex = 0;

    public Model(Controller controller) {
        this.controller = controller;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void createShape(Point startPoint, Point endPoint) {
        var shape = shapeFactory.create(currentShapeType, startPoint, endPoint);
        this.currentShape = shape;
        shapes.add(shape);
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
