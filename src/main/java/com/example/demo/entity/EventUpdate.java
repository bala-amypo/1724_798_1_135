package com.example.demo.entity;

import jakarta.persistence.*;
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
    
    @Enumerated(EnumType.STRING)
    @Column(name = "severity_level")
    private SeverityLevel severityLevel;
    
    @Column(name = "timestamp", nullable = false, updatable = false)
    private Instant timestamp;
    
    public EventUpdate() {
    }
    
    public void onCreate() {
        this.timestamp = Instant.now();
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
    
    // For original requirements - getUpdateType as string
    public String getUpdateType() {
        return severityLevel != null ? severityLevel.name() : null;
    }
    
    public void setUpdateType(String updateType) {
        if (updateType != null) {
            try {
                this.severityLevel = SeverityLevel.valueOf(updateType.toUpperCase());
            } catch (IllegalArgumentException e) {
                this.severityLevel = null;
            }
        } else {
            this.severityLevel = null;
        }
    }
    
    // For test - getSeverityLevel as enum
    public SeverityLevel getSeverityLevel() {
        return severityLevel;
    }
    
    public void setSeverityLevel(SeverityLevel severityLevel) {
        this.severityLevel = severityLevel;
    }
    
    public Instant getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}