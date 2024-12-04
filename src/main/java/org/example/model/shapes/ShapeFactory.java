package org.example.model.shapes;

import java.awt.*;

public class ShapeFactory {

    public Shape create(ShapeType shapeType, Point startPoint, Point endPoint) {
        Shape shape;
        switch (shapeType) {
            case POINT -> shape = new PointShape(startPoint, endPoint);
            case LINE -> shape = new Line(startPoint, endPoint);
            case RECTANGLE -> shape = new Rectangle(startPoint, endPoint);
            case ELLIPSE -> shape = new Ellipse(startPoint, endPoint);
            case LINEOO -> shape = new LineOO(startPoint, endPoint);
            case CUBE -> shape = new Cube(startPoint, endPoint);
            default -> shape = null;
        }
        return shape;
    }
}
