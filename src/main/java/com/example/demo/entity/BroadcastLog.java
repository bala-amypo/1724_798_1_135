package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "broadcast_logs")
public class BroadcastLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_update_id", nullable = false)
    private EventUpdate eventUpdate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscriber_id", nullable = false)
    private User subscriber;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_status")
    private DeliveryStatus deliveryStatus;
    
    @Column(name = "sent_at")
    private Timestamp sentAt;
    
    public BroadcastLog() {
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
    
    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }
    
    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
    
    public Timestamp getSentAt() {
        return sentAt;
    }
    
    public void setSentAt(Timestamp sentAt) {
        this.sentAt = sentAt;
    }
}