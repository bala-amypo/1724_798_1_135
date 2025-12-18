package com.example.demo.controller;

import com.example.eventsystem.entity.Event;
import com.example.eventsystem.service.EventService;

@RestController
@RequestMapping("/api/events")
@Tag(name = "Events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // POST /
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        return new ResponseEntity<>(eventService.create(event), HttpStatus.CREATED);
    }

    // PUT /{id}
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable Long id,
            @RequestBody Event event) {
        return ResponseEntity.ok(eventService.update(id, event));
    }

    // GET /{id}
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getById(id));
    }

    // GET /active
    @GetMapping("/active")
    public ResponseEntity<List<Event>> getActiveEvents() {
        return ResponseEntity.ok(eventService.getActiveEvents());
    }

    // PATCH /{id}/deactivate
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        eventService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}
