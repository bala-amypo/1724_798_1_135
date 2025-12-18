// File: src/main/java/com/example/demo/service/impl/EventUpdateServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.EventUpdate;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.repository.EventRepository;
import com.example.demo.service.BroadcastService;
import com.example.demo.service.EventUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EventUpdateServiceImpl implements EventUpdateService {
    
    private final EventUpdateRepository eventUpdateRepository;
    private final EventRepository eventRepository;
    private final BroadcastService broadcastService;
    
    // Primary constructor with @Autowired
    @Autowired
    public EventUpdateServiceImpl(EventUpdateRepository eventUpdateRepository,
                                 EventRepository eventRepository,
                                 BroadcastService broadcastService) {
        this.eventUpdateRepository = eventUpdateRepository;
        this.eventRepository = eventRepository;
        this.broadcastService = broadcastService;
    }
    
    // Add this constructor for tests (2 parameters)
    public EventUpdateServiceImpl(EventUpdateRepository eventUpdateRepository,
                                 EventRepository eventRepository) {
        this.eventUpdateRepository = eventUpdateRepository;
        this.eventRepository = eventRepository;
        this.broadcastService = null;
    }
    
    // Add no-argument constructor
    public EventUpdateServiceImpl() {
        this.eventUpdateRepository = null;
        this.eventRepository = null;
        this.broadcastService = null;
    }
    
    @Override
    @Transactional
    public EventUpdate publishUpdate(EventUpdate update) {
        eventRepository.findById(update.getEvent().getId())
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        
        EventUpdate savedUpdate = eventUpdateRepository.save(update);
        
        if (broadcastService != null) {
            broadcastService.triggerBroadcast(savedUpdate.getId());
        }
        
        return savedUpdate;
    }
    
    @Override
    public List<EventUpdate> getUpdatesForEvent(Long eventId) {
        return eventUpdateRepository.findByEventId(eventId);
    }
    
    @Override
    public EventUpdate getUpdateById(Long id) {
        return eventUpdateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("EventUpdate not found"));
    }
}