package com.example.demo.config;

import com.example.demo.service.BroadcastService;
import com.example.demo.service.EventUpdateService;
import com.example.demo.service.impl.EventUpdateServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {
    
    private final EventUpdateService eventUpdateService;
    private final BroadcastService broadcastService;
    
    public ServiceConfiguration(EventUpdateService eventUpdateService, 
                                BroadcastService broadcastService) {
        this.eventUpdateService = eventUpdateService;
        this.broadcastService = broadcastService;
    }
    
    @PostConstruct
    public void init() {
        // Set the broadcast service on EventUpdateService
        if (eventUpdateService instanceof EventUpdateServiceImpl) {
            ((EventUpdateServiceImpl) eventUpdateService).setBroadcastService(broadcastService);
        }
    }
}