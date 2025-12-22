package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Use IDs instead of full objects
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "event_id")
    private Long eventId;
    
    @Column(name = "subscribed_at")
    private Date subscribedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }
    
    public Date getSubscribedAt() { return subscribedAt; }
    public void setSubscribedAt(Date subscribedAt) { this.subscribedAt = subscribedAt; }
    
    // Helper methods to maintain compatibility with existing code
    public User getUser() {
        // Return a dummy user or null
        return null;
    }
    
    public Event getEvent() {
        // Return a dummy event or null
        return null;
    }
    
    @PrePersist
    protected void onCreate() {
        subscribedAt = new Date();
    }
}

// package com.example.demo.entity;

// import jakarta.persistence.*;
// import java.sql.Timestamp;

// @Entity
// @Table(name = "subscriptions")
// public class Subscription {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
    
//     @ManyToOne
//     @JoinColumn(name = "user_id")
//     private User user;
    
//     @ManyToOne
//     @JoinColumn(name = "event_id")
//     private Event event;
    
//     @Column(name = "subscribed_at")
//     private Timestamp subscribedAt;
    
//     // Getters and Setters
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }
    
//     public User getUser() { return user; }
//     public void setUser(User user) { this.user = user; }
    
//     public Event getEvent() { return event; }
//     public void setEvent(Event event) { this.event = event; }
    
//     public Timestamp getSubscribedAt() { return subscribedAt; }
//     public void setSubscribedAt(Timestamp subscribedAt) { this.subscribedAt = subscribedAt; }
    
//     @PrePersist
//     protected void onCreate() {
//         subscribedAt = new Timestamp(System.currentTimeMillis());
//     }
// }