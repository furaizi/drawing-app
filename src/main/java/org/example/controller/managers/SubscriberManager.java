package org.example.controller.managers;

import org.example.model.Model;
import org.example.view.View;

import static org.example.model.observer.ObserversManager.ModelEvents.*;

public class SubscriberManager {

    public static void addAllSubscribers(Model model, View view) {
        model.subscribe(SHAPES_LIST_CHANGED, view);
        model.subscribe(SHAPES_LIST_CHANGED, view.getTable());
        model.subscribe(CHOSEN_SHAPE_CHANGED, view);
        model.subscribe(CHOSEN_SHAPE_CHANGED, view.getToolBar());
        model.subscribe(CHOSEN_SHAPE_CHANGED, view.getObjectsMenu());
    }
}
