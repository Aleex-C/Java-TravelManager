package com.example.model_practic.utils.observer;

import com.example.model_practic.utils.utils.Event;

public interface Observable<E extends Event> {
    void addObserver(Observer<E> observer);
    void removeObserver(Observer<E> observer);
    void notifyObservers(E event);
}
