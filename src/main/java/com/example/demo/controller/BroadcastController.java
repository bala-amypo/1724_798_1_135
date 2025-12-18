package com.example.demo.controller;


import com.example.eventsystem.entity.BroadcastLog;
import com.example.eventsystem.service.BroadcastService;

@RestController
@RequestMapping("/api/broadcasts")
@Tag(name = "Broadcasts")
public class BroadcastController {

    private final BroadcastService broadcastService;

    public BroadcastController(BroadcastService broadcastService) {
        this.broadcastService = broadcastService;
    }

    // POST /trigger/{updateId}
    @PostMapping("/trigger/{updateId}")
    public ResponseEntity<Void> trigger(@PathVariable Long updateId) {
        broadcastService.trigger(updateId);
        return ResponseEntity.accepted().build();
    }

    // GET /logs/{updateId}
    @GetMapping("/logs/{updateId}")
    public ResponseEntity<List<BroadcastLog>> getLogs(
            @PathVariable Long updateId) {
        return ResponseEntity.ok(
                broadcastService.getLogs(updateId)
        );
    }
}
