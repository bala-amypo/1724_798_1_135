package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Instant;

@Entity
@Table(name = "event_updates")
public class EventUpdate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
    
    @Column(columnDefinition = "TEXT")
    private String updateContent;
    
    @Column(name = "update_type")
    private String updateType; // INFO, WARNING, CRITICAL
    
    @Column(name = "timestamp", nullable = false, updatable = false)
    private Timestamp timestamp;
    
    public EventUpdate() {
    }
    
    public void onCreate() {
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }
    
    // Remove this duplicate method - it's causing the error
    // public Instant getTimestamp() {
    //     return timestamp != null ? timestamp.toInstant() : null;
    // }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Event getEvent() {
        return event;
    }
    
    public void setEvent(Event event) {
        this.event = event;
    }
    
    public String getUpdateContent() {
        return updateContent;
    }
    
    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }
    
    public String getUpdateType() {
        return updateType;
    }
    
    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }
    
    // Keep only this getter for Timestamp
    public Timestamp getTimestamp() {
        return timestamp;
    }
    
    // Test expects getTimestamp() that returns Instant? If so, rename:
    public Instant getTimestampAsInstant() {
        return timestamp != null ? timestamp.toInstant() : null;
    }
    
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}