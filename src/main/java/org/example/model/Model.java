package org.example.model;

import org.example.model.observer.ObserversManager;
import org.example.model.shapes.ShapeFactory;
import org.example.model.shapes.ShapeType;
import org.example.model.shapes.Shape;
import static org.example.model.observer.ObserversManager.ModelEvents.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Model {

    private final ObserversManager observersManager = new ObserversManager();
    private List<Shape> shapes = new ArrayList<>();
    private final ShapeFactory shapeFactory = new ShapeFactory();
    private Shape currentShape;
    private ShapeType currentShapeType;

    public void createShape(Point point) {
        currentShape = shapeFactory.create(currentShapeType, point, point);
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

    public void loadShapes(List<Shape> shapes) {
        this.shapes = shapes;
        shapes.forEach(shape -> shape.setBeingCreated(false));
        observersManager.notify(SHAPES_LIST_CHANGED);
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
        observersManager.notify(CHOSEN_SHAPE_CHANGED, currentShapeType.getName());
    }

    public void setCurrentShapeAsCreated() {
        currentShape.setBeingCreated(false);
        observersManager.notify(SHAPES_LIST_CHANGED);
    }

}

