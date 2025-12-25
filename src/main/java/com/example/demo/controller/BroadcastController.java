package com.example.demo.controller;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.service.BroadcastService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/broadcasts")
@Tag(name = "Broadcasts", description = "Broadcast management endpoints")
public class BroadcastController {
    
    private final BroadcastService broadcastService;
    
    public BroadcastController(BroadcastService broadcastService) {
        this.broadcastService = broadcastService;
    }
    
    @PostMapping("/trigger/{updateId}")
    public ResponseEntity<Void> triggerBroadcast(@PathVariable Long updateId) {
        broadcastService.triggerBroadcast(updateId);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/logs/{updateId}")
    public ResponseEntity<List<BroadcastLog>> getLogsForUpdate(@PathVariable Long updateId) {
        List<BroadcastLog> logs = broadcastService.getLogsForUpdate(updateId);
        return ResponseEntity.ok(logs);
    }
}