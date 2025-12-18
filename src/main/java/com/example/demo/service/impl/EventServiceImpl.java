// File: src/main/java/com/example/demo/service/impl/EventServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.Event;
import com.example.demo.repository.EventRepository;
import com.example.demo.service.EventService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    
    private final EventRepository eventRepository;
    
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    
    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }
    
    @Override
    public Event updateEvent(Long id, Event event) {
        Event existingEvent = getEventById(id);
        
        if (event.getTitle() != null) {
            existingEvent.setTitle(event.getTitle());
        }
        if (event.getDescription() != null) {
            existingEvent.setDescription(event.getDescription());
        }
        if (event.getLocation() != null) {
            existingEvent.setLocation(event.getLocation());
        }
        if (event.getCategory() != null) {
            existingEvent.setCategory(event.getCategory());
        }
        
        return eventRepository.save(existingEvent);
    }
    
    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
    }
    
    @Override
    public List<Event> getActiveEvents() {
        return eventRepository.findByIsActiveTrue();
    }
    
    @Override
    public Event deactivateEvent(Long id) {
        Event event = getEventById(id);
        event.setIsActive(false);
        return eventRepository.save(event);
    }
}