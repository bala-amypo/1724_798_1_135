package com.example.demo.service.impl;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.entity.EventUpdate;
import com.example.demo.entity.Subscription;
import com.example.demo.repository.BroadcastLogRepository;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.repository.SubscriptionRepository;
import com.example.demo.service.BroadcastService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BroadcastServiceImpl implements BroadcastService {
    
    private final EventUpdateRepository eventUpdateRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final BroadcastLogRepository broadcastLogRepository;
    
    // Constructor order matching test expectations
    public BroadcastServiceImpl(EventUpdateRepository eventUpdateRepository,
                                SubscriptionRepository subscriptionRepository,
                                BroadcastLogRepository broadcastLogRepository) {
        this.eventUpdateRepository = eventUpdateRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.broadcastLogRepository = broadcastLogRepository;
    }
    
    @Override
    public void triggerBroadcast(Long updateId) {
        broadcastUpdate(updateId);
    }
    
    @Override
    public void broadcastUpdate(Long updateId) {
        EventUpdate update = eventUpdateRepository.findById(updateId)
                .orElseThrow(() -> new IllegalArgumentException("Event update not found"));
        
        List<Subscription> subscriptions = subscriptionRepository.findByEventId(update.getEvent().getId());
        
        for (Subscription subscription : subscriptions) {
            BroadcastLog log = new BroadcastLog();
            log.setEventUpdate(update);
            log.setSubscriber(subscription.getUser());
            log.setDeliveryStatus("SENT");
            broadcastLogRepository.save(log);
        }
    }
    
    @Override
    public void recordDelivery(Long updateId, Long userId, boolean failed) {
        // Find the log for this update and user
        List<BroadcastLog> logs = broadcastLogRepository.findByEventUpdateId(updateId);
        for (BroadcastLog log : logs) {
            if (log.getSubscriber().getId().equals(userId)) {
                log.setDeliveryStatus(failed ? "SENT" : "FAILED");
                broadcastLogRepository.save(log);
                break;
            }
        }
    }
    
    @Override
    public List<BroadcastLog> getLogsForUpdate(Long updateId) {
        return broadcastLogRepository.findByEventUpdateId(updateId);
    }
}