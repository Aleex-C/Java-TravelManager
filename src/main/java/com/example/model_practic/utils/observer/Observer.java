package com.example.model_practic.utils.observer;


import com.example.model_practic.utils.utils.Event;

public interface Observer<E extends Event> {
    void update(E event);
}
