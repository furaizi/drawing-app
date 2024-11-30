package org.example.model;

import org.example.controller.Controller;

import org.example.model.shape_factories.ShapeFactory;
import org.example.model.shape_factories.ShapeType;
import org.example.model.shapes.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Model {

    private ShapeFactory shapeFactory = new ShapeFactory();
    private Shape currentShape;
    private ShapeType currentShapeType;
    private List<Shape> shapes = new ArrayList<>();

    public Model() {}

    public List<Shape> getShapes() {
        return shapes;
    }

    public String getStringRepresentation() {
        return shapes.stream()
                .map(Shape::toString)
                .collect(Collectors.joining("\n"));
    }

    public void createShape(Point startPoint, Point endPoint) {
        var shape = shapeFactory.create(currentShapeType, startPoint, endPoint);
        this.currentShape = shape;
        shapes.add(shape);
        Controller.getInstance().update();
    }

    public void updateCurrentShapeEndpoint(Point endPoint) {
        currentShape.setEndPoint(endPoint);
        Controller.getInstance().update();
    }

    public void updateSelectedStatus(List<Integer> selectedIndexes) {
//        shapes.forEach(shape -> shape.setSelected(false));
//        shapes.get(shapeIndex).setSelected(true);
//        Controller.getInstance().update();

        // Устанавливаем выделение только для указанных индексов
        IntStream.range(0, shapes.size()).forEach(i -> {
            shapes.get(i).setSelected(selectedIndexes.contains(i));
        });

        // Обновляем состояние приложения
        Controller.getInstance().update();
    }

    public void setCurrentShapeAsCreated() {
        currentShape.setBeingCreated(false);
    }

    public void setCurrentShapeType(ShapeType currentShapeType) {
        this.currentShapeType = currentShapeType;
    }
}
