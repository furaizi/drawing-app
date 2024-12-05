package org.example.model.observer;

import org.example.model.shapes.Shape;

import java.util.List;

public interface ModelObserver {

    void modelUpdated(String data);
    void modelUpdated(List<Shape> shapes);
}
