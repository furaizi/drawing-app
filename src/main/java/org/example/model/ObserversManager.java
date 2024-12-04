package org.example.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ObserversManager {

    private final HashMap<ModelEvents, List<ModelObserver>> observersMap = new HashMap<>();

    public void subscribe(ModelEvents eventType, ModelObserver observer) {
        observersMap.computeIfAbsent(eventType, k -> new ArrayList<>())
                .add(observer);
    }

    public void notify(ModelEvents eventType) {
        observersMap.get(eventType)
                .forEach(ModelObserver::modelUpdated);
    }
}
