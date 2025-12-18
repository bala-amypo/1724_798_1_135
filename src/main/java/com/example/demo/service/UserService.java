// File: src/main/java/com/example/demo/service/UserService.java
package com.example.demo.service;

import com.example.demo.entity.User;
import java.util.List;

public interface UserService {
    User registerUser(User user);
    
    // ADD: Test expects register(User)
    User register(User user);
    
    User getUserById(Long id);
    List<User> getAllUsers();
    User getUserByEmail(String email);
    
    // ADD: Test expects findByEmail(String)
    User findByEmail(String email);
}