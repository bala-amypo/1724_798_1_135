
package com.example.demo.service.impl;

import com.example.demo.entity.Event;
import com.example.demo.repository.EventRepository;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    
    private final EventRepository eventRepository;
    
    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    
    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }
    
    @Override
    public Event updateEvent(Long id, Event event) {
        Event existingEvent = getEventById(id);
        existingEvent.setTitle(event.getTitle());
        existingEvent.setDescription(event.getDescription());
        existingEvent.setLocation(event.getLocation());
        existingEvent.setCategory(event.getCategory());
        existingEvent.setIsActive(event.getIsActive());
        return eventRepository.save(existingEvent);
    }
    
    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }
    
    @Override
    public List<Event> getActiveEvents() {
        return eventRepository.findByIsActiveTrue();
    }
    
    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    
    @Override
    public void deactivateEvent(Long id) {
    }
}

// package com.example.demo.service.impl;

// import com.example.demo.entity.Event;
// import com.example.demo.repository.EventRepository;
// import com.example.demo.service.EventService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import java.util.List;

// @Service
// public class EventServiceImpl implements EventService {
    
//     private final EventRepository eventRepository;
    
//     @Autowired
//     public EventServiceImpl(EventRepository eventRepository) {
//         this.eventRepository = eventRepository;
//     }
    
//     @Override
//     public Event createEvent(Event event) {
//         return eventRepository.save(event);
//     }
    
//     @Override
//     public Event updateEvent(Long id, Event event) {
//         Event existingEvent = eventRepository.findById(id).orElse(event);
//         if (event.getTitle() != null) existingEvent.setTitle(event.getTitle());
//         if (event.getDescription() != null) existingEvent.setDescription(event.getDescription());
//         if (event.getLocation() != null) existingEvent.setLocation(event.getLocation());
//         if (event.getCategory() != null) existingEvent.setCategory(event.getCategory());
//         if (event.getIsActive() != null) existingEvent.setIsActive(event.getIsActive());
        
//         return eventRepository.save(existingEvent);
//     }
    
//     @Override
//     public Event getEventById(Long id) {
//         return eventRepository.findById(id).orElse(null);
//     }
    
//     @Override
//     public List<Event> getActiveEvents() {
//         return eventRepository.findByIsActiveTrue();
//     }
    
//     @Override
//     public void deactivateEvent(Long id) {
//         Event event = eventRepository.findById(id).orElse(null);
//         if (event != null) {
//             event.setIsActive(false);
//             eventRepository.save(event);
//         }
//     }
// }