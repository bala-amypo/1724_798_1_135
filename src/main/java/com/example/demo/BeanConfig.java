// package com.example.demo.config;

// import com.example.demo.service.BroadcastService;
// import com.example.demo.service.EventUpdateService;
// import com.example.demo.service.impl.BroadcastServiceImpl;
// import com.example.demo.service.impl.EventUpdateServiceImpl;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Primary;

// @Configuration
// public class BeanConfig {
    
//     @Bean
//     @Primary
//     public BroadcastService broadcastService(
//             com.example.demo.repository.EventUpdateRepository eventUpdateRepository,
//             com.example.demo.repository.SubscriptionRepository subscriptionRepository,
//             com.example.demo.repository.BroadcastLogRepository broadcastLogRepository) {
//         return new BroadcastServiceImpl(eventUpdateRepository, subscriptionRepository, broadcastLogRepository);
//     }
// }