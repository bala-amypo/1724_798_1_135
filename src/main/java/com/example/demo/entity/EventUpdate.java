package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "event_updates")
public class EventUpdate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
    
    @Column(nullable = false)
    private String updateContent;
    
    @Column(nullable = false)
    private String updateType; // INFO, WARNING, CRITICAL
    
    @Column(name = "posted_at", nullable = false)
    private Timestamp timestamp;
    
    public EventUpdate() {
    }
    
    // Public method for tests
    public void onCreate() {
        timestamp = Timestamp.from(Instant.now());
        if (updateType == null) {
            updateType = "INFO";
        }
    }
    
    @PrePersist
    protected void prePersist() {
        onCreate();
    }
    
    // For test compatibility
    public SeverityLevel getSeverityLevel() {
        try {
            return SeverityLevel.valueOf(updateType);
        } catch (Exception e) {
            return SeverityLevel.LOW;
        }
    }
    
    // For test compatibility
    public void setSeverityLevel(SeverityLevel severityLevel) {
        this.updateType = severityLevel.name();
    }
    
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
    
    public Timestamp getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}