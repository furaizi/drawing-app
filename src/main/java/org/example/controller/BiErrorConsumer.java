package org.example.controller;

import java.io.IOException;

public interface BiErrorConsumer<T, U> {
    void accept(T t, U u) throws IOException;
}
