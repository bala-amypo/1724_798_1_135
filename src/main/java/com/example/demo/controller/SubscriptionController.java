package com.example.demo.controller;

import com.example.demo.entity.Subscription;
import com.example.demo.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
    
    @Autowired
    private SubscriptionService subscriptionService;
    
    @PostMapping("/{eventId}")
    public Subscription subscribe(@RequestParam Long userId, @PathVariable Long eventId) {
        return subscriptionService.subscribe(userId, eventId);
    }
    
    @DeleteMapping("/{eventId}")
    public void unsubscribe(@RequestParam Long userId, @PathVariable Long eventId) {
        subscriptionService.unsubscribe(userId, eventId);
    }
    
    @GetMapping("/user/{userId}")
    public List<Subscription> getSubscriptionsForUser(@PathVariable Long userId) {
        return subscriptionService.getSubscriptionsForUser(userId);
    }
    
    @GetMapping("/check/{userId}/{eventId}")
    public boolean checkSubscription(@PathVariable Long userId, @PathVariable Long eventId) {
        return subscriptionService.checkSubscription(userId, eventId);
    }
}