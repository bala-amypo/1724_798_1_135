package com.example.demo.service.impl;

import com.example.demo.entity.Event;
import com.example.demo.entity.User;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EventService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    
    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    public Event createEvent(Event event) {
        User publisher = userRepository.findById(event.getPublisher().getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        // Check if role is PUBLISHER or ADMIN
        if (publisher.getRole() != com.example.demo.entity.Role.PUBLISHER && 
            publisher.getRole() != com.example.demo.entity.Role.ADMIN) {
            throw new IllegalArgumentException("Only PUBLISHER or ADMIN can create events");
        }
        
        event.setPublisher(publisher);
        return eventRepository.save(event);
    }
    
    @Override
    public Event updateEvent(Long id, Event event) {
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        
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
    public Event getById(Long id) {
        return getEventById(id);
    }
    
    @Override
    public List<Event> getActiveEvents() {
        return eventRepository.findByIsActiveTrue();
    }
    
    @Override
    public void deactivateEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        event.setActive(false);
        eventRepository.save(event);
    }
}