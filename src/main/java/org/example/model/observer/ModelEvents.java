package org.example.model.observer;


import java.util.Optional;
import java.util.stream.Stream;

public enum ModelEvents {
    SHAPES_LIST_CHANGED("shapesListChanged"), CHOSEN_SHAPE_CHANGED("chosenShapeChanged");

    private final String eventName;

    ModelEvents(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public static Optional<ModelEvents> fromName(String name) {
        return Stream.of(values())
                .filter(type -> type.eventName.equals(name))
                .findFirst();
    }
}
