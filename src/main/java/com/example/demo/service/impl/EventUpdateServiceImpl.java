package com.example.demo.service.impl;

import com.example.demo.entity.Event;
import com.example.demo.entity.EventUpdate;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.service.BroadcastService;
import com.example.demo.service.EventUpdateService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventUpdateServiceImpl implements EventUpdateService {
    
    private final EventUpdateRepository eventUpdateRepository;
    private final EventRepository eventRepository;
    private BroadcastService broadcastService;
    
    // Constructor for test (2 arguments)
    public EventUpdateServiceImpl(EventUpdateRepository eventUpdateRepository, 
                                  EventRepository eventRepository) {
        this.eventUpdateRepository = eventUpdateRepository;
        this.eventRepository = eventRepository;
        this.broadcastService = null;
    }
    
    // Constructor for production (3 arguments)
    public EventUpdateServiceImpl(EventUpdateRepository eventUpdateRepository, 
                                  EventRepository eventRepository,
                                  BroadcastService broadcastService) {
        this.eventUpdateRepository = eventUpdateRepository;
        this.eventRepository = eventRepository;
        this.broadcastService = broadcastService;
    }
    
    public void setBroadcastService(BroadcastService broadcastService) {
        this.broadcastService = broadcastService;
    }
    
    @Override
    public EventUpdate publishUpdate(EventUpdate update) {
        Event event = eventRepository.findById(update.getEvent().getId())
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        update.setEvent(event);
        EventUpdate savedUpdate = eventUpdateRepository.save(update);
        
        if (broadcastService != null) {
            broadcastService.triggerBroadcast(savedUpdate.getId());
        }
        return savedUpdate;
    }
    
    @Override
    public List<EventUpdate> getUpdatesForEvent(Long eventId) {
        // Try both methods for compatibility
        try {
            return eventUpdateRepository.findByEventIdOrderByTimestampAsc(eventId);
        } catch (Exception e) {
            return eventUpdateRepository.findByEventIdOrderByPostedAtAsc(eventId);
        }
    }
    
    @Override
    public EventUpdate getUpdateById(Long id) {
        return eventUpdateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event update not found"));
    }
}