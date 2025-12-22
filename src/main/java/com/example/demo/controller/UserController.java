package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping
    public User createUser(@RequestBody Map<String, Object> request) {
        User user = new User();
        user.setFullName((String) request.get("fullName"));
        user.setEmail((String) request.get("email"));
        user.setPassword((String) request.get("password"));
        user.setRole((String) request.get("role"));
        
        return userService.registerUser(user);
    }
    
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}

// package com.example.demo.controller;

// import com.example.demo.entity.User;
// import com.example.demo.service.UserService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/api/users")
// public class UserController {
    
//     @Autowired
//     private UserService userService;
    
//     @PostMapping
//     public User createUser(@RequestBody User user) {
//         return userService.registerUser(user);
//     }
    
//     @GetMapping("/{id}")
//     public User getUser(@PathVariable Long id) {
//         return userService.getUserById(id);
//     }
    
//     @GetMapping
//     public List<User> getAllUsers() {
//         return userService.getAllUsers();
//     }
// }