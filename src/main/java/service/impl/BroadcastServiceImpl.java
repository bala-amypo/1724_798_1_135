// File: src/main/java/com/example/demo/service/impl/BroadcastServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.entity.EventUpdate;
import com.example.demo.entity.Subscription;
import com.example.demo.repository.BroadcastLogRepository;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.repository.SubscriptionRepository;
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
        
        List<Subscription> subscriptions = subscriptionRepository.findAll()
                .stream()
                .filter(sub -> sub.getEvent().getId().equals(eventUpdate.getEvent().getId()))
                .toList();
        
        for (Subscription subscription : subscriptions) {
            BroadcastLog broadcastLog = new BroadcastLog();
            broadcastLog.setEventUpdate(eventUpdate);
            broadcastLog.setSubscriber(subscription.getUser());
            broadcastLog.setDeliveryStatus("SENT");
            
            broadcastLogRepository.save(broadcastLog);
        }
    }
    
    @Override
    public List<BroadcastLog> getLogsForUpdate(Long updateId) {
        return broadcastLogRepository.findByEventUpdateId(updateId);
    }
}