// File: src/main/java/com/example/demo/service/impl/SubscriptionServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.Subscription;
import com.example.demo.repository.SubscriptionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.EventRepository;
import com.example.demo.service.SubscriptionService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository,
                                  UserRepository userRepository,
                                  EventRepository eventRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }
    
    @Override
    public Subscription subscribe(Long userId, Long eventId) {
        if (subscriptionRepository.existsByUserIdAndEventId(userId, eventId)) {
            throw new IllegalArgumentException("Already subscribed");
        }
        
        Subscription subscription = new Subscription();
        subscription.setUser(userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found")));
        subscription.setEvent(eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found")));
        
        return subscriptionRepository.save(subscription);
    }
    
    @Override
    public void unsubscribe(Long userId, Long eventId) {
        subscriptionRepository.findByUserIdAndEventId(userId, eventId)
                .ifPresent(subscriptionRepository::delete);
    }
    
    @Override
    public List<Subscription> getSubscriptionsForUser(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }
    
    @Override
    public boolean checkSubscription(Long userId, Long eventId) {
        return subscriptionRepository.existsByUserIdAndEventId(userId, eventId);
    }
    
    // Test expects: isSubscribed(long, long)
    public boolean isSubscribed(Long userId, Long eventId) {
        return checkSubscription(userId, eventId);
    }
    
    // Test expects: getUserSubscriptions(long)
    public List<Subscription> getUserSubscriptions(Long userId) {
        return getSubscriptionsForUser(userId);
    }
}