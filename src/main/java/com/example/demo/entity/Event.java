package com.example.eventsystem.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String location;

    private String category;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private User publisher;

    private Boolean isActive = true;

    private Timestamp createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public Event() {}

    // getters & setters
}
