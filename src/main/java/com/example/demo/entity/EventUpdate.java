
package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "event_updates")
public class EventUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Just use event ID instead of full Event object
    @Column(name = "event_id")
    private Long eventId;
    
    @Column(name = "update_content")
    private String updateContent;
    
    @Column(name = "update_type")
    private String updateType;
    
    @Column(name = "posted_at")
    private Date postedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }
    
    public String getUpdateContent() { return updateContent; }
    public void setUpdateContent(String updateContent) { this.updateContent = updateContent; }
    
    public String getUpdateType() { return updateType; }
    public void setUpdateType(String updateType) { this.updateType = updateType; }
    
    public Date getPostedAt() { return postedAt; }
    public void setPostedAt(Date postedAt) { this.postedAt = postedAt; }
    
    @PrePersist
    protected void onCreate() {
        postedAt = new Date();
    }
}

// package com.example.demo.entity;

// import jakarta.persistence.*;
// import java.sql.Timestamp;

// @Entity
// @Table(name = "event_updates")
// public class EventUpdate {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
    
//     @ManyToOne
//     @JoinColumn(name = "event_id")
//     private Event event;
    
//     @Column(name = "update_content")
//     private String updateContent;
    
//     @Column(name = "update_type")
//     private String updateType; // INFO, WARNING, CRITICAL
    
//     @Column(name = "posted_at")
//     private Timestamp postedAt;
    
//     // Getters and Setters
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }
    
//     public Event getEvent() { return event; }
//     public void setEvent(Event event) { this.event = event; }
    
//     public String getUpdateContent() { return updateContent; }
//     public void setUpdateContent(String updateContent) { this.updateContent = updateContent; }
    
//     public String getUpdateType() { return updateType; }
//     public void setUpdateType(String updateType) { this.updateType = updateType; }
    
//     public Timestamp getPostedAt() { return postedAt; }
//     public void setPostedAt(Timestamp postedAt) { this.postedAt = postedAt; }
    
//     @PrePersist
//     protected void onCreate() {
//         postedAt = new Timestamp(System.currentTimeMillis());
//     }
// }