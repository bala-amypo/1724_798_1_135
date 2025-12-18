// File: src/main/java/com/example/demo/repository/EventRepository.java
package com.example.demo.repository;

import com.example.demo.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    // Required: findByIsActiveTrue()
    List<Event> findByIsActiveTrue();
    
    // Test expects: findByIsActiveTrueAndCategory(String)
    List<Event> findByIsActiveTrueAndCategory(String category);
    
    // Test expects: findByIsActiveTrueAndLocationContainingIgnoreCase(String)
    List<Event> findByIsActiveTrueAndLocationContainingIgnoreCase(String location);
}