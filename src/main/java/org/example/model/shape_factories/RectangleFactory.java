package org.example.model.shape_factories;

import org.example.model.shapes.Rectangle;
import org.example.model.shapes.Shape;

import java.awt.*;

public class RectangleFactory implements ShapeFactory {

    @Override
    public Shape create(Point startPoint, Point endPoint) {
        return new Rectangle(startPoint, endPoint);
    }
}
