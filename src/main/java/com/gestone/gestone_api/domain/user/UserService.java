package com.gestone.gestone_api.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User findByEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isEmpty()) return null;
        return user.get();
    }
}
