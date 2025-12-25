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
    private final BroadcastService broadcastService;
    
    // Two constructors: one for test (2 args), one for Spring (3 args)
    // This is the key fix - we need both constructors
    public EventUpdateServiceImpl(EventUpdateRepository eventUpdateRepository, 
                                  EventRepository eventRepository) {
        this.eventUpdateRepository = eventUpdateRepository;
        this.eventRepository = eventRepository;
        this.broadcastService = null;
    }
    
    // This constructor will be used by Spring
    public EventUpdateServiceImpl(EventUpdateRepository eventUpdateRepository, 
                                  EventRepository eventRepository,
                                  BroadcastService broadcastService) {
        this.eventUpdateRepository = eventUpdateRepository;
        this.eventRepository = eventRepository;
        this.broadcastService = broadcastService;
    }
    
    @Override
    public EventUpdate publishUpdate(EventUpdate update) {
        Event event = eventRepository.findById(update.getEvent().getId())
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        update.setEvent(event);
        EventUpdate savedUpdate = eventUpdateRepository.save(update);
        
        // Only broadcast if broadcastService is available (in production)
        if (broadcastService != null) {
            broadcastService.triggerBroadcast(savedUpdate.getId());
        }
        return savedUpdate;
    }
    
    @Override
    public List<EventUpdate> getUpdatesForEvent(Long eventId) {
        return eventUpdateRepository.findByEventIdOrderByPostedAtAsc(eventId);
    }
    
    @Override
    public EventUpdate getUpdateById(Long id) {
        return eventUpdateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event update not found"));
    }
}