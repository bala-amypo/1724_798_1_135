package com.example.demo.controller;

import com.example.eventsystem.entity.Subscription;
import com.example.eventsystem.service.SubscriptionService;

@RestController
@RequestMapping("/api/subscriptions")
@Tag(name = "Subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    // POST /{eventId}?userId=
    @PostMapping("/{eventId}")
    public ResponseEntity<Subscription> subscribe(
            @PathVariable Long eventId,
            @RequestParam Long userId) {
        return new ResponseEntity<>(
                subscriptionService.subscribe(userId, eventId),
                HttpStatus.CREATED
        );
    }

    // DELETE /{eventId}?userId=
    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> unsubscribe(
            @PathVariable Long eventId,
            @RequestParam Long userId) {
        subscriptionService.unsubscribe(userId, eventId);
        return ResponseEntity.noContent().build();
    }

    // GET /user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Subscription>> getUserSubscriptions(
            @PathVariable Long userId) {
        return ResponseEntity.ok(subscriptionService.getByUser(userId));
    }

    // GET /check/{userId}/{eventId}
    @GetMapping("/check/{userId}/{eventId}")
    public ResponseEntity<Boolean> check(
            @PathVariable Long userId,
            @PathVariable Long eventId) {
        return ResponseEntity.ok(
                subscriptionService.isSubscribed(userId, eventId)
        );
    }
}
