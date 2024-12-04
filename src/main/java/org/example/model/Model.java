package org.example.model;

import org.example.model.shape_factories.ShapeFactory;
import org.example.model.shape_factories.ShapeType;
import org.example.model.shapes.Shape;
import static org.example.model.ModelEvents.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Model {

    private final ObserversManager observersManager = new ObserversManager();
    private final List<Shape> shapes = new ArrayList<>();
    private final ShapeFactory shapeFactory = new ShapeFactory();
    private Shape currentShape;
    private ShapeType currentShapeType;

    public void createShape(Point startPoint, Point endPoint) {
        currentShape = shapeFactory.create(currentShapeType, startPoint, endPoint);
        shapes.add(currentShape);
        observersManager.notify(SHAPES_LIST_CHANGED);
    }

    public void removeShape(int index) {
        shapes.remove(index);
        observersManager.notify(SHAPES_LIST_CHANGED);
    }

    public void updateCurrentShapeEndpoint(Point endPoint) {
        currentShape.setEndPoint(endPoint);
        observersManager.notify(SHAPES_LIST_CHANGED);
    }

    public void updateSelectedStatus(List<Integer> selectedIndexes) {
        IntStream.range(0, shapes.size()).forEach(i ->
                shapes.get(i).setSelected(selectedIndexes.contains(i))
        );
        observersManager.notify(SHAPES_LIST_CHANGED);
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public ShapeType getCurrentShapeType() {
        return currentShapeType;
    }

    public ObserversManager getObserversManager() {
        return observersManager;
    }

    public String getStringRepresentation() {
        return shapes.stream()
                .map(Shape::toString)
                .collect(Collectors.joining("\n"));
    }

    public void setCurrentShapeType(ShapeType currentShapeType) {
        this.currentShapeType = currentShapeType;
        observersManager.notify(CHOSEN_SHAPE_CHANGED);
    }

    public void setCurrentShapeAsCreated() {
        currentShape.setBeingCreated(false);
        observersManager.notify(SHAPES_LIST_CHANGED);
    }

}

