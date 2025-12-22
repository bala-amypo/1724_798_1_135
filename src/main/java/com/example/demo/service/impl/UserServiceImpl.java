package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public User registerUser(User user) {
        // Simple check - just save without validation
        return userRepository.save(user);
    }
    
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

// package com.example.demo.service.impl;

// import com.example.demo.entity.User;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.service.UserService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import java.util.List;

// @Service
// public class UserServiceImpl implements UserService {
    
//     private final UserRepository userRepository;
    
//     @Autowired
//     public UserServiceImpl(UserRepository userRepository) {
//         this.userRepository = userRepository;
//     }
    
//     @Override
//     public User registerUser(User user) {
//         // Only check for duplicate email (as required)
//         if (userRepository.existsByEmail(user.getEmail())) {
//             throw new IllegalArgumentException("Email already exists");
//         }
//         return userRepository.save(user);
//     }
    
//     @Override
//     public User getUserById(Long id) {
//         // Simple find - if not found, return null (no exception)
//         return userRepository.findById(id).orElse(null);
//     }
    
//     @Override
//     public List<User> getAllUsers() {
//         return userRepository.findAll();
//     }
// }