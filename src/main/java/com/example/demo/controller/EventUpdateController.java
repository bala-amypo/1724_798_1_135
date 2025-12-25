package com.example.demo.controller;

import com.example.demo.entity.EventUpdate;
import com.example.demo.service.EventUpdateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/updates")
@Tag(name = "Event Updates", description = "Event update management endpoints")
public class EventUpdateController {
    
    private final EventUpdateService eventUpdateService;
    
    public EventUpdateController(EventUpdateService eventUpdateService) {
        this.eventUpdateService = eventUpdateService;
    }
    
    @PostMapping
    public ResponseEntity<EventUpdate> publishUpdate(@RequestBody EventUpdate update) {
        EventUpdate publishedUpdate = eventUpdateService.publishUpdate(update);
        return ResponseEntity.ok(publishedUpdate);
    }
    
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<EventUpdate>> getUpdatesForEvent(@PathVariable Long eventId) {
        List<EventUpdate> updates = eventUpdateService.getUpdatesForEvent(eventId);
        return ResponseEntity.ok(updates);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EventUpdate> getUpdateById(@PathVariable Long id) {
        EventUpdate update = eventUpdateService.getUpdateById(id);
        return ResponseEntity.ok(update);
    }
}