package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.BroadcastService;
import com.example.demo.service.EventUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventUpdateServiceImpl implements EventUpdateService {
    
    private final EventUpdateRepository eventUpdateRepository;
    private final EventRepository eventRepository;
    private final BroadcastService broadcastService;
    
    @Autowired
    public EventUpdateServiceImpl(EventUpdateRepository eventUpdateRepository,
                                 EventRepository eventRepository,
                                 BroadcastService broadcastService) {
        this.eventUpdateRepository = eventUpdateRepository;
        this.eventRepository = eventRepository;
        this.broadcastService = broadcastService;
    }
    
    @Override
    public EventUpdate publishUpdate(EventUpdate update) {
        EventUpdate savedUpdate = eventUpdateRepository.save(update);
        broadcastService.triggerBroadcast(savedUpdate.getId());
        return savedUpdate;
    }
    
    @Override
    public List<EventUpdate> getUpdatesForEvent(Long eventId) {
        return eventUpdateRepository.findByEventId(eventId);
    }
    
    @Override
    public EventUpdate getUpdateById(Long id) {
        return eventUpdateRepository.findById(id).orElse(null);
    }
}