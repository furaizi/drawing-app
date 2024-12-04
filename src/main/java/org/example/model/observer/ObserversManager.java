package org.example.model.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ObserversManager {

    private final HashMap<ModelEvents, List<ModelObserver>> observersMap = new HashMap<>();

    public void subscribe(ModelEvents eventType, ModelObserver observer) {
        observersMap.computeIfAbsent(eventType, k -> new ArrayList<>())
                .add(observer);
    }

    public void notify(ModelEvents eventType, String data) {
        observersMap.get(eventType)
                .forEach(observer -> observer.modelUpdated(data));
    }

    public void notify(ModelEvents eventType) {
        notify(eventType, "");
    }


    public enum ModelEvents {
        SHAPES_LIST_CHANGED, CHOSEN_SHAPE_CHANGED
    }
}
