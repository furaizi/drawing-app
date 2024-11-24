package org.example.model.shape_factories;

import org.example.model.shapes.Shape;
import java.awt.*;

public interface ShapeFactory {
    Shape create(Point startPoint, Point endPoint);
}
