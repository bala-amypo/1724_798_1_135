package com.example.demo.service.impl;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.entity.Event;
import com.example.demo.entity.EventUpdate;
import com.example.demo.entity.Subscription;
import com.example.demo.entity.User;
import com.example.demo.repository.BroadcastLogRepository;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.repository.SubscriptionRepository;
import com.example.demo.service.BroadcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;

@Service
public class BroadcastServiceImpl implements BroadcastService {
    
    private final BroadcastLogRepository broadcastLogRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final EventUpdateRepository eventUpdateRepository;
    
    @Autowired
    public BroadcastServiceImpl(BroadcastLogRepository broadcastLogRepository,
                               SubscriptionRepository subscriptionRepository,
                               EventUpdateRepository eventUpdateRepository) {
        this.broadcastLogRepository = broadcastLogRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.eventUpdateRepository = eventUpdateRepository;
    }
    
    @Override
    public void triggerBroadcast(Long updateId) {
        // Get the update
        EventUpdate update = eventUpdateRepository.findById(updateId)
                .orElseThrow(() -> new IllegalArgumentException("EventUpdate not found"));
        
        // Get the event
        Event event = update.getEvent();
        
        // Get all subscribers for this event
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        
        for (Subscription subscription : subscriptions) {
            if (subscription.getEvent().getId().equals(event.getId())) {
                User subscriber = subscription.getUser();
                
                // Create broadcast log
                BroadcastLog log = new BroadcastLog();
                log.setEventUpdate(update);
                log.setSubscriber(subscriber);
                log.setDeliveryStatus("SENT");
                log.setSentAt(new Timestamp(System.currentTimeMillis()));
                
                broadcastLogRepository.save(log);
            }
        }
    }
    
    @Override
    public List<BroadcastLog> getLogsForUpdate(Long updateId) {
        return broadcastLogRepository.findByEventUpdateId(updateId);
    }
}