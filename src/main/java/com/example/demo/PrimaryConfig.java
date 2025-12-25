// package com.example.demo.config;

// import com.example.demo.service.BroadcastService;
// import com.example.demo.service.EventUpdateService;
// import com.example.demo.service.impl.BroadcastServiceImpl;
// import com.example.demo.service.impl.EventUpdateServiceImpl;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Primary;

// @Configuration
// public class PrimaryConfig {
    
//     @Bean
//     @Primary
//     public EventUpdateService eventUpdateServicePrimary(
//             com.example.demo.repository.EventUpdateRepository eventUpdateRepository,
//             com.example.demo.repository.EventRepository eventRepository,
//             BroadcastService broadcastService) {
//         return new EventUpdateServiceImpl(eventUpdateRepository, eventRepository, broadcastService);
//     }
    
//     @Bean
//     @Primary  
//     public BroadcastService broadcastServicePrimary(
//             com.example.demo.repository.BroadcastLogRepository broadcastLogRepository,
//             com.example.demo.repository.SubscriptionRepository subscriptionRepository,
//             com.example.demo.repository.EventUpdateRepository eventUpdateRepository) {
//         return new BroadcastServiceImpl(broadcastLogRepository, subscriptionRepository, eventUpdateRepository, true);
//     }
// }