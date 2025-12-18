// File: src/main/java/com/example/demo/entity/Event.java
package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Instant;

@Entity
@Table(name = "events")
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    private String location;
    
    private String category;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id", nullable = false)
    private User publisher;
    
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;
    
    @Column(name = "last_updated_at")
    private Timestamp lastUpdatedAt;
    
    public Event() {
    }
    
    public Event(Long id, String title, String description, String location, String category, 
                 User publisher, Boolean isActive, Timestamp createdAt, Timestamp lastUpdatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.category = category;
        this.publisher = publisher;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
    }
    
    @PrePersist
    public void onCreate() {
        createdAt = Timestamp.valueOf(LocalDateTime.now());
        if (lastUpdatedAt == null) {
            lastUpdatedAt = createdAt;
        }
    }
    
    @PreUpdate
    public void onUpdate() {
        lastUpdatedAt = Timestamp.valueOf(LocalDateTime.now());
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
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    // Test expects: setActive(boolean)
    public void setActive(Boolean active) {
        this.isActive = active;
    }
    
    // Test expects: isActive()
    public Boolean isActive() {
        return isActive;
    }
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    // Test expects: getLastUpdatedAt()
    public Timestamp getLastUpdatedAt() {
        return lastUpdatedAt;
    }
    
    public void setLastUpdatedAt(Timestamp lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }
    // In Event.java, add this method:


public Instant getLastUpdatedAtAsInstant() {
    return lastUpdatedAt != null ? lastUpdatedAt.toInstant() : null;
}

// Or if test directly calls getLastUpdatedAt() expecting Instant:
public Instant getLastUpdatedAt() {
    return lastUpdatedAt != null ? lastUpdatedAt.toInstant() : null;
}
}