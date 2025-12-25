package com.example.demo.controller;

import com.example.demo.entity.Event;
import com.example.demo.service.EventService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@Tag(name = "Events", description = "Event management endpoints")
public class EventController {
    
    private final EventService eventService;
    
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        return ResponseEntity.ok(createdEvent);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        Event updatedEvent = eventService.updateEvent(id, event);
        return ResponseEntity.ok(updatedEvent);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<Event>> getActiveEvents() {
        List<Event> events = eventService.getActiveEvents();
        return ResponseEntity.ok(events);
    }
    
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateEvent(@PathVariable Long id) {
        eventService.deactivateEvent(id);
        return ResponseEntity.ok().build();
    }
}