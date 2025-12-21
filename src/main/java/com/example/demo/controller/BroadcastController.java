package com.example.demo.controller;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.service.BroadcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/broadcasts")
public class BroadcastController {
    
    @Autowired
    private BroadcastService broadcastService;
    
    @PostMapping("/trigger/{updateId}")
    public void triggerBroadcast(@PathVariable Long updateId) {
        broadcastService.triggerBroadcast(updateId);
    }
    
    @GetMapping("/logs/{updateId}")
    public List<BroadcastLog> getLogsForUpdate(@PathVariable Long updateId) {
        return broadcastService.getLogsForUpdate(updateId);
    }
}