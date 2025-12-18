package com.example.demo.controller;


import com.example.eventsystem.entity.EventUpdate;
import com.example.eventsystem.service.EventUpdateService;

@RestController
@RequestMapping("/api/updates")
@Tag(name = "Event Updates")
public class EventUpdateController {

    private final EventUpdateService eventUpdateService;

    public EventUpdateController(EventUpdateService eventUpdateService) {
        this.eventUpdateService = eventUpdateService;
    }

    // POST /
    @PostMapping
    public ResponseEntity<EventUpdate> publishUpdate(@RequestBody EventUpdate update) {
        return new ResponseEntity<>(eventUpdateService.publish(update), HttpStatus.CREATED);
    }

    // GET /event/{eventId}
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<EventUpdate>> getByEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(eventUpdateService.getByEvent(eventId));
    }

    // GET /{id}
    @GetMapping("/{id}")
    public ResponseEntity<EventUpdate> getById(@PathVariable Long id) {
        return ResponseEntity.ok(eventUpdateService.getById(id));
    }
}
