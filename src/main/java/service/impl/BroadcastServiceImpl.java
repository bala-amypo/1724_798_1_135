package com.example.demo.service.impl;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.entity.EventUpdate;
import com.example.demo.entity.Subscription;
import com.example.demo.repository.BroadcastLogRepository;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.repository.SubscriptionRepository;
import com.example.demo.service.BroadcastService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class BroadcastServiceImpl implements BroadcastService {

    private final EventUpdateRepository updateRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final BroadcastLogRepository logRepository;

    public BroadcastServiceImpl(EventUpdateRepository updateRepository,
                                SubscriptionRepository subscriptionRepository,
                                BroadcastLogRepository logRepository) {
        this.updateRepository = updateRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.logRepository = logRepository;
    }

    @Override
    public void triggerBroadcast(Long updateId) {
        EventUpdate update = updateRepository.findById(updateId)
                .orElseThrow(() -> new RuntimeException("Update not found"));

        List<Subscription> subscriptions =
                subscriptionRepository.findByEventId(update.getEvent().getId());

        for (Subscription sub : subscriptions) {
            BroadcastLog log = new BroadcastLog();
            log.setEventUpdate(update);
            log.setSubscriber(sub.getUser());
            log.setDeliveryStatus("SENT");
            log.setSentAt(new Timestamp(System.currentTimeMillis()));
            logRepository.save(log);
        }
    }

    @Override
    public List<BroadcastLog> getLogsForUpdate(Long updateId) {
        return logRepository.findByEventUpdateId(updateId);
    }
}
