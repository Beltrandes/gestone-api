package com.gestone.gestone_api.marbleshop;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MarbleshopRepository extends JpaRepository<Marbleshop, UUID> {
    
}
