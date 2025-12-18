// File: src/main/java/com/example/demo/entity/Subscription.java
package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "subscriptions", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "event_id"}))
public class Subscription {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
    
    @Column(name = "subscribed_at", nullable = false, updatable = false)
    private Timestamp subscribedAt;
    
    public Subscription() {
    }
    
    public Subscription(Long id, User user, Event event, Timestamp subscribedAt) {
        this.id = id;
        this.user = user;
        this.event = event;
        this.subscribedAt = subscribedAt;
    }
    
    @PrePersist
    protected void onCreate() {
        subscribedAt = Timestamp.valueOf(LocalDateTime.now());
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Event getEvent() {
        return event;
    }
    
    public void setEvent(Event event) {
        this.event = event;
    }
    
    public Timestamp getSubscribedAt() {
        return subscribedAt;
    }
    
    public void setSubscribedAt(Timestamp subscribedAt) {
        this.subscribedAt = subscribedAt;
    }
}