package org.example.model.shape_factories;

import org.example.model.shapes.Ellipse;
import org.example.model.shapes.Shape;

import java.awt.*;

public class EllipseFactory implements ShapeFactory {

    @Override
    public Shape create(Point startPoint, Point endPoint) {
        return new Ellipse(startPoint, endPoint);
    }
}
