// File: src/main/java/com/example/demo/controller/EventController.java
package com.example.demo.controller;

import com.example.demo.entity.Event;
import com.example.demo.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Create a new event")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        return ResponseEntity.ok(createdEvent);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing event")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        Event updatedEvent = eventService.updateEvent(id, event);
        return ResponseEntity.ok(updatedEvent);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get event by ID")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }
    
    @GetMapping("/active")
    @Operation(summary = "Get all active events")
    public ResponseEntity<List<Event>> getActiveEvents() {
        List<Event> events = eventService.getActiveEvents();
        return ResponseEntity.ok(events);
    }
    
    @PatchMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate an event")
    public ResponseEntity<Event> deactivateEvent(@PathVariable Long id) {
        Event event = eventService.deactivateEvent(id);
        return ResponseEntity.ok(event);
    }
}