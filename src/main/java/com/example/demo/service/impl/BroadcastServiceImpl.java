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
    
    private final BroadcastLogRepository broadcastLogRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final EventUpdateRepository eventUpdateRepository;
    
    public BroadcastServiceImpl(BroadcastLogRepository broadcastLogRepository,
                               SubscriptionRepository subscriptionRepository,
                               EventUpdateRepository eventUpdateRepository) {
        this.broadcastLogRepository = broadcastLogRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.eventUpdateRepository = eventUpdateRepository;
    }
    
    @Override
    @Transactional
    public void triggerBroadcast(Long updateId) {
        EventUpdate eventUpdate = eventUpdateRepository.findById(updateId)
                .orElseThrow(() -> new IllegalArgumentException("EventUpdate not found"));
        
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        
        for (Subscription subscription : subscriptions) {
            if (subscription.getEvent().getId().equals(eventUpdate.getEvent().getId())) {
                BroadcastLog broadcastLog = new BroadcastLog();
                broadcastLog.setEventUpdate(eventUpdate);
                broadcastLog.setSubscriber(subscription.getUser());
                broadcastLog.setDeliveryStatus("SENT");
                
                broadcastLogRepository.save(broadcastLog);
            }
        }
    }
    
    @Override
    public List<BroadcastLog> getLogsForUpdate(Long updateId) {
        return broadcastLogRepository.findByEventUpdateId(updateId);
    }
    
    // Test expects: broadcastUpdate(long)
    public void broadcastUpdate(Long updateId) {
        triggerBroadcast(updateId);
    }
    
    // Test expects: recordDelivery(long, long, boolean)
    public void recordDelivery(Long updateId, Long userId, boolean success) {
        EventUpdate eventUpdate = eventUpdateRepository.findById(updateId)
                .orElseThrow(() -> new IllegalArgumentException("EventUpdate not found"));
        
        BroadcastLog broadcastLog = new BroadcastLog();
        broadcastLog.setEventUpdate(eventUpdate);
        broadcastLog.setDeliveryStatus(success ? "SENT" : "FAILED");
        
        broadcastLogRepository.save(broadcastLog);
    }
}