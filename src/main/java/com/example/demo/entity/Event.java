package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "events")
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    private String description;
    
    private String location;
    
    private String category;
    
    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private User publisher;
    
    @Column(nullable = false)
    private Boolean isActive = true;
    
    @Column(nullable = false)
    private Timestamp createdAt;
    
    @Column(nullable = false)
    private Timestamp lastUpdatedAt;
    
    public Event() {
    }
    
    // Add public methods for test
    public void onCreate() {
        this.createdAt = Timestamp.from(Instant.now());
        this.lastUpdatedAt = Timestamp.from(Instant.now());
        if (this.isActive == null) {
            this.isActive = true;
        }
    }
    
    public void onUpdate() {
        this.lastUpdatedAt = Timestamp.from(Instant.now());
    }
    
    @PrePersist
    protected void prePersist() {
        onCreate();
    }
    
    @PreUpdate
    protected void preUpdate() {
        onUpdate();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public User getPublisher() {
        return publisher;
    }
    
    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public boolean isActive() {
        return isActive != null && isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    public void setActive(boolean active) {
        this.isActive = active;
    }
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    public Timestamp getLastUpdatedAt() {
        return lastUpdatedAt;
    }
    
    public void setLastUpdatedAt(Timestamp lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }
}