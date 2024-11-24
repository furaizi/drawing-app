package org.example.model.shape_factories;

import org.example.model.shapes.LineOO;
import org.example.model.shapes.Shape;

import java.awt.*;

public class LineOOFactory implements ShapeFactory {

    @Override
    public Shape create(Point startPoint, Point endPoint) {
        return new LineOO(startPoint, endPoint);
    }
}
