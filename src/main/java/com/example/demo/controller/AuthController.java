package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public User register(@RequestBody Map<String, Object> request) {
        User user = new User();
        user.setFullName((String) request.get("fullName"));
        user.setEmail((String) request.get("email"));
        user.setPassword((String) request.get("password"));
        user.setRole((String) request.get("role"));
        
        return userService.registerUser(user);
    }
    
    @PostMapping("/login")
    public String login(@RequestBody Map<String, Object> request) {
        String email = (String) request.get("email");
        return "Login successful for: " + email;
    }
}
// package com.example.demo.controller;

// import com.example.demo.dto.LoginRequest;
// import com.example.demo.dto.RegisterRequest;
// import com.example.demo.entity.User;
// import com.example.demo.service.UserService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {
    
//     @Autowired
//     private UserService userService;
    
//     @PostMapping("/register")
//     public User register(@RequestBody RegisterRequest request) {
//         User user = new User();
//         user.setFullName(request.getFullName());
//         user.setEmail(request.getEmail());
//         user.setPassword(request.getPassword());
//         user.setRole(request.getRole());
        
//         return userService.registerUser(user);
//     }
    
//     @PostMapping("/login")
//     public String login(@RequestBody LoginRequest request) {
//         return "Login successful for email: " + request.getEmail();
//     }
// }