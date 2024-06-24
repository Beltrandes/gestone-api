package com.gestone.gestone_api.domain.user;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, UUID> {
    UserDetails findByEmail(String email);

    List<User> findUsersByMarbleshopId(UUID marbleshopId);
}
