package com.example.demo.controller;

import com.example.demo.entity.Event;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
public class EventController {
    
    @Autowired
    private EventService eventService;
    
    @PostMapping
    public Event createEvent(@RequestBody Map<String, Object> request) {
        Event event = new Event();
        event.setTitle((String) request.get("title"));
        event.setDescription((String) request.get("description"));
        event.setLocation((String) request.get("location"));
        event.setCategory((String) request.get("category"));
        
        // Handle publisherId (could be string or integer)
        Object publisherId = request.get("publisherId");
        if (publisherId != null) {
            if (publisherId instanceof Integer) {
                event.setPublisherId(((Integer) publisherId).longValue());
            } else if (publisherId instanceof String) {
                event.setPublisherId(Long.parseLong((String) publisherId));
            } else if (publisherId instanceof Long) {
                event.setPublisherId((Long) publisherId);
            }
        }
        
        return eventService.createEvent(event);
    }
    
    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        Event event = new Event();
        event.setTitle((String) request.get("title"));
        event.setDescription((String) request.get("description"));
        event.setLocation((String) request.get("location"));
        event.setCategory((String) request.get("category"));
        
        return eventService.updateEvent(id, event);
    }
    
    @GetMapping("/{id}")
    public Event getEvent(@PathVariable Long id) {
        return eventService.getEventById(id);
    }
    
    @GetMapping("/active")
    public List<Event> getActiveEvents() {
        return eventService.getActiveEvents();
    }
    
    // Use DELETE instead of PATCH for simplicity
    @DeleteMapping("/{id}")
    public String deactivateEvent(@PathVariable Long id) {
        eventService.deactivateEvent(id);
        return "Event deactivated";
    }
}

// package com.example.demo.controller;

// import com.example.demo.entity.Event;
// import com.example.demo.service.EventService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/api/events")
// public class EventController {
    
//     @Autowired
//     private EventService eventService;
    
//     @PostMapping
//     public Event createEvent(@RequestBody Event event) {
//         return eventService.createEvent(event);
//     }
    
//     @PutMapping("/{id}")
//     public Event updateEvent(@PathVariable Long id, @RequestBody Event event) {
//         return eventService.updateEvent(id, event);
//     }
    
//     @GetMapping("/{id}")
//     public Event getEvent(@PathVariable Long id) {
//         return eventService.getEventById(id);
//     }
    
//     @GetMapping("/active")
//     public List<Event> getActiveEvents() {
//         return eventService.getActiveEvents();
//     }
    
//     @PatchMapping("/{id}/deactivate")
//     public void deactivateEvent(@PathVariable Long id) {
//         eventService.deactivateEvent(id);
//     }
// }