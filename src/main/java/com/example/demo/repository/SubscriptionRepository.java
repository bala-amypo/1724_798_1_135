// File: src/main/java/com/example/demo/repository/SubscriptionRepository.java
package com.example.demo.repository;

import com.example.demo.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    // Test expects: existsByUserIdAndEventId(Long, Long)
    boolean existsByUserIdAndEventId(Long userId, Long eventId);
    
    // Test expects: findByUserId(Long)
    List<Subscription> findByUserId(Long userId);
    
    // Test expects: findByUserIdAndEventId(Long, Long)
    Optional<Subscription> findByUserIdAndEventId(Long userId, Long eventId);
    
    // Test expects: findByEventId(Long)
    List<Subscription> findByEventId(Long eventId);
    
    // Test might expect: findByEventId(long)
    List<Subscription> findByEventId(long eventId);
}