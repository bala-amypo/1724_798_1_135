package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
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
        // Only check for duplicate subscription
        if (subscriptionRepository.existsByUserIdAndEventId(userId, eventId)) {
            throw new IllegalArgumentException("Already subscribed");
        }
        
        // Get user and event - assume they exist
        User user = userRepository.findById(userId).orElse(null);
        Event event = eventRepository.findById(eventId).orElse(null);
        
        if (user == null || event == null) {
            return null;
        }
        
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