package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
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
        EventUpdate update = eventUpdateRepository.findById(updateId).orElse(null);
        if (update == null) return;
        
        Event event = update.getEvent();
        if (event == null) return;
        
        List<Subscription> subscriptions = subscriptionRepository.findByEventId(event.getId());
        //List<Subscription> subscriptions = subscriptionRepository.findAll();
        
        for (Subscription subscription : subscriptions) {
            if (subscription.getEvent().getId().equals(event.getId())) {
                User subscriber = subscription.getUser();
                
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