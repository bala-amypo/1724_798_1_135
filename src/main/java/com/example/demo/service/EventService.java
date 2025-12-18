// File: src/main/java/com/example/demo/service/EventService.java
package com.example.demo.service;

import com.example.demo.entity.Event;
import java.util.List;

public interface EventService {
    Event createEvent(Event event);
    Event updateEvent(Long id, Event event);
    Event getEventById(Long id);
    Event getById(Long id); // Test expects this
    List<Event> getActiveEvents();
    Event deactivateEvent(Long id);
}