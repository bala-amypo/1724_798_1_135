// File: src/main/java/com/example/demo/service/BroadcastService.java
package com.example.demo.service;

import com.example.demo.entity.BroadcastLog;
import java.util.List;

public interface BroadcastService {
    void triggerBroadcast(Long updateId);
    List<BroadcastLog> getLogsForUpdate(Long updateId); // THIS WAS MISSING
    
    // Test expects these methods
    void broadcastUpdate(Long updateId);
    void recordDelivery(Long updateId, Long userId, boolean success);
}