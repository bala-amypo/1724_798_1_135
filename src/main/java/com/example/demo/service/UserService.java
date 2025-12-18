// File: src/main/java/com/example/demo/service/UserService.java
package com.example.demo.service;

import com.example.demo.entity.User;
import java.util.List;

public interface UserService {
    User registerUser(User user);
    User register(User user); // Test expects this
    User getUserById(Long id);
    List<User> getAllUsers();
    User getUserByEmail(String email);
    User findByEmail(String email); // Test expects this
}