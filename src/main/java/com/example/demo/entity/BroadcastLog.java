package com.example.demo.entity;

import jakarta.persistence.*;
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
    private Instant sentAt;
    
    public BroadcastLog() {
    }
    
    @PrePersist
    protected void onCreate() {
        sentAt = Instant.now();
        if (deliveryStatus == null) {
            deliveryStatus = "SENT";
        }
    }
    
    // For test compatibility
    public DeliveryStatus getDeliveryStatus() {
        try {
            return DeliveryStatus.valueOf(deliveryStatus);
        } catch (Exception e) {
            return DeliveryStatus.SENT;
        }
    }
    
    // For test compatibility
    public void setDeliveryStatus(DeliveryStatus status) {
        this.deliveryStatus = status.name();
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
    
    public String getDeliveryStatusString() {
        return deliveryStatus;
    }
    
    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
    
    public Instant getSentAt() {
        return sentAt;
    }
    
    public void setSentAt(Instant sentAt) {
        this.sentAt = sentAt;
    }
}