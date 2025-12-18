package com.example.demo.service.impl;

import com.example.demo.entity.EventUpdate;
import com.example.demo.service.BroadcastService;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.service.EventUpdateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventUpdateServiceImpl implements EventUpdateService {

    private final EventUpdateRepository repository;
    private final BroadcastService broadcastService;

    public EventUpdateServiceImpl(EventUpdateRepository repository,
                                  BroadcastService broadcastService) {
        this.repository = repository;
        this.broadcastService = broadcastService;
    }

    @Override
    public EventUpdate publishUpdate(EventUpdate update) {
        EventUpdate saved = repository.save(update);
        broadcastService.triggerBroadcast(saved.getId());
        return saved;
    }

    @Override
    public List<EventUpdate> getUpdatesForEvent(Long eventId) {
        return repository.findByEventId(eventId);
    }

    @Override
    public EventUpdate getUpdateById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Update not found"));
    }
}
