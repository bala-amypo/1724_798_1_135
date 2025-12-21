package com.example.demo.service;

import com.example.demo.entity.BroadcastLog;
import java.util.List;

public interface BroadcastService {
    void triggerBroadcast(Long updateId);
    List<BroadcastLog> getLogsForUpdate(Long updateId);
}