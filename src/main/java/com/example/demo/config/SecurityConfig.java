package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                .requestMatchers("/").permitAll()
                .requestMatchers("/health").permitAll()
                
                // User endpoints
                .requestMatchers(HttpMethod.GET, "/api/users/**").hasAnyRole("ADMIN", "PUBLISHER", "SUBSCRIBER")
                
                // Event endpoints - PUBLISHER and ADMIN can create/update
                .requestMatchers(HttpMethod.POST, "/api/events").hasAnyRole("PUBLISHER", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/events/**").hasAnyRole("PUBLISHER", "ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/api/events/**/deactivate").hasAnyRole("PUBLISHER", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/events/**").hasAnyRole("ADMIN", "PUBLISHER", "SUBSCRIBER")
                
                // Event update endpoints - Only PUBLISHER and ADMIN can publish updates
                .requestMatchers(HttpMethod.POST, "/api/updates").hasAnyRole("PUBLISHER", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/updates/**").hasAnyRole("ADMIN", "PUBLISHER", "SUBSCRIBER")
                
                // Subscription endpoints - All users (including SUBSCRIBERS) can access
                .requestMatchers(HttpMethod.POST, "/api/subscriptions/**").hasAnyRole("SUBSCRIBER", "ADMIN", "PUBLISHER")
                .requestMatchers(HttpMethod.DELETE, "/api/subscriptions/**").hasAnyRole("SUBSCRIBER", "ADMIN", "PUBLISHER")
                .requestMatchers(HttpMethod.GET, "/api/subscriptions/**").hasAnyRole("SUBSCRIBER", "ADMIN", "PUBLISHER")
                
                // Broadcast endpoints - Only ADMIN and PUBLISHER can trigger broadcasts
                .requestMatchers(HttpMethod.POST, "/api/broadcasts/trigger/**").hasAnyRole("PUBLISHER", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/broadcasts/**").hasAnyRole("ADMIN", "PUBLISHER", "SUBSCRIBER")
                
                // Default - require authentication for everything else
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
}