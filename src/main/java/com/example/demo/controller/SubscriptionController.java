// File: src/main/java/com/example/demo/controller/SubscriptionController.java
package com.example.demo.controller;

import com.example.demo.entity.Subscription;
import com.example.demo.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Subscribe to an event")
    public ResponseEntity<Subscription> subscribe(@RequestHeader("X-User-Id") Long userId, 
                                                 @PathVariable Long eventId) {
        Subscription subscription = subscriptionService.subscribe(userId, eventId);
        return ResponseEntity.ok(subscription);
    }
    
    @DeleteMapping("/{eventId}")
    @Operation(summary = "Unsubscribe from an event")
    public ResponseEntity<Void> unsubscribe(@RequestHeader("X-User-Id") Long userId, 
                                           @PathVariable Long eventId) {
        subscriptionService.unsubscribe(userId, eventId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get all subscriptions for a user")
    public ResponseEntity<List<Subscription>> getSubscriptionsForUser(@PathVariable Long userId) {
        List<Subscription> subscriptions = subscriptionService.getSubscriptionsForUser(userId);
        return ResponseEntity.ok(subscriptions);
    }
    
    @GetMapping("/check/{userId}/{eventId}")
    @Operation(summary = "Check subscription status")
    public ResponseEntity<Boolean> checkSubscription(@PathVariable Long userId, 
                                                    @PathVariable Long eventId) {
        boolean isSubscribed = subscriptionService.checkSubscription(userId, eventId);
        return ResponseEntity.ok(isSubscribed);
    }
}