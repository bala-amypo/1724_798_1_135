package com.example.demo.service;

import com.example.demo.entity.Event;
import java.util.List;

public interface EventService {

    Event create(Event event);

    Event update(Long id, Event event);

    Event getById(Long id);

    List<Event> getActiveEvents();

    void deactivate(Long id);
}
