package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "broadcast_logs")
public class BroadcastLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "event_update_id")
    private EventUpdate eventUpdate;
    
    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    private User subscriber;
    
    @Column(name = "delivery_status")
    private String deliveryStatus; // PENDING, SENT, FAILED
    
    @Column(name = "sent_at")
    private Timestamp sentAt;
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public EventUpdate getEventUpdate() { return eventUpdate; }
    public void setEventUpdate(EventUpdate eventUpdate) { this.eventUpdate = eventUpdate; }
    
    public User getSubscriber() { return subscriber; }
    public void setSubscriber(User subscriber) { this.subscriber = subscriber; }
    
    public String getDeliveryStatus() { return deliveryStatus; }
    public void setDeliveryStatus(String deliveryStatus) { this.deliveryStatus = deliveryStatus; }
    
    public Timestamp getSentAt() { return sentAt; }
    public void setSentAt(Timestamp sentAt) { this.sentAt = sentAt; }
    
    @PrePersist
    protected void onCreate() {
        sentAt = new Timestamp(System.currentTimeMillis());
    }
}