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
    public Event create(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event update(Long id, Event event) {
        Event existing = getById(id);
        existing.setTitle(event.getTitle());
        existing.setDescription(event.getDescription());
        existing.setLocation(event.getLocation());
        existing.setCategory(event.getCategory());
        return eventRepository.save(existing);
    }

    @Override
    public Event getById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    @Override
    public List<Event> getActiveEvents() {
        return eventRepository.findByIsActiveTrue();
    }

    @Override
    public void deactivate(Long id) {
        Event event = getById(id);
        event.setIsActive(false);
        eventRepository.save(event);
    }
}
