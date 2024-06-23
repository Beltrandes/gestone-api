package com.gestone.gestone_api.user;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
    public User findUserByEmail(String email);
    public List<User> findUsersByMarbleshopId(UUID marbleshopId);
}
