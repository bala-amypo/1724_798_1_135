
package com.example.demo.controller;

import com.example.demo.entity.Event;
import com.example.demo.entity.EventUpdate;
import com.example.demo.service.EventUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/updates")
public class EventUpdateController {
    
    @Autowired
    private EventUpdateService eventUpdateService;
    
    @PostMapping
    public EventUpdate publishUpdate(@RequestBody Map<String, Object> request) {
        EventUpdate update = new EventUpdate();
        update.setUpdateContent((String) request.get("updateContent"));
        update.setUpdateType((String) request.get("updateType"));
        
        // Create a simple Event object with just the ID
        Object eventId = request.get("eventId");
        if (eventId != null) {
            Event event = new Event();
            if (eventId instanceof Integer) {
                event.setId(((Integer) eventId).longValue());
            } else if (eventId instanceof String) {
                event.setId(Long.parseLong((String) eventId));
            } else if (eventId instanceof Long) {
                event.setId((Long) eventId);
            }
            update.setEvent(event);
        }
        
        return eventUpdateService.publishUpdate(update);
    }
    
    @GetMapping("/event/{eventId}")
    public List<EventUpdate> getUpdatesForEvent(@PathVariable Long eventId) {
        return eventUpdateService.getUpdatesForEvent(eventId);
    }
    
    @GetMapping("/{id}")
    public EventUpdate getUpdateById(@PathVariable Long id) {
        return eventUpdateService.getUpdateById(id);
    }
}
// package com.example.demo.controller;

// import com.example.demo.entity.EventUpdate;
// import com.example.demo.service.EventUpdateService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/api/updates")
// public class EventUpdateController {
    
//     @Autowired
//     private EventUpdateService eventUpdateService;
    
//     @PostMapping
//     public EventUpdate publishUpdate(@RequestBody EventUpdate update) {
//         return eventUpdateService.publishUpdate(update);
//     }
    
//     @GetMapping("/event/{eventId}")
//     public List<EventUpdate> getUpdatesForEvent(@PathVariable Long eventId) {
//         return eventUpdateService.getUpdatesForEvent(eventId);
//     }
    
//     @GetMapping("/{id}")
//     public EventUpdate getUpdateById(@PathVariable Long id) {
//         return eventUpdateService.getUpdateById(id);
//     }
// }