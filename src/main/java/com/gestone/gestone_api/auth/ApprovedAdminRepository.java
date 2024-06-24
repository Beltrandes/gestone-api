package com.gestone.gestone_api.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovedAdminRepository extends JpaRepository<ApprovedAdmin, Integer> {

    Optional<ApprovedAdmin> findByEmail(String email);
}
