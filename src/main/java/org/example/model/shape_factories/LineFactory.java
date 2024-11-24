package org.example.model.shape_factories;

import org.example.model.shapes.Line;
import org.example.model.shapes.Shape;

import java.awt.*;

public class LineFactory implements ShapeFactory {

    @Override
    public Shape create(Point startPoint, Point endPoint) {
        return new Line(startPoint, endPoint);
    }
}
