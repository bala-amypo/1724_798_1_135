package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.entity.Role;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        User existingUser = userService.findByEmail(request.getEmail());
        if (existingUser != null) {
            throw new IllegalArgumentException("Email already exists");
        }
        
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        
        // Convert string role to Role enum
        user.setRole(Role.valueOf(request.getRole().toUpperCase()));
        
        User savedUser = userService.registerUser(user);
        
        // Generate token with correct parameters
        String token = jwtUtil.generateToken(
            savedUser.getId(),
            savedUser.getEmail(),
            savedUser.getRole().name()
        );
        
        return ResponseEntity.ok(token);
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        User user = userService.findByEmail(request.getEmail());
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        
        // Generate token with correct parameters
        String token = jwtUtil.generateToken(
            user.getId(),
            user.getEmail(),
            user.getRole().name()
        );
        
        return ResponseEntity.ok(token);
    }
}