package org.example.model.shapes;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

import static org.example.model.shapes.ShapeType.*;

public class ShapeFactory {

    private final Map<ShapeType, BiFunction<Point, Point, Shape>> creators = new HashMap<>();

    public ShapeFactory() {
        register(POINT, PointShape::new);
        register(LINE, Line::new);
        register(RECTANGLE, Rectangle::new);
        register(ELLIPSE, Ellipse::new);
        register(CUBE, Cube::new);
        register(LINEOO, LineOO::new);
    }

    public Shape create(ShapeType type, Point start, Point end) {
        return Optional.ofNullable(creators.get(type))
                .orElseThrow(() -> new IllegalArgumentException("Unknown shape type"))
                .apply(start, end);
    }

    private void register(ShapeType type, BiFunction<Point, Point, Shape> creator) {
        creators.put(type, creator);
    }
}
