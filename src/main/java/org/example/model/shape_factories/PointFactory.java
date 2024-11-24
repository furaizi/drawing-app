package org.example.model.shape_factories;

import org.example.model.shapes.PointShape;
import org.example.model.shapes.Shape;

import java.awt.*;

public class PointFactory implements ShapeFactory {
    @Override
    public Shape create(Point startPoint, Point endPoint) {
        return new PointShape(startPoint, endPoint);
    }
}
