package com.example.eventsystem.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
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

    // PENDING / SENT / FAILED
    private String deliveryStatus;

    private Timestamp sentAt;

    public BroadcastLog() {}

    // Getters & Setters
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
