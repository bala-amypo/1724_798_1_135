// File: src/main/java/com/example/demo/service/impl/BroadcastServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.entity.EventUpdate;
import com.example.demo.entity.Subscription;
import com.example.demo.repository.BroadcastLogRepository;
import com.example.demo.repository.SubscriptionRepository;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.service.BroadcastService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class BroadcastServiceImpl implements BroadcastService {
    
    private BroadcastLogRepository broadcastLogRepository;
    private SubscriptionRepository subscriptionRepository;
    private EventUpdateRepository eventUpdateRepository;
    
    // ADD THIS: No-argument constructor (required by Spring)
    public BroadcastServiceImpl() {
        // Constructor can be empty - Spring will inject dependencies via setters or @Autowired
    }
    
    // Keep your existing constructors
    public BroadcastServiceImpl(BroadcastLogRepository broadcastLogRepository,
                               SubscriptionRepository subscriptionRepository,
                               EventUpdateRepository eventUpdateRepository) {
        this.broadcastLogRepository = broadcastLogRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.eventUpdateRepository = eventUpdateRepository;
    }
    
    public BroadcastServiceImpl(EventUpdateRepository wrongRepo1, 
                               SubscriptionRepository subscriptionRepository, 
                               EventUpdateRepository wrongRepo2) {
        this.broadcastLogRepository = null;
        this.subscriptionRepository = subscriptionRepository;
        this.eventUpdateRepository = wrongRepo2;
    }
    
    // Add @Autowired annotation to fields if needed
    // @Autowired
    // private BroadcastLogRepository broadcastLogRepository;
    
    // ... rest of your methods remain the same
    @Override
    @Transactional
    public void triggerBroadcast(Long updateId) {
        if (eventUpdateRepository == null) return;
        
        EventUpdate eventUpdate = eventUpdateRepository.findById(updateId)
                .orElseThrow(() -> new IllegalArgumentException("EventUpdate not found"));
        
        List<Subscription> subscriptions = subscriptionRepository != null ? 
            subscriptionRepository.findAll() : List.of();
        
        for (Subscription subscription : subscriptions) {
            if (subscription.getEvent().getId().equals(eventUpdate.getEvent().getId())) {
                BroadcastLog broadcastLog = new BroadcastLog();
                broadcastLog.setEventUpdate(eventUpdate);
                broadcastLog.setSubscriber(subscription.getUser());
                broadcastLog.setDeliveryStatus("SENT");
                
                if (broadcastLogRepository != null) {
                    broadcastLogRepository.save(broadcastLog);
                }
            }
        }
    }
    
    @Override
    public List<BroadcastLog> getLogsForUpdate(Long updateId) {
        if (broadcastLogRepository == null) {
            return List.of();
        }
        return broadcastLogRepository.findByEventUpdateId(updateId);
    }
    
    public void broadcastUpdate(Long updateId) {
        triggerBroadcast(updateId);
    }
    
    public void recordDelivery(Long updateId, Long userId, boolean success) {
        if (broadcastLogRepository == null || eventUpdateRepository == null) return;
        
        EventUpdate eventUpdate = eventUpdateRepository.findById(updateId)
                .orElseThrow(() -> new IllegalArgumentException("EventUpdate not found"));
        
        BroadcastLog broadcastLog = new BroadcastLog();
        broadcastLog.setEventUpdate(eventUpdate);
        broadcastLog.setDeliveryStatus(success ? "SENT" : "FAILED");
        
        broadcastLogRepository.save(broadcastLog);
    }
}