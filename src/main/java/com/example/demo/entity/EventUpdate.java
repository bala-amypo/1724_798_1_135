// File: src/main/java/com/example/demo/entity/EventUpdate.java
package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "event_updates")
public class EventUpdate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
    
    @Column(name = "update_content", columnDefinition = "TEXT", nullable = false)
    private String updateContent;
    
    @Column(name = "update_type", nullable = false)
    private String updateType; // INFO, WARNING, CRITICAL
    
    @Column(name = "posted_at", nullable = false, updatable = false)
    private Timestamp postedAt;
    
    public EventUpdate() {
    }
    
    public EventUpdate(Long id, Event event, String updateContent, String updateType, Timestamp postedAt) {
        this.id = id;
        this.event = event;
        this.updateContent = updateContent;
        this.updateType = updateType;
        this.postedAt = postedAt;
    }
    
    @PrePersist
    protected void onCreate() {
        postedAt = Timestamp.valueOf(LocalDateTime.now());
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