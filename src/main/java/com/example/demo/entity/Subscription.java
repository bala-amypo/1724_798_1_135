package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    
    @Column(name = "subscribed_at")
    private Timestamp subscribedAt;
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }
    
    public Timestamp getSubscribedAt() { return subscribedAt; }
    public void setSubscribedAt(Timestamp subscribedAt) { this.subscribedAt = subscribedAt; }
    
    @PrePersist
    protected void onCreate() {
        subscribedAt = new Timestamp(System.currentTimeMillis());
    }
}