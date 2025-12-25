package com.example.demo.repository;

import com.example.demo.entity.EventUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface EventUpdateRepository extends JpaRepository<EventUpdate, Long> {
    List<EventUpdate> findByEventId(Long eventId);
    
    @Query("SELECT eu FROM EventUpdate eu WHERE eu.event.id = :eventId ORDER BY eu.timestamp ASC")
    List<EventUpdate> findByEventIdOrderByTimestampAsc(@Param("eventId") Long eventId);
    
    // Keep both for compatibility
    @Query("SELECT eu FROM EventUpdate eu WHERE eu.event.id = :eventId ORDER BY eu.timestamp ASC")
    List<EventUpdate> findByEventIdOrderByPostedAtAsc(@Param("eventId") Long eventId);
}