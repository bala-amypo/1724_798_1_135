package com.example.demo.controller;

import com.example.demo.entity.Subscription;
import com.example.demo.service.SubscriptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@Tag(name = "Subscriptions", description = "Subscription management endpoints")
public class SubscriptionController {
    
    private final SubscriptionService subscriptionService;
    
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }
    
    @PostMapping("/{eventId}")
    public ResponseEntity<Subscription> subscribe(@PathVariable Long eventId, @RequestParam Long userId) {
        Subscription subscription = subscriptionService.subscribe(userId, eventId);
        return ResponseEntity.ok(subscription);
    }
    
    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> unsubscribe(@PathVariable Long eventId, @RequestParam Long userId) {
        subscriptionService.unsubscribe(userId, eventId);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Subscription>> getUserSubscriptions(@PathVariable Long userId) {
        List<Subscription> subscriptions = subscriptionService.getSubscriptionsForUser(userId);
        return ResponseEntity.ok(subscriptions);
    }
    
    @GetMapping("/check/{userId}/{eventId}")
    public ResponseEntity<Boolean> checkSubscription(@PathVariable Long userId, @PathVariable Long eventId) {
        boolean isSubscribed = subscriptionService.checkSubscription(userId, eventId);
        return ResponseEntity.ok(isSubscribed);
    }
}