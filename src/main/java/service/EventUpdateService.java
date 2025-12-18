package com.example.demo.service;

import com.example.demo.entity.EventUpdate;
import java.util.List;

public interface EventUpdateService {

    EventUpdate publish(EventUpdate update);

    List<EventUpdate> getByEvent(Long eventId);

    EventUpdate getById(Long id);
}
