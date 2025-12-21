package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // Stored as plain text
        user.setRole(request.getRole());
        
        return userService.registerUser(user);
    }
    
    // @PostMapping("/login")
    // public String login(@RequestBody LoginRequest request) {
    //     // Simple authentication - just return success message
    //     return "Login successful for: " + request.getEmail();
    // }
}