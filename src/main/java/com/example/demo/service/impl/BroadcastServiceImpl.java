package com.example.demo.service.impl;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.entity.EventUpdate;
import com.example.demo.entity.Subscription;
import com.example.demo.repository.BroadcastLogRepository;
import com.example.demo.repository.SubscriptionRepository;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.service.BroadcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class BroadcastServiceImpl implements BroadcastService {
    
    private final EventUpdateRepository eventUpdateRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final BroadcastLogRepository broadcastLogRepository;
    
    // Match the test's expected constructor signature
    @Autowired
    public BroadcastServiceImpl(
            EventUpdateRepository eventUpdateRepository,
            SubscriptionRepository subscriptionRepository,
            BroadcastLogRepository broadcastLogRepository) {
        this.eventUpdateRepository = eventUpdateRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.broadcastLogRepository = broadcastLogRepository;
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
    
    @Override
    public void broadcastUpdate(Long updateId) {
        triggerBroadcast(updateId);
    }
    
    @Override
    public void recordDelivery(Long updateId, Long userId, boolean success) {
        EventUpdate eventUpdate = eventUpdateRepository.findById(updateId)
                .orElseThrow(() -> new IllegalArgumentException("EventUpdate not found"));
        
        BroadcastLog broadcastLog = new BroadcastLog();
        broadcastLog.setEventUpdate(eventUpdate);
        broadcastLog.setDeliveryStatus(success ? "SENT" : "FAILED");
        
        broadcastLogRepository.save(broadcastLog);
    }
}