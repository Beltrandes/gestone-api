package com.gestone.gestone_api.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApprovedEmployeeRepository extends JpaRepository<ApprovedEmployee, Integer> {
    Optional<ApprovedEmployee>  findByEmail(String email);
}
