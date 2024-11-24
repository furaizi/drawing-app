package org.example.model.shape_factories;

import org.example.model.shapes.Cube;
import org.example.model.shapes.Shape;

import java.awt.*;

public class CubeFactory implements ShapeFactory {

    @Override
    public Shape create(Point startPoint, Point endPoint) {
        return new Cube(startPoint, endPoint);
    }
}
