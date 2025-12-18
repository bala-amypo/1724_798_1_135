// File: src/main/java/com/example/demo/repository/EventUpdateRepository.java
package com.example.demo.repository;

import com.example.demo.entity.EventUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventUpdateRepository extends JpaRepository<EventUpdate, Long> {
    // Required: findByEventId(Long)
    List<EventUpdate> findByEventId(Long eventId);
    
    // Test expects: findByEventIdOrderByTimestampAsc(Long)
    List<EventUpdate> findByEventIdOrderByTimestampAsc(Long eventId);
}