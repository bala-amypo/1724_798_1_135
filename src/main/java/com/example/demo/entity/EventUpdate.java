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
    
    @Column(name = "posted_at", nullable = false, updatable = false)
    private Timestamp postedAt;
    
    public EventUpdate() {
    }
    
    // Make onCreate public
    public void onCreate() {
        this.postedAt = Timestamp.valueOf(LocalDateTime.now());
    }
    
    // Test expects getTimestamp()
    public Instant getTimestamp() {
        return postedAt != null ? postedAt.toInstant() : null;
    }
    
    // Test expects getSeverityLevel() - alias for updateType
    public String getSeverityLevel() {
        return updateType;
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
    
    public Timestamp getPostedAt() {
        return postedAt;
    }
    
    public void setPostedAt(Timestamp postedAt) {
        this.postedAt = postedAt;
    }
}