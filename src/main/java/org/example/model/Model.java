package org.example.model;

import org.example.model.shape_factories.ShapeFactory;
import org.example.model.shape_factories.ShapeType;
import org.example.model.shapes.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Model {
    private final List<ModelObserver> observers = new ArrayList<>();
    private final List<Shape> shapes = new ArrayList<>();
    private final ShapeFactory shapeFactory = new ShapeFactory();
    private Shape currentShape;
    private ShapeType currentShapeType;

    public void addObserver(ModelObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        observers.forEach(ModelObserver::modelUpdated);
    }

    public void createShape(Point startPoint, Point endPoint) {
        currentShape = shapeFactory.create(currentShapeType, startPoint, endPoint);
        shapes.add(currentShape);
        notifyObservers();
    }

    public void updateCurrentShapeEndpoint(Point endPoint) {
        currentShape.setEndPoint(endPoint);
        notifyObservers();
    }

    public void updateSelectedStatus(List<Integer> selectedIndexes) {
        IntStream.range(0, shapes.size()).forEach(i ->
                shapes.get(i).setSelected(selectedIndexes.contains(i))
        );
        notifyObservers();
    }

    public void setCurrentShapeAsCreated() {
        currentShape.setBeingCreated(false);
        notifyObservers();
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public String getStringRepresentation() {
        return shapes.stream()
                .map(Shape::toString)
                .collect(Collectors.joining("\n"));
    }

    public void setCurrentShapeType(ShapeType currentShapeType) {
        this.currentShapeType = currentShapeType;
    }
}

