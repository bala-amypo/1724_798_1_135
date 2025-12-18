package com.example.eventsystem.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"user_id", "event_id"}
    )
)
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    private Timestamp subscribedAt;

    public Subscription() {}

    @PrePersist
    protected void onSubscribe() {
        this.subscribedAt = new Timestamp(System.currentTimeMillis());
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }
    
    public void setEvent(Event event) {
        this.event = event;
    }

    public Timestamp getSubscribedAt() {
        return subscribedAt;
    }
}
