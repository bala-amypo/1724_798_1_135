package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "broadcast_logs")
public class BroadcastLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "event_update_id", nullable = false)
    private EventUpdate eventUpdate;
    
    @ManyToOne
    @JoinColumn(name = "subscriber_id", nullable = false)
    private User subscriber;
    
    @Column(nullable = false)
    private String deliveryStatus = "SENT"; // PENDING, SENT, FAILED
    
    @Column(nullable = false)
    private Timestamp sentAt;
    
    public BroadcastLog() {
    }
    
    @PrePersist
    protected void onCreate() {
        sentAt = Timestamp.from(Instant.now());
        if (deliveryStatus == null) {
            deliveryStatus = "SENT";
        }
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public EventUpdate getEventUpdate() {
        return eventUpdate;
    }
    
    public void setEventUpdate(EventUpdate eventUpdate) {
        this.eventUpdate = eventUpdate;
    }
    
    public User getSubscriber() {
        return subscriber;
    }
    
    public void setSubscriber(User subscriber) {
        this.subscriber = subscriber;
    }
    
    public String getDeliveryStatus() {
        return deliveryStatus;
    }
    
    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
    
    public Timestamp getSentAt() {
        return sentAt;
    }
    
    public void setSentAt(Timestamp sentAt) {
        this.sentAt = sentAt;
    }
}