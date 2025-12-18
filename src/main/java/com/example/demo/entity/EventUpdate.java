package com.example.eventsystem.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class EventUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    private String updateContent;

    // INFO / WARNING / CRITICAL
    private String updateType;

    private Timestamp postedAt;

    public EventUpdate() {}

    @PrePersist
    protected void onPost() {
        this.postedAt = new Timestamp(System.currentTimeMillis());
    }

    // Getters & Setters
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
}
