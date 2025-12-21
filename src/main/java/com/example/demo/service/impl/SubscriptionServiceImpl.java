package com.example.demo.service.impl;

import com.example.demo.entity.Event;
import com.example.demo.entity.Subscription;
import com.example.demo.entity.User;
import com.example.demo.repository.SubscriptionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.EventRepository;
import com.example.demo.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    
    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository,
                                  UserRepository userRepository,
                                  EventRepository eventRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }
    
    @Override
    public Subscription subscribe(Long userId, Long eventId) {
        // Check if already subscribed
        if (subscriptionRepository.existsByUserIdAndEventId(userId, eventId)) {
            throw new IllegalArgumentException("Already subscribed");
        }
        
        // Get user and event
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        
        // Create subscription
        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setEvent(event);
        
        return subscriptionRepository.save(subscription);
    }
    
    @Override
    public void unsubscribe(Long userId, Long eventId) {
        List<Subscription> subscriptions = subscriptionRepository.findByUserId(userId);
        
        for (Subscription subscription : subscriptions) {
            if (subscription.getEvent().getId().equals(eventId)) {
                subscriptionRepository.delete(subscription);
                return;
            }
        }
        
        throw new IllegalArgumentException("Subscription not found");
    }
    
    @Override
    public List<Subscription> getSubscriptionsForUser(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }
    
    @Override
    public boolean checkSubscription(Long userId, Long eventId) {
        return subscriptionRepository.existsByUserIdAndEventId(userId, eventId);
    }
}