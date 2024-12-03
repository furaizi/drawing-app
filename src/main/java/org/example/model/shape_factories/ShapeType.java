package org.example.model.shape_factories;

import java.util.Optional;
import java.util.stream.Stream;

public enum ShapeType {
    POINT("Point"), LINE("Line"), RECTANGLE("Rectangle"), ELLIPSE("Ellipse"), LINEOO("LineOO"), CUBE("Cube");

    private final String name;
    ShapeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Optional<ShapeType> fromName(String name) {
        return Stream.of(values())
                .filter(type -> type.name.equals(name))
                .findFirst();
    }
}
